/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytt.dijidori.discordbot.command;

import java.util.HashSet;
import java.util.List;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import ytt.dijidori.discordbot.command.AbstractCommand;
import ytt.dijidori.discordbot.info.AbstractInfoModule;

/**
 *
 * @author Eric
 */
public class InfoCommand extends AbstractCommand {
    
    private static HashSet<AbstractInfoModule> infoMods = new HashSet<AbstractInfoModule>();

    @Override
    public String getKeyword() {
        return "Info";
    }

    @Override
    public void runCommand(MessageReceivedEvent e) {
        List<User> users = e.getMessage().getMentionedUsers();
        if (users.size() < 1) {
            e.getChannel().sendMessage("Please mention a user");
        }
        String retString;
        for (User u : users) {
            retString = "User Name: " + u.getUsername();
            retString += "\nID: " + u.getId();
            retString += "\nOnline Status: " + u.getOnlineStatus().name();
            retString += "\nCurrently Playing: " + (u.getCurrentGame() == null ? "" : u.getCurrentGame());
            for(AbstractInfoModule mod : infoMods){
                retString += "\n" + mod.getInfo(u);
            }
            e.getChannel().sendMessage(retString);
        }
    }
    
    public static void registerInfoModule(AbstractInfoModule mod){
        infoMods.add(mod);
    }

    @Override
    public String getHelp() {
        return "info help string";
    }

}
