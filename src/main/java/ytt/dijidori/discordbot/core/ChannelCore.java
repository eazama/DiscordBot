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
public class ChannelCore {

    private PropertiesConfiguration chanConfig;
    private static ChannelCore core;
    private static final String CONFIG_NAME = "channels";

    private ChannelCore() {
        chanConfig = ConfigCore.getCore().getConfig(CONFIG_NAME);
        //chanConfig = ConfigCore.loadConfiguration("properties/chn.txt");
    }

    public static ChannelCore getCore() {
        if (core == null) {
            core = new ChannelCore();
        }
        return core;
    }

    public boolean isMuted(String chanID) {
        return chanConfig.getBoolean(chanID+".muted", false);
        //return Boolean.parseBoolean(ConfigCore.getConfig(chanConfig, chanID + ".muted", "False"));
    }

    public void setMuted(String chanID, boolean bool) {
        chanConfig.setProperty(chanID+ ".muted", bool);
        //ConfigCore.setConfig(chanConfig, chanID + ".muted", Boolean.toString(bool));
    }
}
