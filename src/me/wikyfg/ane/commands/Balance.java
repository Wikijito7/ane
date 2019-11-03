package me.wikyfg.ane.commands;

import me.wikyfg.ane.ANEMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Balance implements CommandExecutor {

    private ANEMain main;
    public Balance(ANEMain main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("balance") || cmd.getName().equalsIgnoreCase("money")){
            if(args.length == 0){
                p.sendMessage(ChatColor.GREEN + "Tienes " + main.eco.getBalance(p) + " euros, pobre de mierda.");
            }else{
                p.sendMessage(ChatColor.RED + "Illo colega tas pasao d'argumentoh. Prueba a poner /money o /balance.");
            }
        }
        return false;
    }
}
