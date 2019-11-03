package me.wikyfg.ane.commands;

import me.wikyfg.ane.ANEMain;
import me.wikyfg.ane.files.Files;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayCMD implements CommandExecutor {

    private ANEMain main;
    public PayCMD(ANEMain main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("pagar")){
            Player p = (Player) sender;
            if(args.length < 2 || args.length > 2){
                p.sendMessage(ChatColor.RED + "Colega, el comando es /pay <jugador> <cantidad>");
            }
            if(args.length == 2){
                Player pl = main.getServer().getPlayer(args[0]);
                if(pl != null){
                    if(main.eco.getBalance(p)-Double.parseDouble(args[1]) > 0){
                        if(!Files.userdata.get("alcalde").equals("none")){
                            Player alc = main.getServer().getPlayer(Files.userdata.get("alcalde").toString());
                            main.eco.depositPlayer(alc, Double.parseDouble(args[1])*0.10);
                            if(main.getServer().getPlayer(alc.getName()) != null) alc.sendMessage(ChatColor.GREEN + "Has recibido " + Double.parseDouble(args[1])*0.10 + " macacoins por los impuestos de una transacción.");
                        }
                        main.eco.depositPlayer(pl, Double.parseDouble(args[1]));
                        main.eco.depositPlayer(p, main.eco.getBalance(p)-Double.parseDouble(args[1]));
                        p.sendMessage(ChatColor.GREEN + "Has pagado a " + pl.getName() + " " + args[0] + " macacoins.");
                        pl.sendMessage(ChatColor.GREEN + "Has recibido " + args[0] + " macacoins.");
                    }else{
                        p.sendMessage(ChatColor.RED + "No tienes suficientes macacoins.");
                    }

                }else{
                    p.sendMessage(ChatColor.RED + "Ese jugador no existe o no está conectado.");
                }
            }
        }
        return false;
    }
}
