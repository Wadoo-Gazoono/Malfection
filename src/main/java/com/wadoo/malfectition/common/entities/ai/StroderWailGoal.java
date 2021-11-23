package com.wadoo.malfectition.common.entities.ai;

import com.wadoo.malfectition.common.entities.StroderEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

public class StroderWailGoal extends Goal {
    protected final StroderEntity entity;
    private int timer = 0;
    public StroderWailGoal(StroderEntity entity){
        this.entity = entity;
    }

    @Override
    public boolean canUse() {
        for (Player player : this.entity.level.getEntitiesOfClass(Player.class, this.entity.getBoundingBox().inflate(10.2D, 4.0D, 10.2D))) {
            if (player != null && !player.isCreative()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void start() {
        super.start();
        timer = 0;
    }

    @Override
    public void tick() {
        super.tick();
        if(!this.entity.level.getEntitiesOfClass(Player.class, this.entity.getBoundingBox().inflate(10.2D, 4.0D, 10.2D)).isEmpty()){
            if(this.timer < 100){
                if(this.timer % 2 == 0 && this.timer > 5 && this.timer < 30){
                    this.entity.playSound(SoundEvents.STRIDER_DEATH, 1.8f, 2.15f + this.entity.getRandom().nextFloat() * 5);
                    this.entity.playSound(SoundEvents.STRIDER_HURT, 1.8f, 0.65f + this.entity.getRandom().nextFloat());
                }
                if(this.timer % 2 == 0 && this.timer > 50 && this.timer < 55) {
                    this.entity.playSound(SoundEvents.GRASS_BREAK, 1.8f, 0.75f);
                }
                if(this.timer > 48 || this.timer < 21){
                    this.entity.setDigging(true);
                }
                else{
                    this.entity.setDigging(false);
                }
                this.entity.setShaking(true);
                this.entity.getNavigation().stop();
                this.entity.setDeltaMovement(0d, -5d, 0d);
                this.timer++;
                if(this.timer > 5 && this.timer < 25) {
                    for (Player player : this.entity.level.getEntitiesOfClass(Player.class, this.entity.getBoundingBox().inflate(10.2D, 4.0D, 10.2D))) {
                        if (player.distanceToSqr(this.entity) < 45.0d && !player.isCreative()) {
                            this.entity.getLookControl().setLookAt(player, 30f, 30f);
                            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 30, 1), this.entity);
                            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 2), this.entity);
                        }
                    }
                }
                if(this.timer == 60){
                    this.entity.remove(Entity.RemovalReason.DISCARDED);
                }
            }
            else {
                this.entity.setShaking(false);
                this.timer = 0;
            }
        }
    }
}
