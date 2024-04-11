package deepslater.equilibrium.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;

@Mixin(SculkVeinBlock.class)
public abstract class SculkVeinMixin extends MultifaceBlock implements SculkBehaviour, SimpleWaterloggedBlock {
    @Shadow
    private MultifaceSpreader veinSpreader;

    public SculkVeinMixin(Properties pProperties) {
        super(pProperties);
    }

    @Inject(method = "attemptUseCharge", at = @At("HEAD"), cancellable = true)
    protected void onAttemptUseCharge(
            SculkSpreader.ChargeCursor pCursor,
            LevelAccessor pLevel,
            BlockPos pPos,
            RandomSource pRandom,
            SculkSpreader pSpreader,
            boolean pShouldConvertBlocks,
            CallbackInfoReturnable<Integer> cir
    ) {
        if (pShouldConvertBlocks && this.attemptSoulSculk(pSpreader, pLevel, pCursor.getPos(), pRandom)) {
            cir.setReturnValue(pCursor.getCharge());
            cir.cancel();
        }
    }

    private boolean attemptSoulSculk(SculkSpreader pSpreader, LevelAccessor pLevel, BlockPos pPos, RandomSource pRandom) {
        BlockState $$4 = pLevel.getBlockState(pPos);
        TagKey<Block> $$5 = BlockTags.WITHER_SUMMON_BASE_BLOCKS;
        Iterator var7 = Direction.allShuffled(pRandom).iterator();

        while(var7.hasNext()) {
            Direction $$6 = (Direction)var7.next();
            if (hasFace($$4, $$6)) {
                BlockPos $$7 = pPos.relative($$6);
                BlockState $$8 = pLevel.getBlockState($$7);
                if ($$8.is($$5)) {
                    BlockState $$9 = Blocks.SCULK.defaultBlockState();
                    pLevel.setBlock($$7, $$9, 3);
                    Block.pushEntitiesUp($$8, $$9, pLevel, $$7);
                    pLevel.playSound((Player) null, $$7, SoundEvents.SCULK_BLOCK_SPREAD, SoundSource.BLOCKS, 1.0F, 1.0F);
                    this.veinSpreader.spreadAll($$9, pLevel, $$7, pSpreader.isWorldGeneration());
                    Direction $$10 = $$6.getOpposite();
                    Direction[] var13 = DIRECTIONS;
                    int var14 = var13.length;

                    for(int var15 = 0; var15 < var14; ++var15) {
                        Direction $$11 = var13[var15];
                        if ($$11 != $$10) {
                            BlockPos $$12 = $$7.relative($$11);
                            BlockState $$13 = pLevel.getBlockState($$12);
                            if ($$13.is(this)) {
                                this.onDischarged(pLevel, $$13, $$12, pRandom);
                            }
                        }
                    }

                    return true;
                }
            }
        }

        return false;
    }
}