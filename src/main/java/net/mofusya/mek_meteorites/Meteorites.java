package net.mofusya.mek_meteorites;

import com.mojang.logging.LogUtils;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import net.mofusya.mek_meteorites.config.MtJsonConfig;
import net.mofusya.mek_meteorites.config.MtJsonConfigUtils;
import net.mofusya.mek_meteorites.meteors.IMeteorType;
import net.mofusya.mek_meteorites.meteors.MeteorTypeRegister;
import net.mofusya.mek_meteorites.meteors.MtMeteorTypes;
import net.mofusya.mek_meteorites.meteors.MeteorUtils;
import net.mofusya.ornatelib.registries.OrnateItemRegister;
import org.slf4j.Logger;

@Mod(Meteorites.MOD_ID)
public class Meteorites
{
    public static final String MOD_ID = "mek_meteorites";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final OrnateItemRegister R = new OrnateItemRegister(MOD_ID);
    public static final RegistryObject<Item> TEST_ITEM = R.register("test_item", TestItem::new);

    public Meteorites()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        R.register(modEventBus);
        MtMeteorTypes.R.register();
        MtJsonConfig.BASE_SERVER_CONFIG.load();
        MtJsonConfig.ADVANCED_SERVER_CONFIG.load();

        MeteorTypeRegister R = new MeteorTypeRegister();
        for (IMeteorType customMeteorType : MtJsonConfigUtils.getCustomMeteorTypes()) {
            R.register(customMeteorType);
        }
        R.register();

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.OP_BLOCKS)
            event.accept(TEST_ITEM);
    }

    public static class TestItem extends Item{

        public TestItem(Properties build) {
            super(build);
        }

        @Override
        public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
            if (level instanceof ServerLevel server){
                MeteorUtils.spawnRandomMeteor(server, player.getOnPos().relative(Direction.UP, 20));
                return InteractionResultHolder.success(player.getItemInHand(hand));
            }

            return super.use(level, player, hand);
        }
    }
}
