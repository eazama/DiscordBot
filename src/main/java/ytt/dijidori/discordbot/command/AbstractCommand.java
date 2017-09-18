/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytt.dijidori.discordbot.command;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

/**
 *
 * @author Eric
 */
public abstract class AbstractCommand implements Comparable<AbstractCommand>{
    public abstract String getKeyword();
    public abstract String getHelp();
    public abstract void runCommand(MessageReceivedEvent e);
        @Override
    public final int compareTo(AbstractCommand nMod){
        return getKeyword().compareToIgnoreCase(nMod.getKeyword());
    }

}
