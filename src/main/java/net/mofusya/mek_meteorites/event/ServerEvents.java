package net.mofusya.mek_meteorites.event;

import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mofusya.mek_meteorites.util.MeteorUtils;

@Mod.EventBusSubscriber
public class ServerEvents {

    @SubscribeEvent
    public static void onServerTick(TickEvent.LevelTickEvent event){
        if (event.phase != TickEvent.Phase.END) return;
        if (!(event.level instanceof ServerLevel server)) return;

        MeteorUtils.tickMeteors(server);
    }
}
