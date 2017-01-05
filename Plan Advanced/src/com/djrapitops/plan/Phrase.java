package com.djrapitops.plan;

import org.bukkit.ChatColor;

public enum Phrase {
    DATABASE_TYPE_DOES_NOT_EXIST("That database type doesn't exist."),
    DATABASE_FAILURE_DISABLE("Database initialization has failed, disabling Plan."),
    PLANLITE_REG_HOOK("Registered additional hook, passed on to PlanLite: "),
    USERNAME_NOT_VALID("This Player doesn't exist."),
    USERNAME_NOT_SEEN("This Player has not played on this server."),
    USERNAME_NOT_KNOWN("Player not found from the database."),
    COLOR_MAIN(ChatColor.DARK_GREEN),
    COLOR_SEC(ChatColor.GRAY),
    COLOR_TER(ChatColor.DARK_GRAY),
    ERROR_PLANLITE("PlanLite not found, if you're have plugins using PlanAPI v1.6.0 download PlanLite."),
    ERROR_NO_USERNAME("INSPECT-GETNAME\nNo username given, returned empty username.\n");

    private final String text;
    private final ChatColor color;

    private Phrase(final String text) {
        this.text = text;
        this.color = null;
    }

    private Phrase(final ChatColor color) {
        this.color = color;
        this.text = "";
    }

    @Override
    public String toString() {
        return text;
    }

    public ChatColor color() {
        return color;
    }
}