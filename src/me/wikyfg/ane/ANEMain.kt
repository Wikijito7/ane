package me.wikyfg.ane;
import me.wikyfg.ane.commands.*;
import me.wikyfg.ane.events.DeathEvent;
import me.wikyfg.ane.events.JoinEvent;
import me.wikyfg.ane.events.PlayerEvent;
import me.wikyfg.ane.files.Files;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ANEMain extends JavaPlugin {
    public Files files;
    private PluginManager plugin = this.getServer().getPluginManager();

    @Override
    public void onEnable() {
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
        getCommand("setspawn").setExecutor(new Setspawn(this));
        getCommand("do").setExecutor(new Do(this));
        getCommand("me").setExecutor(new Me(this));
        getCommand("entorno").setExecutor(new Entorno(this));
    }

    private void loadEvents(){
        plugin.registerEvents(new JoinEvent(this), this);
        plugin.registerEvents(new DeathEvent(this), this);
        plugin.registerEvents(new PlayerEvent(this), this);
    }
}
