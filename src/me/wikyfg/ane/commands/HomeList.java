package me.wikyfg.ane.commands;

import me.wikyfg.ane.files.Files;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

public class HomeList implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("homelist")){
            Set<String> homes = Files.userdata.getConfigurationSection(p.getName() + ".homes").getKeys(false);
            p.sendMessage(ChatColor.GOLD + "Tus casas son las siguientes: " + ChatColor.GREEN + homes.toString().replaceAll("]", "").replaceAll("\\[", ""));
        }
        return false;
    }
}
