/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytt.dijidori.discordbot.plugin;

import ytt.dijidori.discordbot.command.admin.*;
import ytt.dijidori.discordbot.command.InfoCommand;
import ytt.dijidori.discordbot.info.RoleInfo;
import ytt.dijidori.discordbot.core.Core;
import ytt.dijidori.discordbot.command.*;

/**
 *
 * @author Eric
 */
public class CorePlugin implements DiscordBotPlugin {

    @Override
    public void init() {
        Core c = Core.getInstance();
        c.registerCommand("General", new TestCommand());
        c.registerCommand("General", new HelpCommand("General"));
        c.registerCommand("General", new InfoCommand());
        c.registerCommand("Admin", new HelpCommand("Admin"));
        c.registerCommand("Admin", new RoleCommand());
        c.registerCommand("Admin", new JoinCommand());
        c.registerCommand("Admin", new LeaveCommand());
        c.registerCommand("Admin", new EnableCommand());
        c.registerCommand("Admin", new VisibleCommand());
        c.registerCommand("Admin", new MuteChnCommand());
        c.registerCommand("Admin", new MuteGldCommand());
        InfoCommand.registerInfoModule(new RoleInfo());
    }

}
