package net.mofusya.mek_meteorites.meteors.meteor;

import net.minecraft.resources.ResourceLocation;
import net.mofusya.mek_meteorites.meteors.IMeteorType;

public class MeteorType implements IMeteorType {
    private final ResourceLocation id;

    public MeteorType(String modId, String id) {
        this(new ResourceLocation(modId, id));
    }

    public MeteorType(ResourceLocation id) {
        this.id = id;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }
}
