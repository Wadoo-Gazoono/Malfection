package com.wadoo.stroder.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.wadoo.stroder.StroderMod;
import com.wadoo.stroder.client.layer.StroderArmourLayer;
import com.wadoo.stroder.client.model.StroderModel;
import com.wadoo.stroder.common.entities.StroderEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class StroderRenderer extends GeoEntityRenderer<StroderEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(StroderMod.MOD_ID, "textures/entity/stroder_hat.png");
    private static final ResourceLocation MODEL = new ResourceLocation(StroderMod.MOD_ID, "geo/stroder.geo.json");
    private static final ResourceLocation HATMODEL = new ResourceLocation(StroderMod.MOD_ID, "geo/stroder_hat.geo.json");
    public StroderRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new StroderModel());
        this.shadowRadius = 0.25f;
        //this.addLayer(new StroderArmourLayer(this));
    }
}
