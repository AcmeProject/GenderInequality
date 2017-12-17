package net.poweredbyawesome.genderinequality;

import net.poweredbyhate.gender.GenderPlugin;
import net.poweredbyhate.gender.MentalIllness;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.logging.Level;

public final class GenderInequalityDMG extends JavaPlugin implements Listener {

    public MentalIllness mentalIllness;
    public HashMap<String, HashMap<String, Double>> base = new HashMap<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(this, this);
        for (String s : getConfig().getConfigurationSection("modifiers").getKeys(false)) {
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
        for (String s : getConfig().getConfigurationSection("modifiers.attacker").getKeys(false)) {
            base.get("attacker").put(s,getConfig().getDouble("modifiers.attacker."+s));
        }
        for (String s : getConfig().getConfigurationSection("modifiers.defender").getKeys(false)) {
            base.get("defender").put(s,getConfig().getDouble("modifiers.defender."+s));
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent ev) {
        if (ev.getDamager() instanceof Player) {
            String badguy = mentalIllness.getSnowflake((Player) ev.getDamager()).getGender().getName().toLowerCase();
            if (base.get("attacker").containsKey(badguy)) {
                ev.setDamage(ev.getDamage() * base.get("attacker").get(badguy));
            }
        }
        if (ev.getEntity() instanceof Player) {
            String goodguy = mentalIllness.getSnowflake((Player) ev.getEntity()).getGender().getName();
            if (base.get("defender").containsKey(goodguy)) {
                ev.setDamage(ev.getDamage() * base.get("defender").get(goodguy));
            }
        }
    }

}
