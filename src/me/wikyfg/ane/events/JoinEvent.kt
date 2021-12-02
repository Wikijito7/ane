package me.wikyfg.ane.events

import me.wikyfg.ane.ANEMain
import me.wikyfg.ane.files.Files
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class JoinEvent(private val main: ANEMain) : Listener {
    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        val p = e.player
        p.apply {
            if (Files.userdata[name] == null) {
                Files.userdata["$name.homes.home"] = "none"
                Files.userdata["$name.back"] = "${location.x},${location.y},${location.z},${location.world?.name}"
                main.files?.saveFiles()
            }
        }
    }
}