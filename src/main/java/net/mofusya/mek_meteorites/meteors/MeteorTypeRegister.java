package net.mofusya.mek_meteorites.meteors;

import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MeteorTypeRegister {
    private final Map<ResourceLocation, IMeteorType> R;

    public MeteorTypeRegister() {
        R = new HashMap<>();
    }

    public <TYPE extends IMeteorType> TYPE register(TYPE meteorType) {
        R.put(meteorType.getId(), meteorType);
        return meteorType;
    }

    public Map<ResourceLocation, IMeteorType> get() {
        return new HashMap<>(this.R);
    }

    public void register(){
        MeteorTypeManager.register(this);
    }
}
