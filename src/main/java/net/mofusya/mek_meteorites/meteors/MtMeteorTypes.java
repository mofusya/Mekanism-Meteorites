package net.mofusya.mek_meteorites.meteors;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.mofusya.mek_meteorites.C;
import net.mofusya.mek_meteorites.meteors.meteor.MeteorType;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MtMeteorTypes {

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
}