/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytt.dijidori.discordbot.info;

import net.dv8tion.jda.entities.User;
import ytt.dijidori.discordbot.command.AbstractCommand;

/**
 *
 * @author Eric
 */
public abstract class AbstractInfoModule implements Comparable<AbstractCommand> {

    public abstract String getModuleName();
    public abstract String getInfo(User u);

    @Override
    public final int compareTo(AbstractCommand nMod) {
        return getModuleName().compareToIgnoreCase(nMod.getKeyword());
    }
}
