package me.wikyfg.ane.commands;

import com.google.common.util.concurrent.AtomicDouble;
import me.wikyfg.ane.ANEMain;
import me.wikyfg.ane.files.Files;
import me.wikyfg.ane.utils.Numeric;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ANECommand implements CommandExecutor {

    private ANEMain main;
    public ANECommand(ANEMain main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        DecimalFormat dFormat = new DecimalFormat("#.##");
        if(cmd.getName().equalsIgnoreCase("ane")){
            if(args.length == 0){
                p.sendMessage(ChatColor.GREEN + "Bienvenido a la página de ayuda de Absolutly Not Essentials.");
                p.sendMessage(ChatColor.GOLD + "/spawn: " + ChatColor.GREEN + "Te envia al spawn del mundo."); //ToDo: Posibilidad de modificar el spawn del mundo(/setspawn???)
                p.sendMessage(ChatColor.GOLD + "/home: " + ChatColor.GREEN + "Te envia al lugar que hayas seleccionado como casa."); //ToDo: /homelist????
                p.sendMessage(ChatColor.GOLD + "/sethome (<nombre>): " + ChatColor.GREEN + "Pone ese punto como tu hogar con el nombre indicado.");
                p.sendMessage(ChatColor.GOLD + "/back: " + ChatColor.GREEN + "Te envia a la localización previa.");
                p.sendMessage(ChatColor.GOLD + "/day: " + ChatColor.GREEN + "Hace de día.");
                p.sendMessage(ChatColor.GOLD + "/night: " + ChatColor.GREEN + "Hace de noche.");
                p.sendMessage(ChatColor.GOLD + "/sun: " + ChatColor.GREEN + "Quita la lluvia.");
                p.sendMessage(ChatColor.GOLD + "/rain: " + ChatColor.GREEN + "Hace que llueva.");
            }

            if(args.length == 1){
                if(args[0].equalsIgnoreCase("balance") || args[0].equalsIgnoreCase("money")){
                    p.sendMessage(ChatColor.GREEN + "Tienes " + String.valueOf(dFormat.format(main.eco.getBalance(p))).replace(".", ",") + " macacoins.");
                }

                if(args[0].equalsIgnoreCase("pay") || args[0].equalsIgnoreCase("pagar")){
                    p.sendMessage(ChatColor.RED + "Colega, el comando es /pay <jugador> <cantidad>");
                }

                if(args[0].equalsIgnoreCase("top")){
                    final HashMap<String, Double> top = new HashMap<>();
                    Files.moneydata.getKeys(false).forEach(e-> top.put(e, Files.moneydata.getDouble(e)));
                    AtomicInteger l = new AtomicInteger(1);
                    ArrayList<Double> total = new ArrayList<>();
                    top.forEach((u, c)-> total.add(c));
                    double sum = Numeric.sum(total);

                    p.sendMessage(ChatColor.GRAY + "Calculando el top del dinero...");
                    top.entrySet().stream().sorted(Map.Entry.comparingByValue(Collections.reverseOrder())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new)).forEach((t,v)->{
                            p.sendMessage(ChatColor.GRAY + String.valueOf(l) + ". " + t + ": " + dFormat.format(v));
                            l.addAndGet(1);
                    });
                    p.sendMessage(ChatColor.GRAY + "Dinero total en el servidor: " + dFormat.format(sum) + " macacoins.");
                }
            }

            if(args.length == 2){
                if(args[0].equalsIgnoreCase("balance") || args[0].equalsIgnoreCase("money")){
                    Player pl = main.getServer().getPlayer(args[1]);
                    if(pl != null) {
                        p.sendMessage(ChatColor.GREEN + pl.getName() +" tiene " + String.valueOf(dFormat.format(main.eco.getBalance(pl))).replace(".", ",") + " macacoins.");
                    }else{
                        p.sendMessage(ChatColor.RED + "Ese jugador no existe o no está conectado.");
                    }
                }
            }

            if(args.length == 3){
                Player pl = main.getServer().getPlayer(args[1]);
                if(pl != null){
                    if(args[0].equalsIgnoreCase("pay") || args[0].equalsIgnoreCase("pagar")) {
                        if (main.eco.getBalance(p) - Double.parseDouble(args[2]) > 0) {
                            if (!Files.userdata.get("alcalde").equals("none")) {
                                Player alc = main.getServer().getPlayer(Files.userdata.get("alcalde").toString());

                                if (alc != null){
                                    main.eco.depositPlayer(alc, Double.parseDouble(args[2]) * 0.10);
                                    alc.sendMessage(ChatColor.GREEN + "Has recibido " + Double.parseDouble(args[2]) * 0.10 + " macacoins por los impuestos de una transacción.");
                                }

                            }

                            if(Double.parseDouble(args[2]) < 0.01){
                                p.sendMessage(ChatColor.RED + "La cantidad mínima es 0.01.");
                                return false;
                            }

                            main.eco.depositPlayer(pl, Double.parseDouble(args[2]));
                            main.eco.withdrawPlayer(p, Double.parseDouble(args[2]));
                            main.files.saveFiles();
                            p.sendMessage(ChatColor.GREEN + "Has pagado a " + pl.getName() + " " + dFormat.format(Double.parseDouble(args[2])) + " macacoins.");
                            pl.sendMessage(ChatColor.GREEN + p.getName() + " te ha enviado " + Double.parseDouble(dFormat.format(Double.parseDouble(args[2]))) + " macacoins.");
                        } else {
                            p.sendMessage(ChatColor.RED + "No tienes suficientes macacoins.");
                        }
                    }

                    if(args[0].equalsIgnoreCase("givemoney")){
                        main.eco.depositPlayer(pl, Double.parseDouble(args[2]));
                        p.sendMessage(ChatColor.GREEN + "Enviado a " + pl.getName() + " " + args[2] + " macacoins.");
                    }

                    if(args[0].equalsIgnoreCase("removemoney")){
                        main.eco.withdrawPlayer(pl, Double.parseDouble(args[2]));
                        p.sendMessage(ChatColor.GREEN + "Eliminado a " + pl.getName() + " " + args[2] + " macacoins.");
                    }

                }else{
                    p.sendMessage(ChatColor.RED + "Ese jugador no existe o no está conectado.");
                }
            }

            if(args.length > 4 || args.length == 2) {
                if (args[0].equalsIgnoreCase("pay") || args[0].equalsIgnoreCase("pagar")) {
                    p.sendMessage(ChatColor.RED + "Colega, el comando es /ane pay <jugador> <cantidad>");
                }
            }
        }
        return false;
    }
}