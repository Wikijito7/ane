package me.wikyfg.ane.commands;

import me.wikyfg.ane.ANEMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Rain implements CommandExecutor {

    private ANEMain main;

    public Rain(ANEMain main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (!p.isOp()){
            p.sendMessage(ChatColor.RED + "Lo siento, no tienes permisos para hacer esto.");
            return false;
        }

        if (args.length > 0) {
            p.sendMessage(ChatColor.RED + "Te has confundido de argumentos, prueba a poner /rain.");
            return false;
        }
        p.getWorld().setStorm(true);
        p.sendMessage(ChatColor.GOLD + "Clima cambiado a lluvia.");
        p.getWorld().setWeatherDuration(20000);
        return false;
    }
}