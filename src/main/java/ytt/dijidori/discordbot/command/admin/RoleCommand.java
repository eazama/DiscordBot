/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytt.dijidori.discordbot.command.admin;

import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import ytt.dijidori.discordbot.command.AbstractCommand;
import ytt.dijidori.discordbot.core.RoleCore;
import ytt.dijidori.discordbot.core.UserCore;

/**
 *
 * @author Eric
 */
public class RoleCommand extends AbstractCommand {

    @Override
    public String getKeyword() {
        return "Role";
    }

    @Override
    public void runCommand(MessageReceivedEvent e) {
        System.out.println("Role Triggered");
        RoleCore core = RoleCore.getCore();
        String[] msgWrd = e.getMessage().getContent().split(" ");
        System.out.println("User Role is " + msgWrd[1]);
        if (msgWrd.length < 3) {
            e.getChannel().sendMessage("Usage: [PermissionLevel] MentionedUsers");
            return;
        }

        if (!core.isValidRole(msgWrd[1])) {
            e.getChannel().sendMessage(msgWrd[1] + " is not a valid permission level");
            return;
        }

        for (User u : e.getMessage().getMentionedUsers()) {
            core.setRole(msgWrd[1], u.getId());
            UserCore.getCore().saveAll();
        }
    }

    @Override
    public String getHelp() {
        return "blub";
    }

}
