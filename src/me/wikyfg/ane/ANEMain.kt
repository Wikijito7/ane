package me.wikyfg.ane

import me.wikyfg.ane.commands.*
import me.wikyfg.ane.events.ClickEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.PluginManager
import me.wikyfg.ane.events.JoinEvent
import me.wikyfg.ane.events.DeathEvent
import me.wikyfg.ane.events.PlayerEvent
import me.wikyfg.ane.files.Files

class ANEMain : JavaPlugin() {
    @JvmField
    var files: Files? = null
    private val plugin = server.pluginManager
    override fun onEnable() {
        logger.info("Absolutely Not Essentials has been enabled")
        loadEvents()
        loadCommands()
        setupFiles()
    }

    private fun setupFiles() {
        files = Files()
        files?.apply {
            setupFiles()
            saveFiles()
        }
    }

    override fun onDisable() {
        logger.info("Absolutely Not Essentials has been disabled")
        files?.saveFiles()
    }

    private fun loadCommands() {
        getCommand("tp")?.setExecutor(Teleport(this))
        getCommand("ANE")?.setExecutor(ANECommand())
        getCommand("back")?.setExecutor(Back())
        getCommand("home")?.setExecutor(Home(this))
        getCommand("sethome")?.setExecutor(Sethome(this))
        getCommand("spawn")?.setExecutor(Spawn(this))
        getCommand("homelist")?.setExecutor(HomeList())
        getCommand("pagar")?.setExecutor(PayCMD(this))
        getCommand("balance")?.setExecutor(Balance())
        getCommand("jail")?.setExecutor(Jail(this))
        getCommand("day")?.setExecutor(Day())
        getCommand("night")?.setExecutor(Night())
        getCommand("rain")?.setExecutor(Rain())
        getCommand("sun")?.setExecutor(Sun())
        getCommand("savexp")?.setExecutor(Saveexp())
        getCommand("setspawn")?.setExecutor(Setspawn())
        getCommand("do")?.setExecutor(Do())
        getCommand("me")?.setExecutor(Me())
        getCommand("entorno")?.setExecutor(Entorno())
    }

    private fun loadEvents() {
        plugin.apply {
            val aneMain = this@ANEMain
            registerEvents(JoinEvent(aneMain), aneMain)
            registerEvents(DeathEvent(aneMain), aneMain)
            registerEvents(PlayerEvent(aneMain), aneMain)
            registerEvents(ClickEvent(), aneMain)
        }
    }
}