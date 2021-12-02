package me.wikyfg.ane.events;


import me.wikyfg.ane.ANEMain;
import me.wikyfg.ane.files.Files;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;


public class DeathEvent implements Listener {

    private ANEMain main;

    public DeathEvent(ANEMain main){
        this.main = main;
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getEntity();

        System.out.println(p.getName() + " ha muerto.");
        String coords = p.getLocation().getX() + "," + p.getLocation().getY() + "," + p.getLocation().getZ() + "," + p.getLocation().getWorld().getName();
        Files.userdata.set(p.getName() + ".back", coords);
        main.files.saveFiles();
    }

}
