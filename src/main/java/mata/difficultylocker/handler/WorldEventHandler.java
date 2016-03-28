package mata.difficultylocker.handler;

import com.google.common.collect.Maps;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mata.difficultylocker.DifficultyLocker;
import mata.difficultylocker.util.LogHelper;
import mata.difficultylocker.util.MessageHelper;
import mata.difficultylocker.util.WorldConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Mata on 2/28/2016.
 *
 * This MOD is Copyright © Matthew Smeets and is the intellectual property of the author. It may be not be reproduced under any circumstances except for personal, private use as long as it remains in its unaltered, unedited form. It may not be placed on any web site or otherwise distributed publicly without advance written permission. Use of this mod on any other website or as a part of any public display is strictly prohibited, and a violation of copyright.
 *
 */
public class WorldEventHandler {
    @SubscribeEvent
    public void joinEvent(EntityJoinWorldEvent event) {


        if (event.entity instanceof EntityPlayer) {
            
            if (DimensionManager.getCurrentSaveRootDirectory() != null) {
                if (DifficultyLocker.config.getConfig().hasCategory("worlds") && DifficultyLocker.config.getConfig().hasKey("worlds", DimensionManager.getCurrentSaveRootDirectory().getName())) {
                    LogHelper.info(StatCollector.translateToLocal("log.old_config_detected"));

                    Map<String, Property> map = Maps.newHashMap();
                    ConfigCategory category = DifficultyLocker.config.getConfig().getCategory("worlds");
                    for (Map.Entry<String, Property> entry : category.entrySet()) {
                        map.put(entry.getKey(), entry.getValue());
                    }

                    DifficultyLocker.config.getConfig().removeCategory(category);

                    for (Map.Entry<String, Property> entry : map.entrySet()) {
                        File file = new File(DimensionManager.getCurrentSaveRootDirectory().getParentFile().getAbsolutePath() + "/" + entry.getKey());
                        if (file.exists()) {
                            LogHelper.info(MessageHelper.localLogMessage("log.config_converting",entry.getKey()));
                            DifficultyLocker.worldConfig.lockDifficultyOnWorld(file, EnumDifficulty.getDifficultyEnum(entry.getValue().getInt()));
                        } else {
                            LogHelper.info(MessageHelper.localLogMessage("log.config_skipping",entry.getKey()));
                        }

                    }
                    DifficultyLocker.config.saveConfig();
                    LogHelper.info(StatCollector.translateToLocal("log.config_converted"));

                }


                if (DifficultyLocker.worldConfig.doesWorldHaveDifficultyLocked(DimensionManager.getCurrentSaveRootDirectory())) {
                    try {
                        EnumDifficulty enumdifficulty = DifficultyLocker.worldConfig.getLockedDifficulty(DimensionManager.getCurrentSaveRootDirectory());

                        MinecraftServer.getServer().func_147139_a(enumdifficulty);
                        Minecraft.getMinecraft().gameSettings.difficulty = enumdifficulty;

                        if (event.world.isRemote) {
                            MessageHelper.sendLocalisedMessage(event.entity, "chat.world_locked", enumdifficulty.name());
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (event.world.isRemote) {
                        MessageHelper.sendLocalisedMessage(event.entity, "chat.world_unlocked", null);
                    }
                }
            }
        }
    }
}
