package me.wikyfg.ane.commands

import me.wikyfg.ane.api.ExperienceAPI
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Balance : CommandExecutor {
    private var experienceAPI: ExperienceAPI? = null
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {
        val p = sender as? Player
        if (cmd.name.equals("balance", ignoreCase = true) ||
            cmd.name.equals("money", ignoreCase = true)
        ) {
            p?.apply {
                experienceAPI = ExperienceAPI(p)
                if (args.isEmpty()) {
                    sendMessage("${ChatColor.GREEN}Tienes ${ChatColor.GOLD}${experienceAPI?.currentExp} ${ChatColor.GREEN}exp.")

                } else {
                    sendMessage("${ChatColor.RED}Illo colega tas pasao d'argumentoh. Prueba a poner /money o /balance.")
                }
            }
        }

        return true
    }
}