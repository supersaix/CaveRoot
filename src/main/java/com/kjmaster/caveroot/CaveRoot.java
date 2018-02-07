package com.kjmaster.caveroot;

import com.kjmaster.caveroot.gen.RootGenerator;
import com.kjmaster.caveroot.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = CaveRoot.MODID, version = CaveRoot.VERSION, dependencies = "required-after:kjlib")
public class CaveRoot
{
    public static final String MODID = "caveroot";
    public static final String VERSION = "1.0.2";

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
