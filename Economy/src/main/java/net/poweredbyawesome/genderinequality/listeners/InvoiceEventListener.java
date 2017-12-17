package net.poweredbyawesome.genderinequality.listeners;

import net.poweredbyawesome.genderinequality.GenderInequalityEcon;
import net.poweredbyawesome.moneyovertime.events.PlayerInvoiceEvent;
import net.poweredbyhate.gender.special.Snowflake;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by Lax on 12/9/2017.
 */
public class InvoiceEventListener implements Listener {

    @EventHandler
    public void onInvoice(PlayerInvoiceEvent ev) {
        Snowflake snowflake = GenderInequalityEcon.mentalIllness.getSnowflake(ev.getPlayer());
        if (GenderInequalityEcon.instance.base.get("SwearJar").containsKey(snowflake.getGender().getName().toLowerCase())) { // (╭☞ ͠° ͜ʖ °)╭☞
            ev.setAmount(ev.getAmount()* GenderInequalityEcon.instance.base.get("MoneyOvertime").get(snowflake.getGender().getName().toLowerCase()));
        }
    }
}

