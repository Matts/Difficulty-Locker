package mata.difficultylocker.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mata.difficultylocker.DifficultyLocker;
import mata.difficultylocker.Reference;
import mata.difficultylocker.util.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.DimensionManager;

/**
 * Created by Mata on 2/28/2016.
 *
 * This MOD is Copyright © Matthew Smeets and is the intellectual property of the author. It may be not be reproduced under any circumstances except for personal, private use as long as it remains in its unaltered, unedited form. It may not be placed on any web site or otherwise distributed publicly without advance written permission. Use of this mod on any other website or as a part of any public display is strictly prohibited, and a violation of copyright.
 *
 */
public class GuiEventHandler {

    /**
     * Hook into the gui event to manage the gui and buttons
     * @param guiEvent
     * @author Matthew Smeets
     */
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void guiEvent(GuiScreenEvent.InitGuiEvent guiEvent) {
        GuiScreen gui = guiEvent.gui;

        if(gui instanceof GuiOptions){
            if (DimensionManager.getCurrentSaveRootDirectory() != null) {
                if (Minecraft.getMinecraft().theWorld != null) {
                    guiEvent.buttonList.add(new GuiButton(Reference.LOCK_BUTTON_ID, gui.width / 2 - 155 + 1 % 2 * 160, (gui.height / 6 - 12 + 24), 150, 20, StatCollector.translateToLocal("button.lock_difficulty")));
                    if (guiEvent.buttonList.size() > 5) {
                        if (DifficultyLocker.worldConfig.doesWorldHaveDifficultyLocked(DimensionManager.getCurrentSaveRootDirectory())) {
                            for (int i = 0; i < guiEvent.buttonList.size(); i++) {
                                GuiButton btn = (GuiButton) guiEvent.buttonList.get(i);
                                if (btn.id == Reference.DIFFICULTY_BUTTON_ID) {
                                    btn.enabled = false;
                                }
                                if (btn.id == Reference.LOCK_BUTTON_ID) {
                                    btn.enabled = false;
                                }
                            }
                        }
                    }
                } else {
                    if (guiEvent.buttonList.size() > 5) {
                        for (int i = 0; i < guiEvent.buttonList.size(); i++) {
                            GuiButton btn = (GuiButton) guiEvent.buttonList.get(i);
                            if (btn.id == Reference.DIFFICULTY_BUTTON_ID) {
                                guiEvent.buttonList.remove(i);
                            }
                        }

                    }
                }
            }else{
                if (guiEvent.buttonList.size() > 5) {
                    for (int i = 0; i < guiEvent.buttonList.size(); i++) {
                        GuiButton btn = (GuiButton) guiEvent.buttonList.get(i);
                        if (btn.id == Reference.DIFFICULTY_BUTTON_ID) {
                            guiEvent.buttonList.remove(i);
                        }
                    }
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void actionPreformed(GuiScreenEvent.ActionPerformedEvent event) {
        GuiScreen gui = event.gui;

        if (gui instanceof GuiOptions) {
            if (DimensionManager.getCurrentSaveRootDirectory() != null) {
                if (event.button.id == Reference.LOCK_BUTTON_ID) {
                    if (Minecraft.getMinecraft().theWorld != null) {
                        for (int i = 0; i < event.buttonList.size(); i++) {
                            GuiButton btn = (GuiButton) event.buttonList.get(i);
                            if (btn.id == Reference.DIFFICULTY_BUTTON_ID) {
                                btn.enabled = false;
                            }
                            if (btn.id == Reference.LOCK_BUTTON_ID) {
                                btn.enabled = false;
                            }
                        }

                        DifficultyLocker.worldConfig.lockDifficultyOnWorld(DimensionManager.getCurrentSaveRootDirectory(), Minecraft.getMinecraft().theWorld.difficultySetting);

                    } else {
                        LogHelper.info(StatCollector.translateToLocal("log.impossible_trigger"));
                    }
                }
            }
        }
    }
}
