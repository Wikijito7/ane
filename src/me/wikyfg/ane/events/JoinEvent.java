package me.wikyfg.ane.events;

import me.wikyfg.ane.ANEMain;
import me.wikyfg.ane.files.Files;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class JoinEvent implements Listener {
    private ANEMain main;

    public JoinEvent(ANEMain main){
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(Files.userdata.get(p.getName()) == null){
            Files.userdata.set(p.getName() + ".homes.home", "none");
            Files.userdata.set(p.getName() + ".back", p.getLocation().getX() + "," + p.getLocation().getY() + "," + p.getLocation().getZ() + "," + p.getLocation().getWorld().getName());
            main.files.saveFiles();
        }

    }

}
