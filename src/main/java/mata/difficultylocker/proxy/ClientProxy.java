package mata.difficultylocker.proxy;

import mata.difficultylocker.handler.GuiEventHandler;
import mata.difficultylocker.handler.WorldEventHandler;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by Matt on 3/8/2016.
 */
public class ClientProxy extends CommonProxy {

    public void registerEvents(){
        MinecraftForge.EVENT_BUS.register(new WorldEventHandler());
        MinecraftForge.EVENT_BUS.register(new GuiEventHandler());
    }
}
