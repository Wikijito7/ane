package me.wikyfg.ane.commands;

import me.wikyfg.ane.ANEMain;
import me.wikyfg.ane.files.Files;
import me.wikyfg.ane.utils.Numeric;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teleport implements CommandExecutor {

    private final ANEMain main;

    public Teleport(ANEMain main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (!p.isOp()) {
            p.sendMessage(ChatColor.RED + "Lo siento, no tienes permisos para hacer esto.");
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("tp") ||
                cmd.getName().equalsIgnoreCase("teleport")) {
            if (args.length == 0) {
                p.sendMessage(ChatColor.RED + "Illo, creo que te faltan argumentos. /tp <nombre>, /tp <nombre> <nombre> ó /tp <x> <y> <z>");
            }

            if (args.length == 1) {
                Player pl = Bukkit.getPlayer(args[0]);
                if (pl != null) {
                    teleport(p, pl, p);
                    if (p.getGameMode() == GameMode.SURVIVAL ||
                            pl.isOp()) {
                        pl.sendMessage(ChatColor.GRAY + p.getName() + " se ha teletransportado a tu localización.");
                    }
                    p.sendMessage(ChatColor.GRAY + "Teletransportandote a localización de " + pl.getName() + ".");

                } else {
                    p.sendMessage(ChatColor.RED + "Ese usuario no existe o está desconectado.");
                }

            }

            if (args.length == 2) {
                Player pl1 = Bukkit.getPlayer(args[0]);
                Player pl2 = Bukkit.getPlayer(args[1]);
                if (pl1 != null &&
                        pl2 != null) {
                    teleport(pl1, pl2, pl1);
                    pl1.sendMessage(ChatColor.GRAY + "Has sido teletransportado a la localización de " + pl2.getName() + ".");
                    if (pl2.isOp() ||
                            pl1.getGameMode() != GameMode.SPECTATOR) {
                        pl2.sendMessage(ChatColor.GRAY + "Teletransportado " + pl1.getName() + " a tu localización.");
                    }
                }

            } else {
                p.sendMessage(ChatColor.RED + "Ese usuario no existe o está desconectado.");
            }

        }

        if (args.length == 3) {
            if (!Numeric.isNumeric(args[0]) && !Numeric.isNumeric(args[1]) &&
                    !Numeric.isNumeric(args[2])) {
                p.sendMessage(ChatColor.RED + "Los valores introducidos no son números.");
                return true;
            }

            Location tp = new Location(p.getWorld(), Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
            Files.userdata.set(p.getName() + ".back", p.getLocation().getX() + "," + p.getLocation().getY() + "," + p.getLocation().getZ() + "," + p.getLocation().getWorld().getName());
            if (main.files != null) {
                main.files.saveFiles();
            }
            p.teleport(tp);
            p.sendMessage(ChatColor.GRAY + "Has sido teletransportado a " + Double.parseDouble(args[0]) + " " + Double.parseDouble(args[1]) + " " + Double.parseDouble(args[2]) + " en el mundo" + p.getWorld());
        }

        return true;
    }

    private void teleport(Player p, Player playerTo, Player playerTeleported) {
        Files.userdata.set(p.getName() + ".back", p.getLocation().getX() + "," + p.getLocation().getY() + "," + p.getLocation().getZ() + "," + p.getLocation().getWorld().getName());
        if (main.files != null) {
            main.files.saveFiles();
        }
        playerTeleported.teleport(playerTo.getLocation());
    }
}
