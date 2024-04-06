package deepslater.equilibrium.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class EchoesBlock extends Block {
    private final RandomSource random;

    public EchoesBlock(Properties pProperties) {
        super(pProperties);
        this.random = RandomSource.create();
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        Vec3 isMoving = pEntity.getDeltaMovement();
        if (pEntity.getType() == EntityType.PLAYER) {
            if (isMoving.get(Direction.Axis.X) > 0) {
                spawnStepParticles(pEntity);
            } else if (isMoving.get(Direction.Axis.Z) > 0) {
                spawnStepParticles(pEntity);
            }
        }

        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    private void spawnStepParticles(Entity pEntity) {
        Vec3 vec3 = pEntity.getDeltaMovement();
        pEntity.level()
                .addParticle(
                        ParticleTypes.SCULK_SOUL,
                        pEntity.getX() + (this.random.nextDouble() - 0.5) * (double)pEntity.getBbWidth(),
                        pEntity.getY() + 0.1,
                        pEntity.getZ() + (this.random.nextDouble() - 0.5) * (double)pEntity.getBbWidth(),
                        vec3.x * -0.2, 0.1, vec3.z * -0.2
                );
        float f = this.random.nextFloat() * 0.4F + this.random.nextFloat() > 0.9F ? 0.6F : 0.0F;
        pEntity.playSound(SoundEvents.SOUL_ESCAPE, f, 0.6F + this.random.nextFloat() * 0.4F);
    }

    @Override
    public void attack(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        spawnInteractParticles(pLevel, pPos, pPlayer);

        super.attack(pState, pLevel, pPos, pPlayer);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
        spawnInteractParticles(pLevel, pPos, pPlayer);

        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        return itemstack.getItem() instanceof BlockItem && (new BlockPlaceContext(pPlayer, pHand, itemstack, pHit)).canPlace() ? InteractionResult.PASS : InteractionResult.SUCCESS;
    }

    private void spawnInteractParticles(Level pLevel, BlockPos pPos, Player pPlayer) {
        double d0 = 0.6;
        RandomSource randomsource = pLevel.random;
        Direction direction = pPlayer.getDirection();
        Direction.Axis direction$axis = direction.getOpposite().getAxis();

        double d1 = direction$axis == Direction.Axis.X ? 0.5 + d0 * (double)direction.getStepX() : (double)randomsource.nextFloat();
        double d2 = direction$axis == Direction.Axis.Y ? 0.5 + d0 * (double)direction.getStepY() : (double)randomsource.nextFloat();
        double d3 = direction$axis == Direction.Axis.Z ? 0.5 + d0 * (double)direction.getStepZ() : (double)randomsource.nextFloat();
        pLevel.addParticle(ParticleTypes.SCULK_SOUL, (double)pPos.getX() + d1, (double)pPos.getY() + d2, (double)pPos.getZ() + d3, 0.0, 0.0, 0.0);

        float f = this.random.nextFloat() * 0.4F + this.random.nextFloat() > 0.9F ? 0.6F : 0.0F;
        pPlayer.playSound(SoundEvents.SOUL_ESCAPE, f, 0.6F + this.random.nextFloat() * 0.4F);
    }
}