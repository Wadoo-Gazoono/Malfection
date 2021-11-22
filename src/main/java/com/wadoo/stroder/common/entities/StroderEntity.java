package com.wadoo.stroder.common.entities;

import com.wadoo.stroder.common.entities.ai.StroderBoomGoal;
import com.wadoo.stroder.common.registry.StroderSounds;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public class StroderEntity extends PathfinderMob implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final EntityDataAccessor<Boolean> SHAKING = SynchedEntityData.defineId(StroderEntity.class, EntityDataSerializers.BOOLEAN);

    public StroderEntity(EntityType<? extends StroderEntity> stroder, Level level) {
        super(stroder, level);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(this.getShaking()){
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.stroder.shake", true));
            return PlayState.CONTINUE;
        }
        if(event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.stroder.walk", true));
            return PlayState.CONTINUE;
        }
        else{
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.stroder.idle", true));
            return PlayState.CONTINUE;
        }

        }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(1, new StroderBoomGoal(this));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, StroderEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SHAKING, false);
    }

    public void setShaking(boolean shaking){
        this.entityData.set(SHAKING, shaking);
    }

    public boolean getShaking(){
        return this.entityData.get(SHAKING);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<StroderEntity>(this, "controller", 8, this::predicate));

    }

    @Override
    public void tick() {
        super.tick();
        this.playSound(StroderSounds.WAIL.get(), 1.0f, 1.0f);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return StroderSounds.WAIL.get();
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

}
