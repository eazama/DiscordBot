/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytt.dijidori.discordbot.command.admin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import ytt.dijidori.discordbot.command.AbstractCommand;
import ytt.dijidori.discordbot.core.CommandCore;
import ytt.dijidori.discordbot.core.Core;
import ytt.dijidori.discordbot.core.EventList;
import ytt.dijidori.discordbot.core.RoleCore;

/**
 *
 * @author Eric
 */
public class EnableCommand extends AbstractCommand {

    @Override
    public String getKeyword() {
        return "Enable";
    }

    @Override
    public void runCommand(MessageReceivedEvent e) {
        String[] msgArr = e.getMessage().getContent().split(" ");
        if (!msgArr[1].equalsIgnoreCase("True") && !msgArr[1].equalsIgnoreCase("False")) {
            e.getChannel().sendMessage(getHelp());
            return;
        }
        Set<String> msgWrd = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        msgWrd.addAll(Arrays.asList(e.getMessage().getContent().split(" ")));
        if (msgWrd.size() < 3) {
            e.getChannel().sendMessage(getHelp());
        }

        boolean b = Boolean.parseBoolean(msgArr[1]);
        EventList list = Core.getInstance().getEventList("General");
        CommandCore core = CommandCore.getCore();
        for (AbstractCommand ev : list) {
            if (msgWrd.contains(ev.getKeyword())) {
                core.setEnabled(ev.getKeyword(), b);
                e.getChannel().sendMessage(ev.getKeyword() + " is now " + (b ? "enabled" : "disabled"));
            }
        }
    }

    @Override
    public String getHelp() {
        return "blub";
    }

}
