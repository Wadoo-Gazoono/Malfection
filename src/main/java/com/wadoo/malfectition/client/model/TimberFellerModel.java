package com.wadoo.malfectition.client.model;

import com.wadoo.malfectition.Malfectition;
import com.wadoo.malfectition.common.entities.TimberFellerEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class TimberFellerModel extends AnimatedTickingGeoModel {

    @Override
    public ResourceLocation getModelLocation(Object object) {
        return new ResourceLocation(Malfectition.MOD_ID, "geo/timber_feller.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Object object) {
        return new ResourceLocation(Malfectition.MOD_ID, "textures/entity/timber_feller.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Object animatable) {
        return new ResourceLocation(Malfectition.MOD_ID, "animations/timber_feller.animations.json");
    }

}
