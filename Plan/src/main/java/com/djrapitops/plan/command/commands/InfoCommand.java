package main.java.com.djrapitops.plan.command.commands;

import main.java.com.djrapitops.plan.Phrase;
import main.java.com.djrapitops.plan.Plan;
import main.java.com.djrapitops.plan.command.CommandType;
import main.java.com.djrapitops.plan.command.SubCommand;
import main.java.com.djrapitops.plan.utilities.MiscUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Rsl1122
 */
public class InfoCommand extends SubCommand {

    private Plan plugin;

    /**
     * Subcommand Constructor.
     *
     * @param plugin Current instance of Plan
     */
    public InfoCommand(Plan plugin) {
        super("info", "plan.info", Phrase.CMD_USG_INFO+"", CommandType.CONSOLE, "");

        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        ChatColor tColor = Phrase.COLOR_SEC.color();

        String[] messages = {
            Phrase.CMD_INFO_HEADER+"",
            Phrase.CMD_INFO_VERSION.parse(plugin.getDescription().getVersion()),
            Phrase.CMD_BALL.toString() + tColor + " " + MiscUtils.checkVersion(),
            Phrase.CMD_MANAGE_STATUS_ACTIVE_DB.parse(plugin.getDB().getConfigName()),
            Phrase.CMD_FOOTER+""
        };
        sender.sendMessage(messages);
        return true;
    }

}
