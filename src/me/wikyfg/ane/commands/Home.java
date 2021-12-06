package me.wikyfg.ane.commands;

import me.wikyfg.ane.ANEMain;
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

public class Home implements CommandExecutor {
    private final ANEMain main;

    public Home(ANEMain main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("home")) {
            if (args.length == 0) {
                if (p.getGameMode() == GameMode.SURVIVAL &&
                        !CommandExperienceUtils.Companion.onCommandExecution(p, 1)) {
                    return true;
                }

                if (!Files.userdata.get(p.getName() + ".homes.home").equals("none")) {
                    Files.userdata.set(p.getName() + ".back", p.getLocation().getX() + "," + p.getLocation().getY() + "," + p.getLocation().getZ() + "," + p.getLocation().getWorld().getName());
                    String[] coord = Files.userdata.get(p.getName() + ".homes.home").toString().split(",");
                    Location tp = new Location(Bukkit.getWorld(coord[3]), Double.parseDouble(coord[0]), Double.parseDouble(coord[1]), Double.parseDouble(coord[2]));
                    main.files.saveFiles();
                    p.sendMessage(ChatColor.GREEN + "Teletransportándote a tu casa");
                    p.teleport(tp);
                } else {
                    p.sendMessage(ChatColor.RED + "Amigo mío, no tienes ninguna punto puesto. Puedes ponerlo con /sethome.");
                }
            }

            if (args.length == 1) {
                if (p.getGameMode() == GameMode.SURVIVAL &&
                        !CommandExperienceUtils.Companion.onCommandExecution(p, 1)) {
                    return true;
                }

                System.out.println(Files.userdata.get(p.getName() + ".homes." + args[0]));
                if (Files.userdata.contains(p.getName() + ".homes." + args[0].toLowerCase())) {
                    Files.userdata.set(p.getName() + ".back", p.getLocation().getX() + "," + p.getLocation().getY() + "," + p.getLocation().getZ() + "," + p.getLocation().getWorld().getName());
                    String[] coord = Files.userdata.get(p.getName() + ".homes." + args[0]).toString().split(",");
                    try {
                        Location tp = new Location(Bukkit.getWorld(coord[3]), Double.parseDouble(coord[0]), Double.parseDouble(coord[1]), Double.parseDouble(coord[2]));
                        p.sendMessage(ChatColor.GREEN + "Teletransportándote a tu casa");
                        p.teleport(tp);

                    } catch (IndexOutOfBoundsException e) {
                        p.sendMessage(ChatColor.RED + "Comprueba que esa casa exista, /homelist. (" + args[0] + ")");
                    }

                } else {
                    p.sendMessage(ChatColor.RED + "Amigo mío, no tienes ninguna punto puesto. Puedes ponerlo con /sethome.(Argumento usado: " + args[0] + ")");
                }
            }

            if (args.length > 1) {
                p.sendMessage(ChatColor.RED + "Tas pasao de argumentos, prueba a poner /home ó /home <nombre>.");
            }
        }
        return true;
    }
}
