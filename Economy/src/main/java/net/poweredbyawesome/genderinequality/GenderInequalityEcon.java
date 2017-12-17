package net.poweredbyawesome.genderinequality;

import net.poweredbyawesome.genderinequality.listeners.ChargeEventListener;
import net.poweredbyawesome.genderinequality.listeners.InvoiceEventListener;
import net.poweredbyhate.gender.GenderPlugin;
import net.poweredbyhate.gender.MentalIllness;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.logging.Level;

public final class GenderInequalityEcon extends JavaPlugin {

    public static GenderInequalityEcon instance;
    public static MentalIllness mentalIllness;
    public HashMap<String, HashMap<String, Double>> base = new HashMap<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        for (String s : getConfig().getConfigurationSection("plugins").getKeys(false)) {
            base.put(s, new HashMap<>());
        }
        checkDependencies();
    }

    public void checkDependencies() {
        if (getServer().getPluginManager().getPlugin("Gender") != null) {
            getLogger().log(Level.INFO, "Plugin Found: Gender!");
            GenderPlugin genderPlugin = (GenderPlugin) Bukkit.getPluginManager().getPlugin("Gender");
            mentalIllness = genderPlugin.goMental();
        } else {
            getLogger().log(Level.SEVERE, "Gender plugin not found, disabling plugin.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        if (getServer().getPluginManager().getPlugin("MoneyOvertime") != null) {
            Bukkit.getPluginManager().registerEvents(new InvoiceEventListener(), this);
            getLogger().log(Level.INFO, "Plugin Found: MoneyOvertime!");
            for (String s : getConfig().getConfigurationSection("plugins.MoneyOvertime").getKeys(false)) {
                base.get("MoneyOvertime").put(s,getConfig().getDouble("plugins.MoneyOvertime."+s));
            }
        }
        if (getServer().getPluginManager().getPlugin("SwearJar") != null) {
            Bukkit.getPluginManager().registerEvents(new ChargeEventListener(), this);
            getLogger().log(Level.INFO, "Plugin Found: SwearJar!");
            for (String s : getConfig().getConfigurationSection("plugins.SwearJar").getKeys(false)) {
                base.get("SwearJar").put(s,getConfig().getDouble("plugins.SwearJar."+s));
            }
        }
    }
}