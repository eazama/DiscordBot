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

/**
 *
 * @author Eric
 */
public class ConfigCore {

    protected static final String FOLDER = "config";
    private static ConfigCore core;
    private Map<String, PropertiesConfiguration> map = new HashMap<>();
    
    private ConfigCore(){
        
    }

    public static ConfigCore getCore(){
        if(core == null){
            core = new ConfigCore();
        }
        return core;
    }

    public PropertiesConfiguration getConfig(String config) {
        if (map.containsKey(config)) {
            return map.get(config);
        } else {
            PropertiesConfiguration retVal = loadConfiguration(config);
            map.put(config, retVal);
            return retVal;
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
}
