package me.wikyfg.ane.events

import me.wikyfg.ane.api.ExperienceAPI
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
        if (itemInMainHand.type == Material.PAPER) {
            val exp = itemInMainHand.itemMeta?.lore?.get(0)?.split(" ")?.toTypedArray()?.get(0) ?: return
            experienceAPI.changeExp(exp.toInt())
            p.inventory.remove(itemInMainHand)
        }
    }
}