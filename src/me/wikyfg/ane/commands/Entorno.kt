package me.wikyfg.ane.commands

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Entorno : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {
        val p = sender as? Player
        if (args.isNotEmpty()) {
            Bukkit.broadcastMessage("${ChatColor.GOLD}[ENTORNO] ${args.joinToString(separator = " ")}")

        } else {
            p?.let {
                p.sendMessage("${ChatColor.RED}Creo que te has confundido, prueba con /entorno <mensaje>.")
            }
        }

        return true
    }
}