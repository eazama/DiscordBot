/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytt.dijidori.discordbot.core;

import java.util.TreeSet;
import ytt.dijidori.discordbot.command.AbstractCommand;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

/**
 *
 * @author Eric
 */
public class EventList extends TreeSet<AbstractCommand> {

    protected String commandPrefix;

    public EventList(String pre) {
        commandPrefix = pre;
    }

    public String getCommandPrefix() {
        return commandPrefix;
    }

    public void setCommandPrefix(String pre) {
        commandPrefix = pre;
    }

    public void runEvent(MessageReceivedEvent e) {
        if(e.getGuild() != null && GuildCore.getCore().isMuted(e.getGuild().getId())){
            System.out.println("guild is blacklisted");
            return;
        }
        if(e.getTextChannel() != null && ChannelCore.getCore().isMuted(e.getTextChannel().getId())){
            System.out.println("channel is blacklisted");
            return;
        }
        if(RoleCore.getCore().isBlacklisted(e.getAuthor().getId())){
            //System.out.println("user is blacklisted");
            return;
        }
        String[] msgWrd = e.getMessage().getContent().split(" ");
        if (!msgWrd[0].startsWith(commandPrefix)) {
            return;
        }
        for (AbstractCommand cmd : this) {
            if(!CommandCore.getCore().isEnabled(cmd.getKeyword())){
                continue;
            }
            if (msgWrd[0].equalsIgnoreCase(commandPrefix + cmd.getKeyword())) {
                cmd.runCommand(e);
            }
        }
    }
}
