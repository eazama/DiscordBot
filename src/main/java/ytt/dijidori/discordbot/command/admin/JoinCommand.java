/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytt.dijidori.discordbot.command.admin;

import net.dv8tion.jda.entities.impl.JDAImpl;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import org.json.JSONObject;
import ytt.dijidori.discordbot.command.AbstractCommand;
import ytt.dijidori.discordbot.core.ConfigCore;
import ytt.dijidori.discordbot.core.Core;

/**
 *
 * @author Eric
 */
public class JoinCommand extends AbstractCommand {

    private static final String DISCORD_JOIN_PREFIX = "https://discord.gg/";
    private static final String DISCORD_JOIN_URL = "https://discordapp.com/oauth2/authorize?&client_id=%s&scope=bot";
    private static String DISCORD_CLIENT_ID;
    
    public JoinCommand(){
        super();
        DISCORD_CLIENT_ID = ConfigCore.getCore().getConfig("properties").getString("client.id");
    }
    
    @Override
    public String getKeyword() {
        return "Join";
    }

    @Override
    public void runCommand(MessageReceivedEvent e) {
        e.getMessage().getChannel().sendMessage(String.format(DISCORD_JOIN_URL, DISCORD_CLIENT_ID));
    }

    @Override
    public String getHelp() {
        return "blub";
    }

}
