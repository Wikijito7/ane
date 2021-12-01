package me.wikyfg.ane.commands

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Do : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {
        val p = sender as? Player
        p?.apply {
            if (args.isNotEmpty()) {
                Bukkit.broadcastMessage("${ChatColor.YELLOW}* $name ${args.joinToString(separator = " ")}")

            } else {
                sendMessage("${ChatColor.RED}Creo que te has confundido, prueba con /do <mensaje>.")
            }
        }

        return true
    }
}