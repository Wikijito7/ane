package me.wikyfg.ane.commands;

import me.wikyfg.ane.ANEMain;
import me.wikyfg.ane.files.Files;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Sethome implements CommandExecutor {
    private final ANEMain main;

    public Sethome(ANEMain main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("sethome")) {
            if (args.length == 0) {
                p.sendMessage(ChatColor.GREEN + "Punto establecido.");
                Files.userdata.set(p.getName() + ".homes.home", p.getLocation().getX() + ", " + p.getLocation().getY() + ", " + p.getLocation().getZ() + "," + p.getLocation().getWorld().getName());
                main.files.saveFiles();
            }
            if (args.length == 1) {
                p.sendMessage(ChatColor.GREEN + "Punto establecido.");
                Files.userdata.set(p.getName() + ".homes." + args[0].toLowerCase(), p.getLocation().getX() + ", " + p.getLocation().getY() + ", " + p.getLocation().getZ() + "," + p.getLocation().getWorld().getName());
                main.files.saveFiles();
            }
            if (args.length > 1) {
                p.sendMessage(ChatColor.RED + "Te has pasado de argumentos. Prueba a escribir /sethome ó /sethome <nombre>.");
            }
        }
        return true;
    }
}