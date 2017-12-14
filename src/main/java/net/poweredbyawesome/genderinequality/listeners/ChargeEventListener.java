package net.poweredbyawesome.genderinequality.listeners;

import net.poweredbyawesome.genderinequality.GenderInequality;
import net.poweredbyawesome.swearjar.events.PlayerChargeEvent;
import net.poweredbyhate.gender.special.Snowflake;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by Lax on 12/9/2017.
 */
public class ChargeEventListener implements Listener {

    @EventHandler
    public void onCharge(PlayerChargeEvent ev) {
        Snowflake snowflake = GenderInequality.mentalIllness.getSnowflake(ev.getPlayer());
        if (GenderInequality.instance.base.get("SwearJar").containsKey(snowflake.getGender().getName().toLowerCase())) { // ¯\_(ツ)_/¯
            ev.setSwearPrice(ev.getSwearPrice() * GenderInequality.instance.base.get("SwearJar").get(snowflake.getGender().getName().toLowerCase()));
        }
    }
}
