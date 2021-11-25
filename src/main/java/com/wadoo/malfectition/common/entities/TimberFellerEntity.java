package com.wadoo.malfectition.common.entities;

import com.wadoo.malfectition.common.entities.ai.BreakLogGoal;
import com.wadoo.malfectition.common.entities.ai.FindLogGoal;
import com.wadoo.malfectition.common.entities.ai.StroderWailGoal;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class TimberFellerEntity extends PathfinderMob implements IAnimatable, IAnimationTickable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final EntityDataAccessor<Boolean> CHOPPING = SynchedEntityData.defineId(StroderEntity.class, EntityDataSerializers.BOOLEAN);

    public TimberFellerEntity(EntityType<? extends TimberFellerEntity> entity, Level level) {
        super(entity, level);
    }
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(this.getChopping()) {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.timber_feller.chop", true));
            return PlayState.CONTINUE;
        }
        if(event.isMoving()){
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.timber_feller.walk", true));
            return PlayState.CONTINUE;
        }
        else{
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.timber_feller.idle", true));
            return PlayState.CONTINUE;
        }

    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CHOPPING, false);
    }

    public void setChopping(boolean chopping){
        this.entityData.set(CHOPPING, chopping);
    }

    public boolean getChopping(){
        return this.entityData.get(CHOPPING);
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(1, new FindLogGoal(this));
        this.goalSelector.addGoal(1, new BreakLogGoal(this));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, TimberFellerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<TimberFellerEntity>(this, "controller", 5, this::predicate));

    }

    @Override
    public void tick() {
        super.tick();
        if(tickCount < 4){
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE, 1));
        }
    }

    @Override
    protected InteractionResult mobInteract(Player p_21472_, InteractionHand p_21473_) {
        this.setItemSlot(EquipmentSlot.MAINHAND,p_21472_.getItemBySlot(EquipmentSlot.MAINHAND));
        return super.mobInteract(p_21472_, p_21473_);
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public int tickTimer() {
        return this.tickCount;
    }
}
