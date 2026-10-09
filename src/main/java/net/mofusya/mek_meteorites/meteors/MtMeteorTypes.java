package net.mofusya.mek_meteorites.meteors;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;
import net.mofusya.mek_meteorites.C;
import net.mofusya.mek_meteorites.meteors.meteor.MeteorType;

import java.util.List;
import java.util.function.Supplier;

import static net.mofusya.mek_meteorites.R.METEOR_TYPE_REGISTRY_KEY;

public class MtMeteorTypes {

    public static final DeferredRegister<IMeteorType> R = DeferredRegister.create(new ResourceLocation(C.MOD_ID, "meteor_type"), C.MOD_ID);

    public static final Supplier<IForgeRegistry<IMeteorType>> REGISTRY = R.makeRegistry(RegistryBuilder::new);

    public static final RegistryObject<IMeteorType> TEST = R.register("test1", () -> new MeteorType(C.MOD_ID, "test1") {
        @Override
        public int getRadius() {
            return 5;
        }

        @Override
        public List<Block> getMainBlocks() {
            return List.of(Blocks.DEEPSLATE, Blocks.COBBLED_DEEPSLATE, Blocks.DEEPSLATE_GOLD_ORE);
        }

        @Override
        public int getCoreRadius() {
            return 4;
        }

        @Override
        public List<Block> getCoreBlocks() {
            return List.of(Blocks.GOLD_BLOCK, Blocks.SAND);
        }
    });

    /*
    public static final MeteorTypeRegister R = new MeteorTypeRegister();
    public static final IMeteorType TEST2 = R.register(new MeteorType(C.MOD_ID, "test2") {
        @Override
        public int getRadius() {
            return 5;
        }

        @Override
        public List<Block> getMainBlocks() {
            return List.of(Blocks.DEEPSLATE, Blocks.COBBLED_DEEPSLATE, Blocks.DEEPSLATE_GOLD_ORE);
        }

        @Override
        public int getCoreRadius() {
            return 3;
        }

        @Override
        public List<Block> getCoreBlocks() {
            return List.of(Blocks.GOLD_BLOCK, Blocks.SAND);
        }
    });
     */
}