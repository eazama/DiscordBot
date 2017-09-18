/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytt.dijidori.discordbot.core;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import ytt.dijidori.discordbot.core.ConfigCore;

/**
 *
 * @author Eric
 */
public class CommandCore {

    private PropertiesConfiguration commandConfigs;
    private static CommandCore core;
    private static final String CONFIG_NAME = "commands";
    
    public static final String KEY_VISIBLE = ".visible";
    public static final String KEY_ENABLED = ".enabled";
    
    private CommandCore() {
        commandConfigs = ConfigCore.getCore().getConfig(CONFIG_NAME);
        //commandConfigs = ConfigCore.loadConfiguration("properties/cmd.txt");
    }

    public static CommandCore getCore() {
        if (core == null) {
            core = new CommandCore();
        }
        return core;
    }

    public String getConfig(String keyword, String config) {
        return commandConfigs.getString(keyword + "." + config);
    }

    public boolean isVisible(String keyword) {
        return commandConfigs.getBoolean(keyword + KEY_VISIBLE, true);
    }

    public void setVisible(String keyword, boolean bool) {
        commandConfigs.setProperty(keyword + KEY_VISIBLE, bool);
    }

    public boolean isEnabled(String keyword) {
        return commandConfigs.getBoolean(keyword+KEY_ENABLED, true);
    }

    public void setEnabled(String keyword, boolean bool) {
        commandConfigs.setProperty(keyword + KEY_ENABLED, bool);
    }
}
