package com.kjmaster.caveroot.torch;

import com.kjmaster.caveroot.CaveRoot;
import com.kjmaster.caveroot.proxy.CommonProxy;
import com.kjmaster.kjlib.common.items.ItemBase;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.EnumHelper;

public class ItemTorchSpore extends ItemBase implements IPlantable {

    public ItemTorchSpore() {
        super("torch_spore", CaveRoot.caveRootTab);
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return BlockTorchSpore.FUNGUS;
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return CommonProxy.torchSporeBlock.getDefaultState();
    }

    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        net.minecraft.block.state.IBlockState state = worldIn.getBlockState(pos);
        Material soilMaterial = state.getMaterial();
        boolean flag = !soilMaterial.isLiquid() && soilMaterial.isSolid() && soilMaterial.isOpaque();
        if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && flag && worldIn.isAirBlock(pos.up()))
        {
            worldIn.setBlockState(pos.up(), CommonProxy.torchSporeBlock.getDefaultState());

            if (player instanceof EntityPlayerMP)
            {
                CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos.up(), itemstack);
            }

            worldIn.playSound(player, pos, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 1, 1);
            itemstack.shrink(1);
            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }

    public static void registerItemModel(Item item) {
        CaveRoot.proxy.registerItemRenderer(item, 0, "torch_spore");
    }
}
