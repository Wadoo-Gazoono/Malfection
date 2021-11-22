package com.wadoo.stroder.common.entities.ai;

import com.wadoo.stroder.common.entities.StroderEntity;
import com.wadoo.stroder.common.registry.StroderSounds;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

public class StroderBoomGoal extends Goal {
    protected final StroderEntity entity;
    private int timer = 0;
    public StroderBoomGoal(StroderEntity entity){
        this.entity = entity;
    }

    @Override
    public boolean canUse() {
       return true;
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
                for (Player player : this.entity.level.getEntitiesOfClass(Player.class, this.entity.getBoundingBox().inflate(10.2D, 4.0D, 10.2D))) {
                    if (!player.isCreative()) {
                        this.entity.setShaking(true);
                    }
                }
                this.entity.setShaking(true);
                this.entity.getNavigation().stop();
                this.timer++;
                if(this.timer > 5 && this.timer < 25) {
                    for (Player player : this.entity.level.getEntitiesOfClass(Player.class, this.entity.getBoundingBox().inflate(10.2D, 4.0D, 10.2D))) {
                        if (player.distanceToSqr(this.entity) < 25.0d && !player.isCreative()) {
                            this.entity.getLookControl().setLookAt(player, 30f, 30f);
                            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 10, 1), this.entity);
                            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 2), this.entity);
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
