package net.mofusya.mek_meteorites.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.mofusya.mek_meteorites.C;
import net.mofusya.mek_meteorites.meteors.IMeteorType;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MtJsonConfigUtils {
    public static double getRadiusMultiplier() {
        JsonObject json = getBaseConfig();

        return GsonHelper.getAsDouble(json, C.METEOR_SIZE_MULTIPLIER);
    }

    public static double getCoreRadiusMultiplier() {
        JsonObject json = getBaseConfig();

        return GsonHelper.getAsDouble(json, C.METEOR_CORE_SIZE_MULTIPLIER);
    }

    public static List<IMeteorType> getCustomMeteorTypes() {
        JsonObject json = getAdvancedConfig();
        JsonArray customMeteorTypeObjects = GsonHelper.getAsJsonArray(json, C.CUSTOM_METEOR_TYPES);
        return customMeteorTypeObjects.asList().stream().map(JsonElement::getAsJsonObject).map(MtJsonConfigUtils::deserializeMeteorTypeFromJson).toList();
    }

    public static IMeteorType deserializeMeteorTypeFromJson(JsonObject meteorTypeObject) {
        return new IMeteorType() {
            @Override
            public ResourceLocation getId() {
                return new ResourceLocation(GsonHelper.getAsString(meteorTypeObject, C.ID));
            }

            @Override
            public int getRadius() {
                JsonPrimitive radiusObject = meteorTypeObject.getAsJsonPrimitive(C.SIZE);
                return radiusObject == null ? IMeteorType.super.getRadius() : radiusObject.getAsInt();
            }

            @Override
            public List<Block> getMainBlocks() {
                JsonArray mainBlocksObject = meteorTypeObject.getAsJsonArray(C.MAIN_BLOCKS);
                return mainBlocksObject == null ? IMeteorType.super.getMainBlocks() : mainBlocksObject.asList().stream().map(JsonElement::getAsString).map(ResourceLocation::new).map(ForgeRegistries.BLOCKS::getValue).toList();
            }

            @Override
            public int getCoreRadius() {
                JsonPrimitive coreRadiusObject = meteorTypeObject.getAsJsonPrimitive(C.CORE_SIZE);
                return coreRadiusObject == null ? IMeteorType.super.getCoreRadius() : coreRadiusObject.getAsInt();
            }

            @Override
            public @Nullable List<Block> getCoreBlocks() {
                JsonArray coreBlocksObject = meteorTypeObject.getAsJsonArray(C.CORE_BLOCKS);
                return coreBlocksObject == null ? IMeteorType.super.getCoreBlocks() : coreBlocksObject.asList().stream().map(JsonElement::getAsString).map(ResourceLocation::new).map(ForgeRegistries.BLOCKS::getValue).toList();
            }
        };
    }

    public static JsonObject getBaseConfig() {
        return MtJsonConfig.BASE_SERVER_CONFIG.get();
    }

    public static JsonObject getAdvancedConfig() {
        return MtJsonConfig.ADVANCED_SERVER_CONFIG.get();
    }
}
