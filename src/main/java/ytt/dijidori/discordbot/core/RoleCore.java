/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytt.dijidori.discordbot.core;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.apache.commons.configuration.PropertiesConfiguration;
import ytt.dijidori.discordbot.core.Core;
import ytt.dijidori.discordbot.core.ConfigCore;
import ytt.dijidori.discordbot.core.UserCore;

/**
 *
 * @author Eric
 */
public class RoleCore {

    private static RoleCore core;
    private BidiMap<String, Integer> roles;
    
    private static final String KEY_ROLE = "role";
    

    private RoleCore() {
        System.out.println("Loading Roles");
        PropertiesConfiguration prop = ConfigCore.getCore().getConfig(KEY_ROLE);
        roles = new TreeBidiMap<>();
        Iterator<String> i = prop.getKeys();
        while(i.hasNext()){
            String s = i.next();
            try {
                roles.put(prop.getString(s).toUpperCase(), Integer.parseInt(s));
            } catch (Exception ex) {
                System.out.println("Goof in access properties file");
                System.out.println(s + ": " + prop.getString(s));
                System.out.println(ex.getMessage());
            }
        }
    }

    public static RoleCore getCore() {
        if (core == null) {
            core = new RoleCore();
        }
        return core;
    }

    public void setRole(String perm, String userID){
        setRole(roles.get(perm.toUpperCase()), userID);
    }
    
    public void setRole(int perm, String userID) {
        UserCore.getCore().setProperty(userID, KEY_ROLE, perm);
    }

    public int getRoleValue(String userID) {
        String ret = UserCore.getCore().getProperty(userID, KEY_ROLE, "0");
        try{
            return Integer.parseInt(ret);
        } catch (Exception ex) {
            System.out.println("Error in PermissionsInfo.getRoleValue");
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    public String getRole(String userID) {
        return roles.getKey(getRoleValue(userID));
    }
    
    public boolean isValidRole(String val){
        return roles.containsKey(val.toUpperCase());
    }
    
    public boolean isValidRole(int val){
        return roles.containsValue(val);
    }

    public boolean isBlacklisted(String userID) {
        return 4 == getRoleValue(userID) / 100;
    }

    public boolean isUser(String userID) {
        return 3 == getRoleValue(userID) / 100;
    }

    public boolean isAdmin(String userID) {
        return 2 == getRoleValue(userID) / 100;
    }

    public boolean isOwner(String userID) {
        return 1 == getRoleValue(userID) / 100;
    }

    public boolean isUnknown(String userID) {
        return 0 == getRoleValue(userID) / 100;
    }

}
