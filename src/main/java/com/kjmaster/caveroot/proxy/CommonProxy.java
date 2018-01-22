package com.kjmaster.caveroot.proxy;

import com.kjmaster.caveroot.config.ModConfig;
import com.kjmaster.caveroot.root.BlockCaveRoot;
import com.kjmaster.caveroot.root.FoodCaveRoot;
import net.minecraft.block.Block;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CommonProxy {

    public static Item caveRoot;

    public static Block caveRootBlock = new BlockCaveRoot();

    public void registerItemRenderer(Item item, int meta, String id) {}

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(caveRootBlock);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        if (ModConfig.isHasteEnabled) {
            caveRoot = new FoodCaveRoot()
                    .setPotionEffect(new PotionEffect(MobEffects.HASTE, ModConfig.hasteDuration, ModConfig.hasteLevel -1),
                            (float)ModConfig.hasteProbability);
        } else {
            caveRoot = new FoodCaveRoot();
        }
        Item itemBlock = new ItemBlock(caveRootBlock).setRegistryName(caveRootBlock.getRegistryName());
        event.getRegistry().register(caveRoot);
        event.getRegistry().register(itemBlock);
    }
}
