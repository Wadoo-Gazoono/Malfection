package com.wadoo.stroder.common.registry;

import com.wadoo.stroder.StroderMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class StroderSounds{
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, StroderMod.MOD_ID);


    public static final RegistryObject<SoundEvent> WAIL = SOUNDS.register("entity.stroder.wail", () -> new SoundEvent(new ResourceLocation(StroderMod.MOD_ID, "entity.stroder.wail")));
}
