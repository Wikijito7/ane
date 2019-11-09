package me.wikyfg.ane;
import me.wikyfg.ane.commands.*;
import me.wikyfg.ane.events.DeathEvent;
import me.wikyfg.ane.events.JoinEvent;
import me.wikyfg.ane.events.PlayerEvent;
import me.wikyfg.ane.files.Files;
import me.wikyfg.ane.utils.Utils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class ANEMain extends JavaPlugin {
    public Files files;
    private PluginManager plugin = this.getServer().getPluginManager();
    public Economy eco;

    @Override
    public void onEnable() {

        Plugin vault = getServer().getPluginManager().getPlugin( "Vault" );

        if( vault == null )
        {
            return;
        }

        getServer().getServicesManager().register( Economy.class, new Utils( this ), this, ServicePriority.Highest );
        eco = getServer().getServicesManager().getRegistration(Economy.class).getProvider();

        System.out.println("ANECommand has been enabled");
        files = new Files();
        loadEvents();
        files.setupFiles();
        files.saveFiles();
        loadCommands();
    }

    public void onDisable(){
        System.out.println("ANECommand has been disabled");
        files.saveFiles();
    }

    private void loadCommands(){
        getCommand("tp").setExecutor(new Teleport(this));
        getCommand("ANE").setExecutor(new ANECommand(this));
        getCommand("back").setExecutor(new Back());
        getCommand("home").setExecutor(new Home(this));
        getCommand("sethome").setExecutor(new Sethome(this));
        getCommand("spawn").setExecutor(new Spawn(this));
        getCommand("homelist").setExecutor(new HomeList());
        getCommand("pagar").setExecutor(new PayCMD(this));
        getCommand("balance").setExecutor(new Balance(this));
        getCommand("jail").setExecutor(new Jail(this));
        getCommand("day").setExecutor(new Day(this));
        getCommand("night").setExecutor(new Night(this));
        getCommand("rain").setExecutor(new Rain(this));
        getCommand("sun").setExecutor(new Sun(this));
        getCommand("savexp").setExecutor(new Saveexp(this));
    }

    private void loadEvents(){
        plugin.registerEvents(new JoinEvent(this), this);
        plugin.registerEvents(new DeathEvent(this), this);
        plugin.registerEvents(new PlayerEvent(this), this);
    }
}
