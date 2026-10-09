package net.mofusya.mek_meteorites.meteors;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.mofusya.mek_meteorites.meteors.meteor.MeteorType;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.function.Supplier;

public class MeteorUtils {
    private static final Queue<MeteorRec> REMOVED_METEORS = new ArrayDeque<>();
    private static final List<MeteorRec> METEORS = new ArrayList<>();

    public static void tickMeteors(ServerLevel server) {
        if (METEORS.isEmpty()) return;

        for (MeteorRec meteor : METEORS) {
            IMeteorType meteorType = meteor.getMeteorType();
            for (FallingBlockEntity entity : meteor) {
                if (entity.onGround()) {
                    explodeOnLand(server, meteorType, entity.position());
                    placeMeteorite(server, meteorType, entity.position());
                    meteorType.onLand(server, meteor, BlockPos.containing(entity.position().subtract(0,  (double) meteorType.getRadius() * 0.3, 0)));
                    removeMeteor(meteor);
                    break;
                } else if (entity.getY() <= 0) {
                    removeMeteor(meteor);
                    break;
                }
            }
        }

        while (!REMOVED_METEORS.isEmpty()) {
            var removedMeteor = REMOVED_METEORS.poll();
            METEORS.remove(removedMeteor);
            removedMeteor.discard();
        }
    }

    private static void explodeOnLand(ServerLevel server, IMeteorType meteorType, Vec3 pos) {
        int radius = (int) (meteorType.getRadiusWithMult() * 1.25);

        for (int pX = radius; pX >= -radius; pX--) {
            for (int pY = radius; pY >= -radius; pY--) {
                for (int pZ = radius; pZ >= -radius; pZ--) {
                    if (pX * pX + pY * pY + pZ * pZ > radius * radius) continue;
                    double x = pos.x() + pX;
                    double y = pos.y() + pY + (double) radius / 2;
                    double z = pos.z() + pZ;
                    if (server.getBlockState(BlockPos.containing(x, y, z)).is(Blocks.AIR)) continue;

                    server.explode(null, x, y, z, 5, Level.ExplosionInteraction.BLOCK);
                }
            }
        }
    }

    private static void placeMeteorite(ServerLevel server, IMeteorType meteorType, Vec3 pos) {
        int radius = meteorType.getRadiusWithMult();
        int coreRadius = meteorType.getCoreRadiusWithMult();
        boolean hasCore = coreRadius > 0 && meteorType.getCoreBlockStates() != null;

        for (int pX = radius; pX >= -radius; pX--) {
            for (int pY = radius; pY >= -radius; pY--) {
                for (int pZ = radius; pZ >= -radius; pZ--) {
                    int pPosAllSquared = pX * pX + pY * pY + pZ * pZ;
                    if (pPosAllSquared > radius * radius) continue;

                    BlockState pState = getRandomBlockStateFrom(hasCore && pPosAllSquared <= coreRadius ?  meteorType.getCoreBlockStates() :  meteorType.getMainBlockStates());
                    BlockPos pPos = BlockPos.containing(pos.x() + pX, pos.y() + pY - (double) radius * 0.3, pos.z() + pZ);

                    server.setBlock(pPos, pState, Block.UPDATE_ALL);
                }
            }
        }
    }

    public static void spawnMeteor(ServerLevel server, IMeteorType meteorType, BlockPos pos) {
        int radius = meteorType.getRadiusWithMult();
        MeteorRec meteor = new MeteorRec(meteorType);

        for (int pX = radius; pX >= -radius; pX--) {
            for (int pY = radius; pY >= -radius; pY--) {
                for (int pZ = radius; pZ >= -radius; pZ--) {
                    int pPosAllSquared = pX * pX + pY * pY + pZ * pZ;
                    if (pPosAllSquared > radius * radius) continue;

                    BlockState state = getRandomBlockStateFrom(meteorType.getMainBlockStates());
                    FallingBlockEntity entity = FallingBlockEntity.fall(server, pos.offset(pX, pY, pZ), state);
                    entity.setDeltaMovement(new Vec3(0, 1, 0));
                    meteor.add(entity);
                }
            }
        }
        METEORS.add(meteor);
    }

    public static void spawnRandomMeteor(ServerLevel sever, BlockPos pos) {
        var allMeteorTypes = MeteorTypeManager.get().values().stream().toList();
        IMeteorType meteorType = allMeteorTypes.get(Mth.nextInt(RandomSource.create(), 0, allMeteorTypes.size() - 1));
        spawnMeteor(sever, meteorType, pos);
    }

    public static BlockState getRandomBlockStateFrom(List<Supplier<BlockState>> allBlockStates) {
        return allBlockStates.get(Mth.nextInt(RandomSource.create(), 0, allBlockStates.size() - 1)).get();
    }

    public static void removeMeteor(MeteorRec meteor) {
        REMOVED_METEORS.add(meteor);
    }

    public static void getRadius(MeteorType meteorType){
        
    }
}
