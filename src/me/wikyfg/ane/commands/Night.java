package me.wikyfg.ane.commands;

import me.wikyfg.ane.ANEMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Night implements CommandExecutor {

    private ANEMain main;

    public Night(ANEMain main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (args.length > 0){
            p.sendMessage(ChatColor.RED + "Te has confundido de argumentos, prueba a poner /night.");
            return false;
        }
        p.sendMessage(ChatColor.GOLD + "Tiempo cambiado a de noche.");
        p.getWorld().setTime(18000);
        return false;
    }

}
