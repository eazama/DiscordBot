/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytt.dijidori.discordbot.plugin;

import java.util.ServiceLoader;
import java.util.Iterator;
import net.dv8tion.jda.JDA;

/**
 *
 * @author Eric
 */
public class PluginService {
    
    private static PluginService service;
    private ServiceLoader<DiscordBotPlugin> loader;
    private static JDA api;
    
    private PluginService(){
        loader = ServiceLoader.load(DiscordBotPlugin.class);
    }
    
    public static synchronized PluginService getInstance(JDA a){
        if(service == null){
            service = new PluginService();
        }
        api = a;
        return service;
    }
    
    public void loadPlugins(){
        Iterator<DiscordBotPlugin> plugins = loader.iterator();
        while(plugins.hasNext()){
            plugins.next().init();
        }
    }
    /*
    public boolean runCommand(UserChatEvent e){
        if(!e.getMsg().getMessage().startsWith(prop.getProperty("command.prefix"))){
            System.out.println("incorrect prefix");
            return false;
        }
        Iterator<DiscordCommand> commands = loader.iterator();
        System.out.println(commands.hasNext());
        while(commands.hasNext()){
            if(commands.next().runCommand(e, api)){
                return true;
            }
        }
        return false;
    }*/
}
