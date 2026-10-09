package net.mofusya.mek_meteorites.meteors;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;

import java.util.ArrayList;

public class MeteorRec extends ArrayList<FallingBlockEntity> {

    private final IMeteorType meteorType;

    public MeteorRec(IMeteorType meteorType) {
        this.meteorType = meteorType;
    }

    public IMeteorType getMeteorType() {
        return meteorType;
    }

    public void discard(){
        this.forEach(Entity::discard);
    }
}
