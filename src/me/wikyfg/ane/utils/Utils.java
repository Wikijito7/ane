package me.wikyfg.ane.utils;

import me.wikyfg.ane.ANEMain;
import me.wikyfg.ane.files.Files;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class Utils implements Economy {

    private final ANEMain ane;

    public Utils(ANEMain ane) {
        this.ane = ane;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return "ane";
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 0;
    }

    @Override
    public String format(double v) {
        return null;
    }

    @Override
    public String currencyNamePlural() {
        return "macacoins";
    }

    @Override
    public String currencyNameSingular() {
        return "macacoin";
    }

    @Override
    public boolean hasAccount(String s) {
        Player p = Bukkit.getPlayer(s);
        return Files.moneydata.contains(p.getName());
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        return Files.moneydata.contains(offlinePlayer.getName());
    }

    @Override
    public boolean hasAccount(String s, String s1) {
        return false;
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer, String s) {

        return Files.moneydata.contains(offlinePlayer.getName());
    }

    @Override
    public double getBalance(String s) {
        if(!Files.moneydata.contains(s)) Files.moneydata.set(s, 0);

        return Files.moneydata.getDouble(s);
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        if(!Files.moneydata.contains(offlinePlayer.getName())) Files.moneydata.set(offlinePlayer.getName(), 0);

        return Files.moneydata.getDouble(offlinePlayer.getName());
    }

    @Override
    public double getBalance(String s, String s1) {
        return Files.moneydata.getDouble(s);
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer, String s) {
        return Files.moneydata.getDouble(offlinePlayer.getName());
    }

    @Override
    public boolean has(String s, double v) {
        return false;
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, double v) {
        return Files.moneydata.getDouble(offlinePlayer.getName()) >= v;
    }

    @Override
    public boolean has(String s, String s1, double v) {
        return false;
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, String s, double v) {
        return false;
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double v) {
        double result = Files.moneydata.getDouble(offlinePlayer.getName()) - v;
        Files.moneydata.set(offlinePlayer.getName(), result);
        ane.files.saveFiles();
        return new EconomyResponse(v, Files.moneydata.getDouble(offlinePlayer.getName()), EconomyResponse.ResponseType.SUCCESS, "error");
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, String s1, double v) {
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        double result = Files.moneydata.getDouble(offlinePlayer.getName()) - v;
        Files.moneydata.set(offlinePlayer.getName(), result);
        ane.files.saveFiles();
        return new EconomyResponse(v, Files.moneydata.getDouble(offlinePlayer.getName()), EconomyResponse.ResponseType.SUCCESS, "error");
    }

    @Override
    public EconomyResponse depositPlayer(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double v) {
        Files.moneydata.set(offlinePlayer.getName(), Files.moneydata.getDouble(offlinePlayer.getName()) + v);
        ane.files.saveFiles();
        return new EconomyResponse(v, Files.moneydata.getDouble(offlinePlayer.getName()), EconomyResponse.ResponseType.SUCCESS, "error");
    }

    @Override
    public EconomyResponse depositPlayer(String s, String s1, double v) {
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        Files.moneydata.set(offlinePlayer.getName(), Files.moneydata.getDouble(offlinePlayer.getName()) + v);
        ane.files.saveFiles();
        return new EconomyResponse(v, Files.moneydata.getDouble(offlinePlayer.getName()), EconomyResponse.ResponseType.SUCCESS, "error");
    }

    @Override
    public EconomyResponse createBank(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public boolean createPlayerAccount(String s) {
        Files.moneydata.set(s, 0);
        ane.files.saveFiles();
        return true;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
        Files.moneydata.set(offlinePlayer.getName(), 0);
        ane.files.saveFiles();
        return true;
    }

    @Override
    public boolean createPlayerAccount(String s, String s1) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String s) {
        return false;
    }
}