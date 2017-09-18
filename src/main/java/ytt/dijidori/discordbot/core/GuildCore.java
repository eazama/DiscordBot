/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytt.dijidori.discordbot.core;

import org.apache.commons.configuration.PropertiesConfiguration;
import ytt.dijidori.discordbot.core.ConfigCore;

/**
 *
 * @author Eric
 */
public class GuildCore {

    private PropertiesConfiguration guildConfig;
    private static GuildCore core;
    private static final String CONFIG_NAME = "guilds";

    private GuildCore() {
        guildConfig = ConfigCore.getCore().getConfig(CONFIG_NAME);
        //guildConfig = ConfigCore.loadConfiguration("properties/gld.txt");
    }

    public static GuildCore getCore() {
        if (core == null) {
            core = new GuildCore();
        }
        return core;
    }

    public boolean isMuted(String guildID) {
        return guildConfig.getBoolean(guildID+".muted", false);
        //return Boolean.parseBoolean(ConfigCore.getConfig(guildConfig, guildID + ".muted", "False"));
    }

    public void setMuted(String guildID, boolean bool) {
        guildConfig.setProperty(guildID+".muted", bool);
        //ConfigCore.setConfig(guildConfig, guildID + ".muted", Boolean.toString(bool));
    }
}
