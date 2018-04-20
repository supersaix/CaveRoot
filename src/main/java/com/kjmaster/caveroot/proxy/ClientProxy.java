package com.kjmaster.caveroot.proxy;

import com.kjmaster.caveroot.CaveRoot;
import com.kjmaster.caveroot.root.FoodCaveRoot;
import com.kjmaster.caveroot.torch.ItemTorchSpore;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ClientProxy extends CommonProxy {

    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta,
                new ModelResourceLocation(CaveRoot.MODID + ":" + id, "inventory"));
    }

    @SubscribeEvent
    public static void modelEvent(ModelRegistryEvent event) {
        FoodCaveRoot.registerItemModel(caveRoot);
        ItemTorchSpore.registerItemModel(torchSporeItem);
    }
}
