package mata.difficultylocker.util;

import com.sun.prism.shader.Texture_Color_Loader;
import cpw.mods.fml.client.config.GuiConfigEntries;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumDifficulty;
import scala.Char;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Matt on 3/27/2016.
 */
public class MessageHelper {

    //DARE TO CHANGE MEH
    public static boolean lgbt=false, randomColour=false;

    public static String localLogMessage(String unlocalized, String data){
        String message = StatCollector.translateToLocal(unlocalized);
        if(data!=null) {
            message = message.replace("%world%", data);

        }
        return message;
    }

    public static boolean sendLocalisedMessage(Entity entity, String unlocalized, String data){
        String message = StatCollector.translateToLocal(unlocalized);
        message = message.replace("%tag%", StatCollector.translateToLocal("chat.tag"));
        if(data!=null){
            if(EnumDifficulty.valueOf(data)!=null){
                message = message.replace("%sifficulty%",StatCollector.translateToLocal("difficulty."+EnumDifficulty.valueOf(data).name().toLowerCase()));
            }
        }

            List list = new ArrayList<String>();

            if(lgbt){
                list.add("§c");
                list.add("§6");
                list.add("§e");
                list.add("§a");
                list.add("§1");
                list.add("§5");
            }else if(randomColour){
                list.add("§0");
                list.add("§1");
                list.add("§2");
                list.add("§3");
                list.add("§4");
                list.add("§5");
                list.add("§6");
                list.add("§7");
                list.add("§8");
                list.add("§9");
                list.add("§a");
                list.add("§b");
                list.add("§c");
                list.add("§d");
                list.add("§e");
                list.add("§f");
            }
            if(lgbt||randomColour) {
                String msg = "";
                int rnd = 0;
                for (char c : message.toCharArray()) {
                    if (rnd >= list.size()) {
                        rnd = 0;
                    }
                    msg += list.get(rnd) + "" + c;
                    rnd++;
                }
                message = msg;
            }
        ((EntityPlayer) entity).addChatMessage(new ChatComponentText(message));
        return true;
    }

}
