/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytt.dijidori.discordbot.command.admin;

import net.dv8tion.jda.entities.impl.JDAImpl;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import ytt.dijidori.discordbot.command.AbstractCommand;
import ytt.dijidori.discordbot.core.Core;

/**
 *
 * @author Eric
 */
public class LeaveCommand extends AbstractCommand {

    private static final String DISCORD_JOIN_PREFIX = "https://discord.gg/";
    
    @Override
    public String getKeyword() {
        return "Leave";
    }

    @Override
    public void runCommand(MessageReceivedEvent e) {
        ((JDAImpl)Core.api).getRequester().delete("https://discordapp.com/api/users/@me/guilds/" + e.getGuild().getId());
    }

    @Override
    public String getHelp() {
        return "blub";
    }

}
