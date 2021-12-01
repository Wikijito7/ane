package me.wikyfg.ane.commands;

import me.wikyfg.ane.ANEMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Me implements CommandExecutor {

    private ANEMain main;

    public Me(ANEMain main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (args.length > 0){
            Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "* " + p.getName() + " " + String.join(" ", args));
            return false;
        } else {
            p.sendMessage(ChatColor.RED + "Creo que te has confundido, prueba con /me <mensaje>.");
            return false;
        }
    }

}
