package net.mofusya.mek_meteorites.meteors;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.mofusya.mek_meteorites.Meteorites;
import net.mofusya.mek_meteorites.config.MtJsonConfig;
import net.mofusya.mek_meteorites.config.MtJsonConfigUtils;
import net.mofusya.ornatelib.util.annotation.MethodsReturnNonNullByDefault;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@MethodsReturnNonNullByDefault
public interface IMeteorType {
    ResourceLocation getId();

    default int getRadius() {
        return 4;
    }

    default List<Supplier<BlockState>> getMainBlockStates() {
        List<Supplier<BlockState>> blockStates = new ArrayList<>();
        for (Block block : this.getMainBlocks()) {
            blockStates.add(block::defaultBlockState);
        }
        return blockStates;
    }

    default List<Block> getMainBlocks() {
        return List.of(Blocks.STONE);
    }

    default int getCoreRadius(){
        return 0;
    }

    @Nullable
    default List<Supplier<BlockState>> getCoreBlockStates(){
        if (this.getCoreBlocks() == null) return null;

        List<Supplier<BlockState>> blockStates = new ArrayList<>();
        for (Block block : this.getCoreBlocks()) {
            blockStates.add(block::defaultBlockState);
        }
        return blockStates;
    }

    @Nullable
    default List<Block> getCoreBlocks(){
        return null;
    }

    default void onLand(ServerLevel server, MeteorRec meteor, BlockPos pos){
    }

    default int getRadiusWithMult(){
        return (int) (this.getRadius() * MtJsonConfigUtils.getRadiusMultiplier());
    }

    default int getCoreRadiusWithMult(){
        return (int) (this.getCoreRadius() * MtJsonConfigUtils.getCoreRadiusMultiplier());
    }
}