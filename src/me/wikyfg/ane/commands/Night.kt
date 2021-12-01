package me.wikyfg.ane.commands

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Night : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {
        val p = sender as? Player
        p?.apply {
            if (!isOp) {
                sendMessage("${ChatColor.RED}Lo siento, no tienes permisos para hacer esto.")
                return true
            }
            if (args.isNotEmpty()) {
                sendMessage("${ChatColor.RED}Te has confundido de argumentos, prueba a poner /night.")
                return true
            }
            sendMessage("${ChatColor.GOLD}Tiempo cambiado a de noche.")
            world.time = 18000
        }
        return true
    }
}