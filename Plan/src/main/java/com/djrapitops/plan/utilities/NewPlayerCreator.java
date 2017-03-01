package main.java.com.djrapitops.plan.utilities;

import java.util.Date;
import main.java.com.djrapitops.plan.data.DemographicsData;
import main.java.com.djrapitops.plan.data.UserData;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

/**
 *
 * @author Rsl1122
 */
public class NewPlayerCreator {

    /**
     * Creates a new instance of UserData with default values.
     *
     * @param player Player the UserData is created for.
     * @return a new UserData object
     */
    public static UserData createNewPlayer(Player player) {
        return createNewPlayer((OfflinePlayer) player, player.getGameMode());
    }

    /**
     * Creates a new instance of UserData with default values.
     *
     * @param player OfflinePlayer the UserData is created for.
     * @return a new UserData object
     */
    public static UserData createNewPlayer(OfflinePlayer player) {
        return createNewPlayer(player, GameMode.SURVIVAL);
    }

    /**
     * Creates a new instance of UserData with default values.
     *
     * @param player Player the UserData is created for.
     * @param gm Gamemode set as the starting Gamemode
     * @return a new UserData object
     */
    public static UserData createNewPlayer(OfflinePlayer player, GameMode gm) {
        UserData data = new UserData(player, new DemographicsData());
        data.setLastGamemode(gm);
        data.setLastPlayed(new Date().getTime());
        long zero = Long.parseLong("0");
        data.setPlayTime(zero);
        data.setTimesKicked(0);
        data.setLoginTimes(0);
        data.setLastGmSwapTime(zero);
        data.setDeaths(0);
        data.setMobKills(0);
        return data;
    }

}