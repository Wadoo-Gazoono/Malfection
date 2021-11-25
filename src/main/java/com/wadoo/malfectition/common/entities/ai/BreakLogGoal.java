package com.wadoo.malfectition.common.entities.ai;

import com.wadoo.malfectition.common.entities.TimberFellerEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.pathfinder.Path;

import java.util.Optional;
import java.util.function.Predicate;

public class BreakLogGoal extends Goal {
    public TimberFellerEntity entity;
    public int timer = 0;
    private final Predicate<BlockState> VALID_CHOPPING_BLOCKS = (blockState) -> {
        if (blockState.is(BlockTags.DARK_OAK_LOGS)) {
            return true;
        } else {
            return false;
        }
    };

    public BreakLogGoal(TimberFellerEntity entity){
        this.entity = entity;
    }

    @Override
    public void start() {
        super.start();
        this.timer = 0;
    }

    @Override
    public boolean canUse() {
        return this.entity.getItemBySlot(EquipmentSlot.MAINHAND).is(Items.IRON_AXE);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.findNearbyLog().isPresent()){
            this.entity.setChopping(true);
            if (this.entity.distanceToSqr(this.findNearbyLog().get().getX(), this.findNearbyLog().get().getY(), this.findNearbyLog().get().getZ()) < 5) {
                if(this.timer < 20) {
                    this.timer++;
                        if(this.timer == 14) {
                            this.entity.level.destroyBlock(this.findNearbyLog().get(), true, this.entity, 1);
                            this.entity.setChopping(false);
                        }
                }
                else{
                    this.entity.setChopping(false);
                    this.timer = 0;
                }
            }
            else{
                this.timer = 0;
                this.entity.setChopping(false);
            }
        }
        else{
            this.timer = 0;
            this.entity.setChopping(false);
        }
    }

    private Optional<BlockPos> findNearbyLog() {
        return this.findNearestBlock(this.VALID_CHOPPING_BLOCKS, 15.0D);
    }

    private Optional<BlockPos> findNearestBlock(Predicate<BlockState> p_28076_, double p_28077_) {
        BlockPos blockpos = this.entity.blockPosition();
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(int i = 0; (double)i <= p_28077_; i = i > 0 ? -i : 1 - i) {
            for(int j = 0; (double)j < p_28077_; ++j) {
                for(int k = 0; k <= j; k = k > 0 ? -k : 1 - k) {
                    for(int l = k < j && k > -j ? j : 0; l <= j; l = l > 0 ? -l : 1 - l) {
                        blockpos$mutableblockpos.setWithOffset(blockpos, k, i - 1, l);
                        if (blockpos.closerThan(blockpos$mutableblockpos, p_28077_) && p_28076_.test(this.entity.level.getBlockState(blockpos$mutableblockpos))) {
                            return Optional.of(blockpos$mutableblockpos);
                        }
                    }
                }
            }
        }

        return Optional.empty();
    }
}

