package com.wadoo.malfectition.common.registry;

import com.wadoo.malfectition.Malfectition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MalfectionSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Malfectition.MOD_ID);


    public static final RegistryObject<SoundEvent> WAIL = SOUNDS.register("entity.stroder.wail", () -> new SoundEvent(new ResourceLocation(Malfectition.MOD_ID, "entity.stroder.wail")));
}
