package com.wadoo.stroder.common.entities;

import com.wadoo.stroder.StroderMod;
import com.wadoo.stroder.common.registry.StroderEntities;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = StroderMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class EntityAttributes {
    @SubscribeEvent
    public static void EntityAttributeCreationEvent(EntityAttributeCreationEvent event) {
        event.put(StroderEntities.STRODER.get(), Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 12.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.278D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.3D)
                .add(Attributes.ARMOR, 4.0D)
                .add(Attributes.FOLLOW_RANGE, 28.0D)
                .build());
    }
}