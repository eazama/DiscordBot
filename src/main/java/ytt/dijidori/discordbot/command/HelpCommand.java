/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytt.dijidori.discordbot.command;

import ytt.dijidori.discordbot.core.CommandCore;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import ytt.dijidori.discordbot.core.Core;
import ytt.dijidori.discordbot.core.EventList;

/**
 *
 * @author Eric
 */
public class HelpCommand extends AbstractCommand {
    
    private final String listName;
    public HelpCommand(String listName){
        super();
        this.listName = listName;
    }

    @Override
    public String getHelp() {
        return "You already seem to know how to use this";
    }

    @Override
    public String getKeyword() {
        return "Help";
    }

    @Override
    public void runCommand(MessageReceivedEvent e) {
        String[] msgWrd = e.getMessage().getContent().split(" ");
        switch (msgWrd.length) {
            case 1:
                CommandCore core = CommandCore.getCore();
                EventList el = Core.getInstance().getEventList(listName);
                String retString = "Command Trigger: " + el.getCommandPrefix() + "\n";
                retString += "Command List: ";
                for (AbstractCommand cmd : el) {
                    if(!core.isVisible(cmd.getKeyword())){
                        continue;
                    }
                    if(!core.isEnabled(cmd.getKeyword())){
                        continue;
                    }
                    retString += cmd.getKeyword() + ", ";
                }   e.getChannel().sendMessage(retString.substring(0, retString.length() - 2));
                break;
            case 2:
                for (AbstractCommand cmd : Core.getInstance().getEventList("General")) {
                    if (cmd.getKeyword().equalsIgnoreCase(msgWrd[1])) {
                        e.getChannel().sendMessage(cmd.getHelp());
                    }
                }   break;
            default:
                e.getChannel().sendMessage("Usage: " + getKeyword() + " [command]");
                break;
        }
    }
}
