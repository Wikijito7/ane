package me.wikyfg.ane.utils

import org.bukkit.ChatColor
import org.bukkit.entity.Player

class CommandExperienceUtils {
    companion object {
        private fun removeExp(p: Player, level: Int) {
            p.level = p.level - level
        }

        private fun hasEnoughExperience(p: Player): Boolean {
            return p.level - 1 > 0
        }

        fun onCommandExecution(p: Player, level: Int): Boolean {
            return if (!hasEnoughExperience(p)) {
                p.sendMessage("${ChatColor.RED}Necesitas al menos un nivel de experiencia para poder usar los comandos.")
                false

            } else {
                removeExp(p, level)
                true
            }
        }
    }
}