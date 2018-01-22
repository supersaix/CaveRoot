package com.kjmaster.caveroot.config;

import com.kjmaster.caveroot.CaveRoot;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config.LangKey("config.caveroot.title")
@Config(modid = CaveRoot.MODID, name = "Cave Root", category = "Settings")
public class ModConfig {

    @Config.LangKey("config.caveroot.spawnProbability")
    @Config.Comment("Float value for probability that a cave root will spawn")
    @Config.RangeDouble(min = 0.1D, max = 1.0D)
    public static double spawnProb = 0.5D;

    @Config.LangKey("config.caveroot.isHasteEnabled")
    @Config.Comment("Toggle getting haste from eating cave root")
    public static boolean isHasteEnabled = true;

    @Config.LangKey("config.caveroot.hasteLevel")
    @Config.Comment("The level of haste that is applied")
    @Config.RangeInt(min = 0, max = 4)
    public static int hasteLevel = 2;

    @Config.LangKey("config.caveroot.hasteDuration")
    @Config.Comment("The duration of the haste effect")
    @Config.RangeInt(min = 1, max = 6000)
    public static int hasteDuration = 600;

    @Config.LangKey("config.caveroot.hasteProbability")
    @Config.Comment("The probability to get haste when a cave root is eaten")
    @Config.RangeDouble(min = 0.0D, max = 1.0D)
    public static double hasteProbability = 1.0D;

    @Config.LangKey("config.caveroot.foodValue")
    @Config.Comment("The amount of food a cave root provides")
    @Config.RangeInt(min = 0, max = 9)
    public static int foodValue = 3;

    @Config.LangKey("config.caveroot.saturationValue")
    @Config.Comment("The saturation that a cave root provides")
    @Config.RangeDouble(min = 0.1D, max = 1.0D)
    public static double saturationValue = 0.6D;

    @Config.LangKey("config.caveroot.isWolfFood")
    @Config.Comment("Toggle if cave root can be used to feed wolves")
    public static boolean isWolfFood = false;

    @Config.LangKey("config.caveroot.dimensions")
    @Config.Comment("List dimension IDs for dimensions where cave root can spawn")
    public static int[] dimIDs = {0};

    @Mod.EventBusSubscriber(modid = CaveRoot.MODID)
    public static class ConfigSyncHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(CaveRoot.MODID)) {
                ConfigManager.sync(CaveRoot.MODID, Config.Type.INSTANCE);
            }
        }
    }
}
