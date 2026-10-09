package net.mofusya.mek_meteorites;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;
import net.mofusya.mek_meteorites.meteors.IMeteorType;
import net.mofusya.mek_meteorites.meteors.meteor.MeteorType;
import org.jetbrains.annotations.Nullable;

public class R {
    /*
    public static final ResourceKey<Registry<IMeteorType>> METEOR_TYPE_REGISTRY_KEY = registerKey(IMeteorType.class, "meteor_type");
    @Nullable
    private static IForgeRegistry<IMeteorType> METEOR_TYPE_REGISTRY;
    public static final IMeteorType METEOR_TYPE_DEFAULT = new MeteorType(C.MOD_ID, "default_stone");

    public static IForgeRegistry<IMeteorType> getMeteorTypeRegistry(){
        if (METEOR_TYPE_REGISTRY == null){
            METEOR_TYPE_REGISTRY = RegistryManager.ACTIVE.getRegistry(METEOR_TYPE_REGISTRY_KEY);
        }
        return METEOR_TYPE_REGISTRY;
    }

    private static <T> ResourceKey<Registry<T>> registerKey(Class<T> keuClass, String id){
        return ResourceKey.createRegistryKey(new ResourceLocation(C.MOD_ID, id));
    }
     */
}
