package me.wikyfg.ane.commands;

import me.wikyfg.ane.ANEMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Day implements CommandExecutor {

    private ANEMain main;

    public Day(ANEMain main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (!p.isOp()){
            p.sendMessage(ChatColor.RED + "Lo siento, no tienes permisos para hacer esto.");
            return false;
        }

        if (args.length > 0){
            p.sendMessage(ChatColor.RED + "Te has confundido de argumentos, prueba a poner /day.");
            return false;
        }
        p.sendMessage(ChatColor.GOLD + "Tiempo cambiado a de día.");
        p.getWorld().setTime(1000);
        return false;
    }

}
