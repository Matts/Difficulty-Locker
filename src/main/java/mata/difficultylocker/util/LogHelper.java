package mata.difficultylocker.util;

import cpw.mods.fml.common.FMLLog;
import mata.difficultylocker.Reference;
import org.apache.logging.log4j.Level;

/**
 * Created by Matt on 2/28/2016.
 */
public class LogHelper {
    public static void log(Level logLevel, Object object) {
        FMLLog.log(Reference.MOD_NAME, logLevel, String.valueOf(object));
    }

    public static void info(Object object) {
        log(Level.INFO, object);
    }

    public static void warn(Object object) {
        log(Level.WARN, object);
    }

    public static void debug(Object object){
        log(Level.DEBUG, object);
    }
}
