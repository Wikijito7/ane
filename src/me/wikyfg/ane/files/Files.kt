package me.wikyfg.ane.files

import java.io.IOException
import org.bukkit.configuration.InvalidConfigurationException
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class Files {
    fun setupFiles() {
        if (!user.exists()) {
            user.mkdir()
            userdata["motd.message"] = "&3Bienvenido de nuevo al servidor &6%PLAYER%"
            userdata.createSection("Users")
        }
        saveFiles()
    }

    fun saveFiles() {
        try {
            userdata.save(user)
            userdata.load(user)

        } catch (e: IOException) {
            e.printStackTrace()

        } catch (e: InvalidConfigurationException) {
            e.printStackTrace()
        }
    }

    companion object {
        private var user = File("plugins/ANE/", "userdata.yml")
        @JvmField
        var userdata = YamlConfiguration.loadConfiguration(user)
    }
}