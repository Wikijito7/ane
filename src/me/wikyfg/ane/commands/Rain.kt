package me.wikyfg.ane.commands

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Rain : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {
        val p = sender as? Player
        p?.apply {
            if (!isOp) {
                sendMessage("${ChatColor.RED}Lo siento, no tienes permisos para hacer esto.")
                return true
            }
            if (args.isNotEmpty()) {
                sendMessage("${ChatColor.RED}Te has confundido de argumentos, prueba a poner /rain.")
                return true
            }
            world.setStorm(true)
            sendMessage("${ChatColor.GOLD}Clima cambiado a lluvia.")
            world.weatherDuration = 20000
        }
        return true
    }
}