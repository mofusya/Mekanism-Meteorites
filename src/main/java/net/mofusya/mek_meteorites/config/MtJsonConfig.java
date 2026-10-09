package net.mofusya.mek_meteorites.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.mofusya.mek_meteorites.C;
import net.mofusya.ornatelib.config.JsonConfig;

public class MtJsonConfig {

    public static final JsonConfig BASE_SERVER_CONFIG = new JsonConfig(C.MOD_ID + "-server", () -> {
        JsonObject json = new JsonObject();
        json.addProperty(C.METEOR_SIZE_MULTIPLIER, 1.0);
        json.addProperty(C.METEOR_CORE_SIZE_MULTIPLIER, 1.0);

        return json;
    });

    public static final JsonConfig ADVANCED_SERVER_CONFIG = new JsonConfig(C.MOD_ID + "-server-advanced", () -> {
        JsonObject json = new JsonObject();
        JsonArray meteorTypeObjects = new JsonArray();
        {
            JsonObject typeObject = new JsonObject();
            typeObject.addProperty(C.ID, C.MOD_ID + ":basic_stone");
            typeObject.addProperty(C.SIZE, 4);

            JsonArray mainBlocks = new JsonArray();
            mainBlocks.add("minecraft:stone");
            mainBlocks.add("minecraft:andesite");
            mainBlocks.add("minecraft:cobblestone");
            typeObject.add(C.MAIN_BLOCKS, mainBlocks);

            meteorTypeObjects.add(typeObject);
        }
        {
            JsonObject typeObject = new JsonObject();
            typeObject.addProperty(C.ID, C.MOD_ID + ":basic_diamond");
            typeObject.addProperty(C.SIZE, 5);

            JsonArray mainBlocks = new JsonArray();
            mainBlocks.add("minecraft:stone");
            mainBlocks.add("minecraft:andesite");
            mainBlocks.add("minecraft:cobblestone");
            typeObject.add(C.MAIN_BLOCKS, mainBlocks);

            typeObject.addProperty(C.CORE_SIZE, 4);

            JsonArray coreBlocks = new JsonArray();
            coreBlocks.add("minecraft:stone");
            coreBlocks.add("minecraft:cobblestone");
            coreBlocks.add("minecraft:diamond_ore");
            coreBlocks.add("minecraft:diamond_ore");
            coreBlocks.add("minecraft:diamond_ore");
            typeObject.add(C.CORE_BLOCKS, coreBlocks);

            meteorTypeObjects.add(typeObject);
        }
        json.add(C.CUSTOM_METEOR_TYPES, meteorTypeObjects);

        return json;
    });
}