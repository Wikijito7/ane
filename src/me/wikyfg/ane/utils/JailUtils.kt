package me.wikyfg.ane.utils

import me.wikyfg.ane.files.Files
import org.bukkit.entity.Player

fun Player.isInJail(): Boolean {
    return Files.userdata.contains("$name.jail") &&
            Files.userdata["$name.jail"] == "true"
}