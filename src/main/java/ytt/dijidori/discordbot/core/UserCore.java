/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytt.dijidori.discordbot.core;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.configuration.PropertiesConfiguration;
import ytt.dijidori.discordbot.core.RoleCore;
import ytt.dijidori.discordbot.core.ConfigCore;

/**
 *
 * @author Eric
 */
public class UserCore {

    private static UserCore core;
    private static final String FOLDER = "users";
    private Map<String, PropertiesConfiguration> map = new HashMap<>();

    private UserCore() {
    }

    public static UserCore getCore() {
        if (core == null) {
            core = new UserCore();
        }
        return core;
    }

    public PropertiesConfiguration getUser(String userID) {
        if (map.containsKey(userID)) {
            return map.get(userID);
        } else {
            PropertiesConfiguration config = loadConfiguration(userID);
            map.put(userID, config);
            return config;
        }
    }

    public void saveAll() {
        for (PropertiesConfiguration config : map.values()) {
            try {
                config.save();
            } catch (Exception ex) {
                System.err.println("Error saving config " + config.getFileName());
            }
        }
    }

    private static PropertiesConfiguration loadConfiguration(String fileName) {
        PropertiesConfiguration config = null;
        try {
            config = new PropertiesConfiguration(new File(FOLDER + "/" + fileName));
        } catch (Exception ex) {
            System.err.println("Error loading configuration " + fileName);
            ex.printStackTrace();
            System.exit(1);
        }
        return config;
    }
    
    public void setProperty(String userID, String prop, Object value){
        getUser(userID).setProperty(prop, value);
    }
    
    public String getProperty(String userID, String prop){
        return getProperty(userID, prop, null);
    }
    
    public String getProperty(String userID, String prop, String value){
        PropertiesConfiguration u = getUser(userID);
        Object o = u.getProperty(prop);
        return null==o?value:o.toString();
//return u.getString(prop, value);
    }

}
