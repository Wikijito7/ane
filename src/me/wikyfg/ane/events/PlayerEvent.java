package me.wikyfg.ane.events;

import me.wikyfg.ane.ANEMain;
import me.wikyfg.ane.api.ExperienceAPI;
import me.wikyfg.ane.files.Files;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Random;

public class PlayerEvent implements Listener {

    private final ANEMain main;

    public PlayerEvent(ANEMain main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        String[] mensajes = {
                "&a{player} ha entrado, ¡corred!",
                "&aVaya.. quien faltaba.. {player} ha entrado..",
                "&a¿Quien ha invitado a {player}?",
                "&a¡Bieeeeeeen! Ha entrado {player}.",
                "&aÉramos pocos y la abuela parió a {player}..",
                "&a¡Holi {player}!",
                "&a{player}??!?!?!?!111",
                "&a+ &r{player}.",
                "&a&l¿¡Ojo!? &r&a¿Qué haces aquí, {player}?",
                "&a¡Por fin entras, {player}!",
                "&a¡Saludad todos a {player}!",
                "&aGuardad todo lo divertido, ha entrado {player}.",
                "&bEeeeeh, zizi, &avenga entra {player}."
        };
        e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', mensajes[new Random().nextInt(mensajes.length)].replace("{player}", e.getPlayer().getName())));
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        String[] mensajes = {
                "&c{player} ha salido, ¡por fin!",
                "&c¡Se ha ido {player}! ¡Menos mal!",
                "&c¿{player}?",
                "&c{player} ha ido a defender la libertad de los presos políticos.",
                "&cC fué {player}..",
                "&c¡Adió {player}!",
                "&c{player}??!?!?!?!111",
                "&c- &r{player}.",
                "&c¿{player}? :c",
                "&c¡Por fin te vas, {player}!",
                "&c¡Adios {player}!",
                "&cSacad todo lo divertido de nuevo, sa ido {player}.",
                "&bEeeeeh, zizi, &cvenga taluego {player}."
        };
        e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', mensajes[new Random().nextInt(mensajes.length)].replace("{player}", e.getPlayer().getName())));
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (!Files.userdata.contains(p.getName() + ".jail")) {
            Files.userdata.set(p.getName() + ".jail", "false");
            main.files.saveFiles();
        }
        if (Files.userdata.get(p.getName() + ".jail").equals("true")) {
            e.setFormat(ChatColor.DARK_RED + "[Jail] " + ChatColor.GRAY + e.getPlayer().getName() + ": " + e.getMessage());
        }
        String opPrefix = "[" + ChatColor.RED + "OP" + ChatColor.RESET + "] ";
        String userPrefix = "[" + ChatColor.YELLOW + "U" + ChatColor.RESET + "] ";
        String prefix = p.isOp() ? opPrefix : userPrefix;
        e.setFormat(ChatColor.translateAlternateColorCodes('&', e.getFormat().replaceAll("<", prefix).replaceAll(">", ":")));
        e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        if (!Files.userdata.contains(p.getName() + ".jail")) {
            Files.userdata.set(p.getName() + ".jail", "false");
            main.files.saveFiles();
        }

        if (Files.userdata.get(p.getName() + ".jail").equals("true")) {
            p.sendMessage(ChatColor.DARK_RED + "Estás encarcelado, piensa lo que hiciste e intentalo de nuevo más tarde.");
            e.setCancelled(true);
            return;
        }
    }

    @EventHandler
    public void onPlayerBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (!Files.userdata.contains(p.getName() + ".jail")) {
            Files.userdata.set(p.getName() + ".jail", "false");
            main.files.saveFiles();
        }

        if (Files.userdata.get(p.getName() + ".jail").equals("true")) {
            p.sendMessage(ChatColor.DARK_RED + "Estás encarcelado, piensa lo que hiciste e intentalo de nuevo más tarde.");
            e.setCancelled(true);
        }
    }



}
