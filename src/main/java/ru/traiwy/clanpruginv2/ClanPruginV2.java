package ru.traiwy.clanpruginv2;

import commands.ClanMainMenuCommand;
import event.EventOnPlayerChat;
import event.EventonInventoryClick;
import inventoryClan.ClanViborMenu;
import inventoryClan.InventoryClan;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import util.ConfigManager;
import util.MySQLStorage;

public final class ClanPruginV2 extends JavaPlugin {

    @Getter
    private static ClanPruginV2 instance;

    private InventoryClan inventoryClan;
    private ClanViborMenu clanViborMenu;
    private MySQLStorage mySQLStorage;

    @Override
    public void onEnable() {
        instance = this;
        ConfigManager.loadConfig();
        inventoryClan = new InventoryClan(this);
        mySQLStorage = new MySQLStorage();
        clanViborMenu = new ClanViborMenu(this);
        EventOnPlayerChat eventOnPlayerChat = new EventOnPlayerChat(mySQLStorage, this);
        this.getCommand("clan").setExecutor(new ClanMainMenuCommand(this));
        getServer().getPluginManager().registerEvents(new EventonInventoryClick(this, eventOnPlayerChat), this);
        getServer().getPluginManager().registerEvents(new EventOnPlayerChat(mySQLStorage, this), this);

    }
    @Override
    public void onDisable() {
        if (mySQLStorage != null) {
            mySQLStorage.close();
        } else {
            getLogger().warning("mySQLStorage is null, cannot close.");
        }
    }
    public InventoryClan getInventoryClan() {
        return inventoryClan;
    }
    public ClanViborMenu getClanViborMenu(){
        return clanViborMenu;
    }
    public MySQLStorage getMySQLStorage(){
        return mySQLStorage;
    }
}
