package com.wadoo.stroder.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.wadoo.stroder.StroderMod;
import com.wadoo.stroder.common.entities.StroderEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;
import software.bernie.geckolib3.util.RenderUtils;

import java.util.Optional;

public class StroderArmourLayer extends GeoLayerRenderer {
    private static final ResourceLocation TEXTURE = new ResourceLocation(StroderMod.MOD_ID, "textures/entity/stroder_hat.png");
    private static final ResourceLocation MODEL = new ResourceLocation(StroderMod.MOD_ID, "geo/stroder.geo.json");
    private static final ResourceLocation HATMODEL = new ResourceLocation(StroderMod.MOD_ID, "geo/stroder_hat.geo.json");
    public Entity entityLivingBaseIn;
    public float partialTicks;
    public StroderArmourLayer(IGeoRenderer<StroderEntity> entityRendererIn) {
        super(entityRendererIn);
    }


    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, net.minecraft.world.entity.Entity entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        matrixStackIn.pushPose();
        matrixStackIn.translate(0.0d, 0.89d, 0.0d);
        GeoModel model = this.getEntityModel().getModel(MODEL);
        GeoBone e = model.getBone("head").get();
        RenderUtils.translate(e, matrixStackIn);
        RenderType cameo =  RenderType.armorCutoutNoCull(TEXTURE);
        this.getRenderer().render(this.getEntityModel().getModel(HATMODEL), entityLivingBaseIn, partialTicks, cameo, matrixStackIn, bufferIn,
                bufferIn.getBuffer(cameo), packedLightIn, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
        matrixStackIn.popPose();

    }

}