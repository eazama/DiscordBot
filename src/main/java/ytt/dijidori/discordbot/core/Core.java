/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytt.dijidori.discordbot.core;

import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;
import java.util.*;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.events.Event;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import ytt.dijidori.discordbot.plugin.PluginService;
import ytt.dijidori.discordbot.command.AbstractCommand;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationFactory;

/**
 *
 * @author Eric
 */
public class Core {

    private static Core instance;

    private Core() {
    }

    public static Core getInstance() {
        if (instance == null) {
            instance = new Core();
        }
        return instance;
    }

    public static JDA api;
    private Configuration config;
    private Map<String, EventList> eventLists;
    private String userID;
    private HashSet<String> ownerIDs;

    public static void main(String args[]) {
        Core.getInstance().run();
    }

    public void run() {
        //Load Properties
        config = ConfigCore.getCore().getConfig("properties");

        ownerIDs = new HashSet<>(Arrays.asList(config.getStringArray(CoreProps.OWNER_ID)));

        //Register generic event lists
        eventLists = new HashMap<>();
        registerEventList("General", config.getString(CoreProps.COMMAND_PRE));
        eventLists.put("Admin", new AdminList(config.getString(CoreProps.ADMIN_PRE)));

System.out.println(config.getString(CoreProps.BOT_TOKEN));
        //Create API and login
        try {
            api = new JDABuilder().setBotToken(config.getString(CoreProps.BOT_TOKEN))
                    .addListener(new CoreMessageListener())
                    .buildBlocking();
        } catch (IllegalArgumentException e) {
            System.out.println("The config was not populated. Please enter an email and password.");
        } catch (LoginException e) {
            System.out.println("The provided email / password combination was incorrect. Please provide valid details.");
        } catch (InterruptedException e) {
            System.out.println("Interrupt");
        }
        userID = api.getSelfInfo().getId();
        //api.setDebug(true);

        //Load Plugins
        PluginService plugs = PluginService.getInstance(api);
        plugs.loadPlugins();
    }

    public boolean registerEventList(String name, String prefix) {
        if (eventLists.containsKey(name)) {
            System.out.println("EventList " + name + " already exists");
            return false;
        }
        eventLists.put(name, new EventList(prefix));
        return true;
    }

    public boolean registerCommand(String name, AbstractCommand cmd) {
        EventList el = eventLists.get(name);
        if (el == null) {
            System.out.println(name + " is not a valid event list");
            return false;
        }
        return el.add(cmd);
    }

    public EventList getEventList(String name) {
        return eventLists.get(name);
    }

    public boolean isOwner(String id) {
        return ownerIDs.contains(id);
    }

    private class CoreMessageListener implements net.dv8tion.jda.hooks.EventListener {

        @Override
        public void onEvent(Event event) {
            if (event instanceof MessageReceivedEvent) {
                System.out.println(((MessageReceivedEvent) event).getMessage().getContent());
                if (((MessageReceivedEvent) event).getAuthor().getId().equals(Core.getInstance().userID)) {
                    return;
                }
                for (EventList ael : eventLists.values()) {
                    ael.runEvent(((MessageReceivedEvent) event));
                }
            }
        }
    }
}
