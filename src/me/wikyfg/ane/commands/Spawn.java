package me.wikyfg.ane.commands;

import me.wikyfg.ane.ANEMain;
import me.wikyfg.ane.files.Files;
import me.wikyfg.ane.utils.CommandExperienceUtils;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {
    private final ANEMain main;

    public Spawn(ANEMain main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("spawn")) {
            if (args.length == 0) {
                if (p.getGameMode() == GameMode.SURVIVAL &&
                        !CommandExperienceUtils.Companion.onCommandExecution(p, 1)) {
                    return true;
                }

                p.sendMessage(ChatColor.GREEN + "Enviandote al spawn.");
                Files.userdata.set(p.getName() + ".back", p.getLocation().getX() + "," + p.getLocation().getY() + "," + p.getLocation().getZ() + "," + p.getLocation().getWorld().getName());
                if (main.files != null) {
                    main.files.saveFiles();
                }
                p.teleport(p.getLocation().getWorld().getSpawnLocation());

            } else {
                p.sendMessage(ChatColor.RED + "Has puesto demasiados argumentos, prueba a poner sólo /spawn.");
            }

        }
        return true;
    }
}
