package me.wikyfg.ane.commands;

import me.wikyfg.ane.files.Files;
import me.wikyfg.ane.utils.CommandExperienceUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Back implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("back")) {
            Player p = (Player) sender;
            if (args.length == 0) {
                if (p.getGameMode() == GameMode.SURVIVAL &&
                        !CommandExperienceUtils.Companion.onCommandExecution(p, 1)) {
                    return true;
                }

                p.sendMessage(ChatColor.GOLD + "Teletransportándote a tu última localización");
                if (Files.userdata.contains(p.getName() + ".death") && !Files.userdata.get(p.getName() + ".death").equals("none")) {
                    String[] coord = Files.userdata.get(p.getName() + ".death").toString().split(",");
                    teleport(p, coord);
                    Files.userdata.set(p.getName() + ".death", "none");
                    return true;
                }

                String[] coord = Files.userdata.get(p.getName() + ".back").toString().split(",");
                teleport(p, coord);
            } else {
                p.sendMessage(ChatColor.RED + "Creo que te has pasao un pelín de argumentos, prueba con /back.");
            }

        }
        return true;
    }

    private void teleport(Player p, String[] coord) {
        Location tp = new Location(Bukkit.getWorld(coord[3]), Double.parseDouble(coord[0]), Double.parseDouble(coord[1]), Double.parseDouble(coord[2]));
        Files.userdata.set(p.getName() + ".back", p.getLocation().getX() + "," + p.getLocation().getY() + "," + p.getLocation().getZ() + "," + p.getLocation().getWorld().getName());
        p.teleport(tp);
    }
}
