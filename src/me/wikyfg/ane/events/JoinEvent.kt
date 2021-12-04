package me.wikyfg.ane.events

import me.wikyfg.ane.ANEMain
import me.wikyfg.ane.api.ExperienceAPI
import me.wikyfg.ane.files.Files
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class JoinEvent(private val main: ANEMain) : Listener {
    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        val p = e.player
        val expAPI = ExperienceAPI(p)
        p.apply {
            if (Files.userdata[name] == null) {
                Files.userdata["$name.homes.home"] = "none"
                Files.userdata["$name.back"] = "${location.x},${location.y},${location.z},${location.world?.name}"
                main.files?.saveFiles()
                Bukkit.broadcastMessage("${ChatColor.YELLOW} ¡Bienvenido $displayName al servidor! ¡Recuerda usar /ane!")

            } else {
                p.sendMessage("${ChatColor.GREEN}¡Recuerda usar ${ChatColor.GOLD}/ane${ChatColor.GREEN}!")
            }
        }
    }
}