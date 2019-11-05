package me.wikyfg.ane.commands;

import me.wikyfg.ane.ANEMain;
import me.wikyfg.ane.utils.Numeric;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class Saveexp implements CommandExecutor {

    private ANEMain main;

    public Saveexp(ANEMain main) {
        this.main = main;
    }
    private ItemStack item = new ItemStack(Material.PAPER);
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (args.length != 1) {
            p.sendMessage(ChatColor.RED + "Te has confundido de argumentos, prueba a poner /saveexp <quantity>/all.");
            return false;
        }

        if (args[0].equalsIgnoreCase("all")){
            item.setAmount(1);
            item.addEnchantment(Enchantment.DURABILITY, 5);
            item.getItemMeta().setDisplayName(ChatColor.DARK_PURPLE + String.valueOf(p.getExp()) + " xp");
            p.getInventory().addItem(item);
            p.setExp(0);
            return false;
        }

        if (Numeric.isNumeric(args[0])){
            if (p.getExp() < Float.parseFloat(args[1])){
                p.sendMessage(ChatColor.RED + "No tienes tanta experiencia (" + p.getExp() + ")");
                return false;
            }
            item.setAmount(1);
            item.addEnchantment(Enchantment.DURABILITY, 5);
            item.getItemMeta().setDisplayName(ChatColor.DARK_PURPLE + args[1] + " xp");
            p.getInventory().addItem(item);
            p.setExp(p.getExp() - Float.parseFloat(args[1]));
        } else {
            p.sendMessage(ChatColor.RED + "Tienes que añadir un valor numérico.");
        }
        return false;
    }
}