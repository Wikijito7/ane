package me.wikyfg.ane.commands

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ANECommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {
        val p = sender as Player
        if (cmd.name.equals("ane", ignoreCase = true)) {
            p.apply {
                sendMessage("${ChatColor.GREEN}Bienvenido a la página de ayuda de Absolutly Not Essentials.")
                sendMessage("${ChatColor.GREEN}Desarrollado por ${ChatColor.GOLD}Woki.")
                sendMessage("")
                sendMessage("${ChatColor.GOLD}/spawn: ${ChatColor.GREEN}Te envia al spawn del mundo. ${ChatColor.RED}¡Cuesta un nivel de experiencia usarlo!")
                sendMessage("${ChatColor.GOLD}/home (<nombre>): ${ChatColor.GREEN}Te envia al lugar que hayas seleccionado como casa. ${ChatColor.RED}¡Cuesta un nivel de experiencia usarlo!")
                sendMessage("${ChatColor.GOLD}/sethome (<nombre>): ${ChatColor.GREEN}Pone ese punto como tu hogar con el nombre indicado. ${ChatColor.RED}¡Cuesta un nivel de experiencia usarlo!")
                sendMessage("${ChatColor.GOLD}/homelist: ${ChatColor.GREEN}Te muestra la lista de todas tus casa.")
                sendMessage("${ChatColor.GOLD}/balance: ${ChatColor.GREEN}Te muestra la cantidad de experiencia que tienes (/pagar).")
                sendMessage("${ChatColor.GOLD}/back: ${ChatColor.GREEN}Te envia a la localización previa. ${ChatColor.RED}¡Cuesta un nivel de experiencia usarlo!")
                sendMessage("${ChatColor.GOLD}/pagar <nombre> <cantidad>: ${ChatColor.GREEN}Paga a un usuario una cantidad de experiencia. ${ChatColor.RED}¡Cuesta un nivel de experiencia usarlo!")
                sendMessage("${ChatColor.GOLD}/savexp <cantidad/all>: ${ChatColor.GREEN}Guarda toda tu experiencia en un papel para poder usarlo en un futuro. ${ChatColor.RED}¡Cuesta un nivel de experiencia usarlo!")
                sendMessage("${ChatColor.GOLD}/do <mensaje>: ${ChatColor.GREEN}Envia a todo el servidor una acción en tercera persona.")

                if (isOp) {
                    sendMessage("")
                    sendMessage("${ChatColor.RED}Comandos de Administrador:")
                    sendMessage("${ChatColor.GOLD}/day: ${ChatColor.GREEN}Hace de día.")
                    sendMessage("${ChatColor.GOLD}/night: ${ChatColor.GREEN}Hace de noche.")
                    sendMessage("${ChatColor.GOLD}/sun: ${ChatColor.GREEN}Quita la lluvia.")
                    sendMessage("${ChatColor.GOLD}/rain: ${ChatColor.GREEN}Hace que llueva.")
                    sendMessage("${ChatColor.GOLD}/setspawn: ${ChatColor.GREEN}Cambia el punto donde reapareces en el mundo.")
                }
            }
        }

        return true
    }
}