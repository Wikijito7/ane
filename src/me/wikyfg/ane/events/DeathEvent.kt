package me.wikyfg.ane.events

import me.wikyfg.ane.ANEMain
import me.wikyfg.ane.files.Files
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

class DeathEvent(private val main: ANEMain) : Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    fun onDeath(e: PlayerDeathEvent) {
        val p = e.entity
        p.apply {
            main.logger.info("$name ha muerto.")
            val coords = "${location.x},${location.y},${location.z},${location.world?.name}"
            Files.userdata["$name.back"] = coords
            main.files?.saveFiles()
        }
    }
}