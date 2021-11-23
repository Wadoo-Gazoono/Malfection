package com.wadoo.malfectition.client;


import com.wadoo.malfectition.Malfectition;
import com.wadoo.malfectition.client.renderer.StroderRenderer;
import com.wadoo.malfectition.common.registry.MalfectionEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Malfectition.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(MalfectionEntities.STRODER.get(), StroderRenderer::new);


    }

}