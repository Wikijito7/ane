package me.wikyfg.ane.files;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Files {
    public static File user = new File("plugins/ANE/", "userdata.yml");
    public static YamlConfiguration userdata = YamlConfiguration.loadConfiguration(user);
    public static File money = new File("plugins/ANE/", "moneydata.yml");
    public static YamlConfiguration moneydata = YamlConfiguration.loadConfiguration(money);

    public void setupFiles(){
        if(!user.exists()){
            user.mkdir();
            userdata.set("motd.message", "&3Bienvenido de nuevo al servidor &6%PLAYER%");
            userdata.createSection("Users");
        }
        if(!money.exists()){
            money.mkdir();
        }
        saveFiles();
    }

    public void saveFiles() {
        try {
            userdata.save(user);
            userdata.load(user);
            moneydata.save(money);
            moneydata.load(money);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}
