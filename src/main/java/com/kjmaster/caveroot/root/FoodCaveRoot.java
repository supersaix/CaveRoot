package com.kjmaster.caveroot.root;

import com.kjmaster.caveroot.CaveRoot;
import com.kjmaster.caveroot.config.ModConfig;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class FoodCaveRoot extends ItemFood {

    public FoodCaveRoot() {
        super(ModConfig.foodValue, (float)ModConfig.saturationValue, ModConfig.isWolfFood);
        setUnlocalizedName("cave_root");
        setRegistryName("cave_root");
        setCreativeTab(CreativeTabs.FOOD);
    }

    public static void registerItemModel(Item item) {
        CaveRoot.proxy.registerItemRenderer(item, 0, "cave_root");
    }

}
