package com.kjmaster.caveroot;

import com.kjmaster.caveroot.gen.RootGenerator;
import com.kjmaster.caveroot.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.xml.stream.events.Comment;

@Mod(modid = CaveRoot.MODID, version = CaveRoot.VERSION, dependencies = "required-after:kjlib")
public class CaveRoot
{
    public static final String MODID = "caveroot";
    public static final String VERSION = "1.1.0";

    public static CreativeTabs caveRootTab = new CreativeTabs(CaveRoot.MODID) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(CommonProxy.caveRoot);
        }
    };

    @SidedProxy(serverSide = "com.kjmaster.caveroot.proxy.CommonProxy", clientSide = "com.kjmaster.caveroot.proxy.ClientProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        GameRegistry.registerWorldGenerator(new RootGenerator(), 0);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
