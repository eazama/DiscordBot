/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytt.dijidori.discordbot.command;

import net.dv8tion.jda.entities.impl.JDAImpl;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import org.json.JSONObject;
import ytt.dijidori.discordbot.core.Core;

/**
 *
 * @author Eric
 */
public class TestCommand extends AbstractCommand {

    @Override
    public String getKeyword() {
        return "test";
    }

    @Override
    public void runCommand(MessageReceivedEvent e) {
        e.getChannel().sendMessage("Test Success");
    }

    @Override
    public String getHelp() {
        return "test help string";
    }

}
