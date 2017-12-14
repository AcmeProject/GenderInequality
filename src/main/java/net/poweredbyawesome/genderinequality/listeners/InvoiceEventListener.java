package net.poweredbyawesome.genderinequality.listeners;

import net.poweredbyawesome.genderinequality.GenderInequality;
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
        Snowflake snowflake = GenderInequality.mentalIllness.getSnowflake(ev.getPlayer());
        if (GenderInequality.instance.base.get("SwearJar").containsKey(snowflake.getGender().getName().toLowerCase())) { // (╭☞ ͠° ͜ʖ °)╭☞
            ev.setAmount(ev.getAmount()* GenderInequality.instance.base.get("MoneyOvertime").get(snowflake.getGender().getName().toLowerCase()));
        }
    }
}

