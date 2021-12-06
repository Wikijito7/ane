package me.wikyfg.ane.events

import me.wikyfg.ane.api.ExperienceAPI
import me.wikyfg.ane.utils.isInJail
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class ClickEvent : Listener {
    @EventHandler
    fun onClick(e: PlayerInteractEvent){
        val p = e.player
        val experienceAPI = ExperienceAPI(p)
        val itemInMainHand = p.inventory.itemInMainHand
        if (p.isInJail()) {
            p.sendMessage("${ChatColor.RED}Necesitas al menos un nivel de experiencia para poder usar los comandos.")
            e.isCancelled = true
            return
        }
        if (itemInMainHand.type == Material.PAPER) {
            val exp = itemInMainHand.itemMeta?.lore?.get(0)?.split(" ")?.toTypedArray()?.get(0) ?: return
            experienceAPI.changeExp(exp.toInt())
            p.inventory.remove(itemInMainHand)
        }
    }
}