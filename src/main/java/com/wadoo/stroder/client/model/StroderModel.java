package com.wadoo.stroder.client.model;

import com.wadoo.stroder.StroderMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class StroderModel extends AnimatedGeoModel {

    @Override
    public ResourceLocation getModelLocation(Object object) {
        return new ResourceLocation(StroderMod.MOD_ID, "geo/stroder.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Object object) {
        return new ResourceLocation(StroderMod.MOD_ID, "textures/entity/stroder.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Object animatable) {
        return new ResourceLocation(StroderMod.MOD_ID, "animations/stroder.animations.json");
    }
}
