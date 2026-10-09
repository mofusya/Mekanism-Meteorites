package net.mofusya.mek_meteorites.meteors;

import net.minecraft.resources.ResourceLocation;
import net.mofusya.ornatelib.util.ArrayMap;

import java.util.HashMap;
import java.util.Map;

public class MeteorTypeManager {
    private static final Map<ResourceLocation, IMeteorType> R = new HashMap<>();

    public static void register(MeteorTypeRegister registries){
        R.putAll(registries.get());
    }

    public static Map<ResourceLocation, IMeteorType> get() {
        return new HashMap<>(R);
    }
}
