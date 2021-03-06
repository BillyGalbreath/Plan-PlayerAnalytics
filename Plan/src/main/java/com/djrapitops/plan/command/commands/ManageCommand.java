package main.java.com.djrapitops.plan.command.commands;

import com.djrapitops.plugin.command.CommandType;
import com.djrapitops.plugin.command.TreeCommand;
import main.java.com.djrapitops.plan.Plan;
import main.java.com.djrapitops.plan.command.commands.manage.*;
import main.java.com.djrapitops.plan.settings.Permissions;
import main.java.com.djrapitops.plan.settings.locale.Locale;
import main.java.com.djrapitops.plan.settings.locale.Msg;

/**
 * This command is used to manage the database of the plugin.
 * <p>
 * No arguments will run ManageHelpCommand. Contains subcommands.
 *
 * @author Rsl1122
 * @since 2.3.0
 */
public class ManageCommand extends TreeCommand<Plan> {

    /**
     * Subcommand Constructor.
     *
     * @param plugin Current instance of Plan
     */
    public ManageCommand(Plan plugin) {
        super(plugin, "manage,m", CommandType.CONSOLE, Permissions.MANAGE.getPermission(), Locale.get(Msg.CMD_USG_MANAGE).toString(), "plan m");
        super.setColorScheme(plugin.getColorScheme());
    }

    @Override
    public String[] addHelp() {
        return Locale.get(Msg.CMD_HELP_MANAGE).toArray();
    }

    @Override
    public void addCommands() {
        add(
                new ManageMoveCommand(plugin),
                new ManageHotswapCommand(plugin),
                new ManageBackupCommand(plugin),
                new ManageRestoreCommand(plugin),
                new ManageImportCommand(plugin),
                new ManageRemoveCommand(plugin),
                new ManageClearCommand(plugin),
                new ManageSetupCommand(plugin),
                new ManageDisableCommand()
        );
    }
}
