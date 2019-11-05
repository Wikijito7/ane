package me.wikyfg.ane.commands;

import me.wikyfg.ane.ANEMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Sun implements CommandExecutor {

    private ANEMain main;

    public Sun(ANEMain main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (args.length > 0){
            p.sendMessage(ChatColor.RED + "Te has confundido de argumentos, prueba a poner /sun.");
            return false;
        }
        p.getWorld().setStorm(false);
        p.sendMessage(ChatColor.GOLD + "Clima cambiado a soleado.");
        p.getWorld().setWeatherDuration(20000);
        return false;
    }

}
