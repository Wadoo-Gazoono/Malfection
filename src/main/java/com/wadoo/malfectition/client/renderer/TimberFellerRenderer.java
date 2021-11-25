package com.wadoo.malfectition.client.renderer;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.wadoo.malfectition.Malfectition;
import com.wadoo.malfectition.client.model.StroderModel;
import com.wadoo.malfectition.client.model.TimberFellerModel;
import com.wadoo.malfectition.common.entities.StroderEntity;
import com.wadoo.malfectition.common.entities.TimberFellerEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class TimberFellerRenderer extends GeoEntityRenderer<TimberFellerEntity> {
    public TimberFellerEntity entity;
    public TimberFellerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new TimberFellerModel());
        this.shadowRadius = 0.25f;
    }

    @Override
    public void renderEarly(TimberFellerEntity entity, PoseStack stackIn, float ticks, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float partialTicks) {
        this.entity = entity;
        super.renderEarly(entity, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, partialTicks);
    }

    @Override
    public void renderRecursively(GeoBone bone, PoseStack stack, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (bone.getName().equals("item")) {
            stack.pushPose();
            stack.translate(0.25D, 0.15D, 0.0D);
            stack.mulPose(Vector3f.XP.rotationDegrees(-70.0F));
            stack.scale(0.8f, 0.8f, 0.8f);
            ItemStack itemstack = entity.getItemBySlot(EquipmentSlot.MAINHAND);
            Minecraft.getInstance().getItemRenderer().renderStatic(itemstack, ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND,
                    packedLightIn, packedOverlayIn, stack, this.rtb, 0);            stack.popPose();
            bufferIn = rtb.getBuffer(RenderType.entityTranslucent(whTexture));
        }
        super.renderRecursively(bone, stack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}
