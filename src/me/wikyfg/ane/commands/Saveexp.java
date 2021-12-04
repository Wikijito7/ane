package me.wikyfg.ane.commands;

import me.wikyfg.ane.ANEMain;
import me.wikyfg.ane.api.ExperienceAPI;
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
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.Collections;

public class Saveexp implements CommandExecutor {
    private ExperienceAPI experienceAPI;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        experienceAPI = new ExperienceAPI(p);
        if (args.length != 1) {
            p.sendMessage(ChatColor.RED + "Te has confundido de argumentos, prueba a poner /saveexp <quantity>/all.");
            return true;
        }

        if (args[0].equalsIgnoreCase("all")){
            generateExpPaper(p, experienceAPI.getCurrentExp());
            return true;
        }

        if (Numeric.isNumeric(args[0])){
            if (!experienceAPI.hasExp(Integer.parseInt(args[0]))){
                p.sendMessage(ChatColor.RED + "No tienes tanta experiencia (" + experienceAPI.getCurrentExp() + ")");
                return false;
            }
            generateExpPaper(p, Integer.parseInt(args[0]));

        } else {
            p.sendMessage(ChatColor.RED + "Tienes que añadir un valor numérico.");
        }
        return true;
    }

    private void generateExpPaper(Player p, int exp) {
        final ItemStack item = new ItemStack(Material.PAPER);
        item.setAmount(1);
        item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 5);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("Vale de experiencia por " + exp + " exp");
        itemMeta.setLore(Collections.singletonList(exp + " exp"));
        item.setItemMeta(itemMeta);
        p.getInventory().addItem(item);
        experienceAPI.setExp(experienceAPI.getCurrentExp() - exp);
    }
}