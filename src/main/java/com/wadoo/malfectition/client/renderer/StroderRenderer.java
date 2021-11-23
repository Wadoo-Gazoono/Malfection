package com.wadoo.malfectition.client.renderer;


import com.wadoo.malfectition.Malfectition;
import com.wadoo.malfectition.client.model.StroderModel;
import com.wadoo.malfectition.common.entities.StroderEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class StroderRenderer extends GeoEntityRenderer<StroderEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Malfectition.MOD_ID, "textures/entity/stroder_hat.png");
    private static final ResourceLocation MODEL = new ResourceLocation(Malfectition.MOD_ID, "geo/stroder.geo.json");
    private static final ResourceLocation HATMODEL = new ResourceLocation(Malfectition.MOD_ID, "geo/stroder_hat.geo.json");
    public StroderRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new StroderModel());
        this.shadowRadius = 0.25f;
        //this.addLayer(new StroderArmourLayer(this));
    }
}
