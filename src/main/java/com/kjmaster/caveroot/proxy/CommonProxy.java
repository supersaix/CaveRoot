package com.kjmaster.caveroot.proxy;

        import com.kjmaster.caveroot.config.ModConfig;
        import com.kjmaster.caveroot.root.BlockCaveRoot;
        import com.kjmaster.caveroot.root.BlockDriedRoot;
        import com.kjmaster.caveroot.root.FoodCaveRoot;
        import com.kjmaster.caveroot.torch.BlockTorchFungi;
        import com.kjmaster.caveroot.torch.BlockTorchSpore;
        import com.kjmaster.caveroot.torch.ItemTorchSpore;
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

    public static Block driedRootBlock = new BlockDriedRoot();

    public static Block torchFungiBlock = new BlockTorchFungi().setLightLevel(0.9375F + 0.46875F);

    public static Block torchSporeBlock = new BlockTorchSpore().setLightLevel(0.703125F);

    public static Item torchSporeItem = new ItemTorchSpore();

    public void registerItemRenderer(Item item, int meta, String id) {}

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(caveRootBlock);
        event.getRegistry().register(driedRootBlock);
        event.getRegistry().register(torchFungiBlock);
        event.getRegistry().register(torchSporeBlock);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        if (ModConfig.caveRootCategory.isHasteEnabled) {
            caveRoot = new FoodCaveRoot()
                    .setPotionEffect(new PotionEffect(MobEffects.HASTE, ModConfig.caveRootCategory.hasteDuration, ModConfig.caveRootCategory.hasteLevel -1),
                            (float)ModConfig.caveRootCategory.hasteProbability);
        } else {
            caveRoot = new FoodCaveRoot();
        }
        event.getRegistry().register(caveRoot);
        event.getRegistry().register(torchSporeItem);
    }
}
