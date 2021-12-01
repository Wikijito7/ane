package me.wikyfg.ane.commands;

import me.wikyfg.ane.ANEMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Broadcast implements CommandExecutor {

    private ANEMain main;

    public Broadcast(ANEMain main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (args.length > 0){
            Bukkit.broadcastMessage(ChatColor.GOLD + "[ANUNCIO] " + ChatColor.translateAlternateColorCodes('&', String.join(" ", args)));
        } else {
            p.sendMessage(ChatColor.RED + "Creo que te has confundido, prueba con /broadcast <mensaje>.");
        }

        return true;
    }

}
