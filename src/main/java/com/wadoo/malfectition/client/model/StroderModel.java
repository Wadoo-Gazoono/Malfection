package com.wadoo.malfectition.client.model;

import com.wadoo.malfectition.Malfectition;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class StroderModel extends AnimatedTickingGeoModel {

    @Override
    public ResourceLocation getModelLocation(Object object) {
        return new ResourceLocation(Malfectition.MOD_ID, "geo/stroder.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Object object) {
        return new ResourceLocation(Malfectition.MOD_ID, "textures/entity/stroder.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Object animatable) {
        return new ResourceLocation(Malfectition.MOD_ID, "animations/stroder.animations.json");
    }
}
