package me.wikyfg.ane.commands;

import me.wikyfg.ane.ANEMain;
import me.wikyfg.ane.files.Files;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Jail implements CommandExecutor {

    private ANEMain main;
    public Jail(ANEMain main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("jail")){

            if (!p.isOp()){
                p.sendMessage(ChatColor.RED + "Lo siento, no tienes permisos para hacer esto.");
                return false;
            }

            if(args.length == 0 || args.length > 1){
                if(Files.userdata.get("alcalde").equals(p.getName()) || p.isOp()){
                    p.sendMessage(ChatColor.RED + "Para usar este comando haz /jail <nombre>");
                }else{
                    p.sendMessage(ChatColor.MAGIC + "??????");
                }
            }

            if(args.length == 1){
                if(Files.userdata.get("alcalde").equals(p.getName()) || p.isOp()){
                    Player pl = Bukkit.getPlayer(args[0]);
                    if(pl != null || Files.userdata.contains(args[0])){
                        if(Files.userdata.get(args[0] + ".jail").equals("true")){
                            Files.userdata.set(args[0] + ".jail", "false");
                            p.sendMessage(ChatColor.RED + args[0] + " ha sido desencarcelado.");
                            if(pl != null) pl.sendMessage(ChatColor.GREEN + "Te han liberado, no vuelvas a hacer lo que hiciste previamente si no quieres ser encanrcelado de nuevo.");
                            main.files.saveFiles();
                            return false;
                        }
                        Files.userdata.set(args[0] + ".jail", "true");
                        p.sendMessage(ChatColor.RED + args[0] + " ha sido encarcelado.");
                        if(pl != null) pl.sendMessage(ChatColor.DARK_RED + "Has sido encarcelado, piensa qué has hecho para llegar a esa situación.");
                        main.files.saveFiles();
                    }

                }else{
                    p.sendMessage(ChatColor.MAGIC + "Hola, no sé cómo ni cuando has encontrado este comando, pero por favor, no lo uses ya que no tienes" + ChatColor.RED + "permisos para ello.");
                }
            }

        }

        return false;
    }
}
