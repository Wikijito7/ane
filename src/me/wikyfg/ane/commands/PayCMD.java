package me.wikyfg.ane.commands;

import me.wikyfg.ane.ANEMain;
import me.wikyfg.ane.api.ExperienceAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayCMD implements CommandExecutor {

    private final ANEMain main;

    public PayCMD(ANEMain main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("pagar")) {
            Player p = (Player) sender;
            ExperienceAPI expPagador = new ExperienceAPI(p);
            if (args.length != 2) {
                p.sendMessage(ChatColor.RED + "Colega, el comando es /pay <jugador> <cantidad>");
            }

            if (args.length == 2) {
                Player pl = main.getServer().getPlayer(args[0]);
                ExperienceAPI expPagado = new ExperienceAPI(pl);
                if (pl != null) {
                    if (expPagador.getCurrentExp() - Integer.parseInt(args[1]) > 0) {
                        expPagador.setExp(expPagador.getCurrentExp() - Integer.parseInt(args[1]));
                        expPagado.setExp(expPagado.getCurrentExp() + Integer.parseInt(args[1]));
                        p.sendMessage(ChatColor.GREEN + "Has pagado a " + pl.getName() + " " + args[0] + " exp.");
                        pl.sendMessage(ChatColor.GREEN + "Has recibido " + args[0] + " exp.");
                    } else {
                        p.sendMessage(ChatColor.RED + "No tienes suficientes exp.");
                    }

                } else {
                    p.sendMessage(ChatColor.RED + "Ese jugador no existe o no está conectado.");
                }
            }
        }
        return true;
    }
}
