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
        if(cmd.getName().equalsIgnoreCase("ane")){
            p.sendMessage(ChatColor.GREEN + "Bienvenido a la página de ayuda de Absolutly Not Essentials.");
            p.sendMessage(ChatColor.GREEN + "Programado por " + ChatColor.GOLD + "Woki.");
            p.sendMessage(ChatColor.GOLD + "/spawn: " + ChatColor.GREEN + "Te envia al spawn del mundo. " + ChatColor.RED + "¡Cuesta un nivel de experiencia usarlo!");
            p.sendMessage(ChatColor.GOLD + "/home (<nombre>): " + ChatColor.GREEN + "Te envia al lugar que hayas seleccionado como casa. " + ChatColor.RED + "¡Cuesta un nivel de experiencia usarlo!");
            p.sendMessage(ChatColor.GOLD + "/sethome (<nombre>): " + ChatColor.GREEN + "Pone ese punto como tu hogar con el nombre indicado. " + ChatColor.RED + "¡Cuesta un nivel de experiencia usarlo!");
            p.sendMessage(ChatColor.GOLD + "/homelist: " + ChatColor.GREEN + "Te muestra la lista de todas tus casa.");
            p.sendMessage(ChatColor.GOLD + "/balance: " + ChatColor.GREEN + "Te muestra la cantidad de experiencia que tienes (/pagar).");
            p.sendMessage(ChatColor.GOLD + "/back: " + ChatColor.GREEN + "Te envia a la localización previa. " + ChatColor.RED + "¡Cuesta un nivel de experiencia usarlo!");
            p.sendMessage(ChatColor.GOLD + "/pagar <nombre> <cantidad>: " + ChatColor.GREEN + "Paga a un usuario una cantidad de experiencia. " + ChatColor.RED + "¡Cuesta un nivel de experiencia usarlo!");
            p.sendMessage(ChatColor.GOLD + "/savexp <cantidad/all>: " + ChatColor.GREEN + "Guarda toda tu experiencia en un papel para poder usarlo en un futuro. " + ChatColor.RED + "¡Cuesta un nivel de experiencia usarlo!");
            p.sendMessage(ChatColor.GOLD + "/do <mensaje>: " + ChatColor.GREEN + "Envia a todo el servidor una acción en tercera persona.");

            if (p.isOp()) p.sendMessage(ChatColor.GOLD + "/day: " + ChatColor.GREEN + "Hace de día.");
            if (p.isOp()) p.sendMessage(ChatColor.GOLD + "/night: " + ChatColor.GREEN + "Hace de noche.");
            if (p.isOp()) p.sendMessage(ChatColor.GOLD + "/sun: " + ChatColor.GREEN + "Quita la lluvia.");
            if (p.isOp()) p.sendMessage(ChatColor.GOLD + "/rain: " + ChatColor.GREEN + "Hace que llueva.");
            if (p.isOp()) p.sendMessage(ChatColor.GOLD + "/setspawn: " + ChatColor.GREEN + "Cambia el punto donde reapareces en el mundo.");

        }
        return false;
    }
}