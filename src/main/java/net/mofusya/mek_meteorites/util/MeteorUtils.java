
package net.mofusya.mek_meteorites.util;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class MeteorUtils {

    private static final List<FallingBlockEntity> METEORS = new ArrayList<>();

    public static void tickMeteors(ServerLevel server){
        if (METEORS.isEmpty()) return;

        for (FallingBlockEntity meteor : METEORS) {
            if (meteor.horizontalCollision || meteor.onGround()){
                server.explode(null, meteor.getX(), meteor.getY(), meteor.getZ(), 5, Level.ExplosionInteraction.BLOCK);
                removeMeteor(meteor);
            } else if (meteor.getY() <= 0){
                removeMeteor(meteor);
            }
        }
    }

    public static void spawnMeteor(ServerLevel server, BlockPos pos) {
        BlockState state = Blocks.STONE.defaultBlockState();
        FallingBlockEntity entity = FallingBlockEntity.fall(server, pos, state);
        entity.setDeltaMovement(new Vec3(0, 2, 0));
        METEORS.add(entity);
    }

    public static void removeMeteor(FallingBlockEntity meteor){
        METEORS.remove(meteor);
        meteor.discard();
    }
}
