/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytt.dijidori.discordbot.command.admin;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import ytt.dijidori.discordbot.command.AbstractCommand;
import ytt.dijidori.discordbot.core.ChannelCore;
import ytt.dijidori.discordbot.core.CommandCore;
import ytt.dijidori.discordbot.core.Core;
import ytt.dijidori.discordbot.core.EventList;

/**
 *
 * @author Eric
 */
public class MuteChnCommand extends AbstractCommand {

    @Override
    public String getKeyword() {
        return "MuteChn";
    }

    @Override
    public void runCommand(MessageReceivedEvent e) {
        String[] msgArr = e.getMessage().getContent().split(" ");
        if (!msgArr[1].equalsIgnoreCase("True") && !msgArr[1].equalsIgnoreCase("False")) {
            e.getChannel().sendMessage(getHelp());
        }

        boolean b = Boolean.parseBoolean(msgArr[1]);
        EventList list = Core.getInstance().getEventList("General");
        ChannelCore core = ChannelCore.getCore();
        core.setMuted(e.getTextChannel().getId(), b);
        e.getChannel().sendMessage("This channel is now " + (b ? "muted" : "unmuted"));
    }

    @Override
    public String getHelp() {
        return "blub";
    }
}
