/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytt.dijidori.discordbot.core;

import ytt.dijidori.discordbot.command.AbstractCommand;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

/**
 *
 * @author Eric
 */
public class AdminList extends EventList {

    public AdminList(String pre) {
        super(pre);
    }

    @Override
    public void runEvent(MessageReceivedEvent e) {

        String[] msgWrd = e.getMessage().getContent().split(" ");
        if (!msgWrd[0].startsWith(commandPrefix)) {
            return;
        }

        if (!RoleCore.getCore().isAdmin(e.getAuthor().getId())
                && !RoleCore.getCore().isOwner(e.getAuthor().getId())
                && !Core.getInstance().isOwner(e.getAuthor().getId())) {
            System.out.println("User is not an Admin");
            return;
        }

        for (AbstractCommand cmd : this) {
            if (msgWrd[0].equalsIgnoreCase(commandPrefix + cmd.getKeyword())) {
                System.out.println("HERPDERP");
                cmd.runCommand(e);
            }
        }
    }
}
