package com.wadoo.malfectition.common.registry;


import com.wadoo.malfectition.Malfectition;
import com.wadoo.malfectition.common.entities.StroderEntity;
import com.wadoo.malfectition.common.entities.TimberFellerEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MalfectionEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, Malfectition.MOD_ID);

    public static final RegistryObject<EntityType<StroderEntity>> STRODER =
            ENTITIES.register("stroder",
                    ()->EntityType.Builder.of(StroderEntity::new, MobCategory.CREATURE)
                            .fireImmune()
                            .sized(0.45F,1.0F)
                            .build("stroder"));

    public static final RegistryObject<EntityType<TimberFellerEntity>> TIMBER_FELLER =
            ENTITIES.register("timber_feller",
                    ()->EntityType.Builder.of(TimberFellerEntity::new, MobCategory.CREATURE)
                            .fireImmune()
                            .sized(0.45F,1.0F)
                            .build("stroder"));
}
