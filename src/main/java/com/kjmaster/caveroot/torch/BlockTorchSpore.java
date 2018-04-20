package com.kjmaster.caveroot.torch;

import com.kjmaster.caveroot.CaveRoot;
import com.kjmaster.caveroot.proxy.CommonProxy;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class BlockTorchSpore extends BlockCrops {

    public static EnumPlantType FUNGUS = EnumPlantType.getPlantType("Fungus");

    public BlockTorchSpore() {
        this.setUnlocalizedName("torch_spore_block");
        this.setRegistryName("torch_spore_block");
        this.setHardness(0.1F);
        this.setResistance(0.1F);
        this.setCreativeTab(CaveRoot.caveRootTab);
    }

    @Override
    protected Item getSeed() {
        return CommonProxy.torchSporeItem;
    }

    @Override
    protected Item getCrop() {
        return CommonProxy.torchSporeItem;
    }

    @Override
    public int getMaxAge() {
        return 1;
    }

    @Override
    public void grow(World worldIn, BlockPos pos, IBlockState state) {
        int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
        int j = this.getMaxAge();

        if (i > j)
        {
            i = j;
        }
        if (i == 0) {
            worldIn.setBlockState(pos, this.withAge(i), 2);
        } else {
            worldIn.setBlockState(pos, CommonProxy.torchFungiBlock.getDefaultState(), 2);
        }
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        int i = this.getAge(state);

        if (i < this.getMaxAge()) {
            float f = getGrowthChance(this, worldIn, pos);

            if (ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt((int) (25.0F / f) + 1) == 0)) {
                worldIn.setBlockState(pos, this.withAge(i + 1), 2);
                ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
            }
        }
    }



    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
        IBlockState soilState = world.getBlockState(pos);
        Material soilMaterial = soilState.getMaterial();
        return !soilMaterial.isLiquid() && soilMaterial.isSolid() && soilMaterial.isOpaque();
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return false;
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
        IBlockState soil = worldIn.getBlockState(pos.down());
        return canSustainPlant(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this);
    }



    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return FUNGUS;
    }
}
