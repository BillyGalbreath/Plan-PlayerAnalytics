/*
 * Licence is provided in the jar as license.yml also here:
 * https://github.com/Rsl1122/Plan-PlayerAnalytics/blob/master/Plan/src/main/resources/license.yml
 */
package main.java.com.djrapitops.plan.command.commands;

import com.djrapitops.plugin.command.CommandType;
import com.djrapitops.plugin.command.ISender;
import com.djrapitops.plugin.command.SubCommand;
import main.java.com.djrapitops.plan.PlanBungee;
import main.java.com.djrapitops.plan.settings.Permissions;

/**
 * //TODO Class Javadoc Comment
 *
 * @author Rsl1122
 */
public class BungeeSetupToggleCommand extends SubCommand {

    private final PlanBungee plugin;

    public BungeeSetupToggleCommand(PlanBungee plugin) {
        super("setup", CommandType.ALL, Permissions.MANAGE.getPermission(), "Toggle Setup mode for Bungee");
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(ISender sender, String s, String[] strings) {
        boolean setupAllowed = plugin.isSetupAllowed();
        if (setupAllowed) {
            plugin.setSetupAllowed(false);
        } else {
            plugin.setSetupAllowed(true);
        }
        String msg = !setupAllowed ? "§aSet-up is now Allowed" : "§cSet-up is now Forbidden";
        sender.sendMessage(msg);
        return true;
    }
}