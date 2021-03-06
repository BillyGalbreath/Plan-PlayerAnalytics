/*
 * Licence is provided in the jar as license.yml also here:
 * https://github.com/Rsl1122/Plan-PlayerAnalytics/blob/master/Plan/src/main/resources/license.yml
 */
package main.java.com.djrapitops.plan;

import com.djrapitops.plugin.BungeePlugin;
import com.djrapitops.plugin.StaticHolder;
import com.djrapitops.plugin.api.Benchmark;
import com.djrapitops.plugin.api.config.Config;
import com.djrapitops.plugin.api.systems.TaskCenter;
import com.djrapitops.plugin.api.utility.log.DebugLog;
import com.djrapitops.plugin.api.utility.log.Log;
import com.djrapitops.plugin.settings.ColorScheme;
import com.djrapitops.plugin.task.RunnableFactory;
import main.java.com.djrapitops.plan.api.IPlan;
import main.java.com.djrapitops.plan.command.PlanBungeeCommand;
import main.java.com.djrapitops.plan.database.Database;
import main.java.com.djrapitops.plan.settings.Settings;
import main.java.com.djrapitops.plan.settings.locale.Locale;
import main.java.com.djrapitops.plan.settings.locale.Msg;
import main.java.com.djrapitops.plan.settings.theme.PlanColorScheme;
import main.java.com.djrapitops.plan.settings.theme.Theme;
import main.java.com.djrapitops.plan.systems.Systems;
import main.java.com.djrapitops.plan.systems.file.FileSystem;
import main.java.com.djrapitops.plan.systems.file.config.ConfigSystem;
import main.java.com.djrapitops.plan.systems.file.database.DBSystem;
import main.java.com.djrapitops.plan.systems.info.BungeeInformationManager;
import main.java.com.djrapitops.plan.systems.info.InformationManager;
import main.java.com.djrapitops.plan.systems.info.server.BungeeServerInfoManager;
import main.java.com.djrapitops.plan.systems.listeners.BungeePlayerListener;
import main.java.com.djrapitops.plan.systems.processing.Processor;
import main.java.com.djrapitops.plan.systems.queue.ProcessingQueue;
import main.java.com.djrapitops.plan.systems.tasks.TaskSystem;
import main.java.com.djrapitops.plan.systems.update.VersionCheckSystem;
import main.java.com.djrapitops.plan.systems.webserver.WebServer;
import main.java.com.djrapitops.plan.systems.webserver.WebServerSystem;
import main.java.com.djrapitops.plan.utilities.file.export.HtmlExport;

import java.io.InputStream;
import java.util.UUID;

/**
 * Bungee Main class.
 *
 * @author Rsl1122
 */
public class PlanBungee extends BungeePlugin implements IPlan {

    private Systems systems;

    private BungeeServerInfoManager serverInfoManager;
    private BungeeInformationManager infoManager;
    private ServerVariableHolder variableHolder;

    private ProcessingQueue processingQueue;

    private boolean setupAllowed = false;

    @Override
    public void onEnable() {
        super.onEnable();
        try {
            systems = new Systems(this);
            FileSystem.getInstance().init();
            ConfigSystem.getInstance().init();

            Log.setDebugMode(Settings.DEBUG.toString());

            VersionCheckSystem.getInstance().init();

            variableHolder = new ServerVariableHolder(getProxy());

            new Locale().loadLocale();

            Theme.getInstance().init();
            DBSystem.getInstance().init();

            String ip = variableHolder.getIp();
            if ("0.0.0.0".equals(ip)) {
                Log.error("IP setting still 0.0.0.0 - Configure AlternativeIP/IP that connects to the Proxy server.");
                Log.info("Player Analytics partially enabled (Use /planbungee to reload config)");
                return;
            }

            Benchmark.start("WebServer Initialization");

            serverInfoManager = new BungeeServerInfoManager(this);
            infoManager = new BungeeInformationManager(this);

            WebServerSystem.getInstance().init();
            serverInfoManager.loadServerInfo();

            TaskSystem.getInstance().init();

            processingQueue = new ProcessingQueue();

            registerListener(new BungeePlayerListener(this));

            Log.logDebug("Enable", "WebServer Initialization");
            Log.info(Locale.get(Msg.ENABLED).toString());
            if (Settings.ANALYSIS_EXPORT.isTrue()) {
                RunnableFactory.createNew(new HtmlExport(this)).runTaskAsynchronously();
            }
        } catch (Exception e) {
            Log.error("Plugin Failed to Initialize Correctly.");
            Log.toLog(this.getClass().getName(), e);
        }
        registerCommand("planbungee", new PlanBungeeCommand(this));
    }

    public static PlanBungee getInstance() {
        return (PlanBungee) StaticHolder.getInstance(PlanBungee.class);
    }

    @Override
    public void onDisable() {
        if (processingQueue != null) {
            try {
                processingQueue.stop();
            } catch (IllegalArgumentException ignored) {
                /*ignored*/
            }
        }
        systems.close();
        Log.info(Locale.get(Msg.DISABLED).toString());
        Benchmark.pluginDisabled(PlanBungee.class);
        DebugLog.pluginDisabled(PlanBungee.class);
        TaskCenter.cancelAllKnownTasks(PlanBungee.class);
    }

    @Override
    public String getVersion() {
        return super.getDescription().getVersion();
    }

    @Override
    public void onReload() {
        ConfigSystem.reload();
    }

    @Override
    @Deprecated
    public Database getDB() {
        return DBSystem.getInstance().getActiveDatabase();
    }

    public BungeeServerInfoManager getServerInfoManager() {
        return serverInfoManager;
    }

    @Override
    public InformationManager getInfoManager() {
        return infoManager;
    }

    @Override
    public WebServer getWebServer() {
        return WebServerSystem.getInstance().getWebServer();
    }


    @Override
    public ProcessingQueue getProcessingQueue() {
        return processingQueue;
    }

    @Override
    public void addToProcessQueue(Processor... processors) {
        for (Processor processor : processors) {
            processingQueue.addToQueue(processor);
        }
    }

    @Override
    public InputStream getResource(String resource) {
        return getResourceAsStream(resource);
    }

    @Override
    public Config getMainConfig() {
        return ConfigSystem.getInstance().getConfig();
    }

    @Override
    public ColorScheme getColorScheme() {
        return PlanColorScheme.create();
    }

    @Override
    public ServerVariableHolder getVariable() {
        return variableHolder;
    }

    public static UUID getServerUUID() {
        return getInstance().getServerUuid();
    }

    public UUID getServerUuid() {
        return serverInfoManager.getServerUUID();
    }

    @Override
    public Systems getSystems() {
        return systems;
    }

    public boolean isSetupAllowed() {
        return setupAllowed;
    }

    public void setSetupAllowed(boolean setupAllowed) {
        this.setupAllowed = setupAllowed;
    }
}