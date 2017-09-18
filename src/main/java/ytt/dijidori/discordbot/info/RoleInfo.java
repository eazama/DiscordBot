/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytt.dijidori.discordbot.info;

import net.dv8tion.jda.entities.User;
import ytt.dijidori.discordbot.core.RoleCore;

/**
 *
 * @author Eric
 */
public class RoleInfo extends AbstractInfoModule {

    @Override
    public String getModuleName() {
        return "permissions info";
    }

    @Override
    public String getInfo(User u) {
        return "Permission Level: " + RoleCore.getCore().getRole(u.getId()) ;
    }
}
