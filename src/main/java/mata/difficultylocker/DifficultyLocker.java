package mata.difficultylocker;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import mata.difficultylocker.handler.ConfigHandler;
import mata.difficultylocker.proxy.CommonProxy;
import mata.difficultylocker.util.LogHelper;
import mata.difficultylocker.util.WorldConfig;
import net.minecraft.util.StatCollector;

/**
 * Created by Mata on 2/28/2016.
 *
 * This MOD is Copyright © Matthew Smeets and is the intellectual property of the author. It may be not be reproduced under any circumstances except for personal, private use as long as it remains in its unaltered, unedited form. It may not be placed on any web site or otherwise distributed publicly without advance written permission. Use of this mod on any other website or as a part of any public display is strictly prohibited, and a violation of copyright.
 *
 */
@Mod(modid=Reference.MOD_ID, name = Reference.MOD_NAME)
public class DifficultyLocker {
    public static ConfigHandler config;
    public static WorldConfig worldConfig;

    @Mod.Instance
    DifficultyLocker difficultyLocker;

    @SidedProxy(clientSide="mata.difficultylocker.proxy.ClientProxy", serverSide="mata.difficultylocker.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        event.getModMetadata().version = Reference.MOD_VERSION;

        config = new ConfigHandler(event.getSuggestedConfigurationFile());
        worldConfig = new WorldConfig();


        LogHelper.debug("PreInit");

        proxy.registerEvents();


    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){

        if(event.getSide().isClient()) {
            LogHelper.debug("Init");
            LogHelper.info(StatCollector.translateToLocal("log.welcome"));
        }else{
            LogHelper.info(StatCollector.translateToLocal("log.not_compatable"));
            LogHelper.info(StatCollector.translateToLocal("log.disable"));
        }
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        if(event.getSide().isClient()) {
            LogHelper.debug("PostInit");
        }
    }

}
