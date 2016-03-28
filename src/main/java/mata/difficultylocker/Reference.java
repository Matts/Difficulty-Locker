package mata.difficultylocker;

import com.google.common.base.Throwables;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Matt on 2/28/2016.
 */
public class Reference {

    private static String getProp(String id) {
        if (prop == null) {
            try {
                prop = new Properties();
                InputStream stream = Reference.class
                        .getResourceAsStream("reference.properties");
                prop.load(stream);
                stream.close();
            } catch (Exception e) {
                prop = null;
                Throwables.propagate(e);
            }
        }
        return prop.getProperty(id);
    }

    private static Properties prop;

    public static final String MOD_ID = "difficultylocker";
    public static final String MOD_NAME = "Difficulty Locker";
    public static final String MOD_VERSION = getProp("version");

    public static final Integer LOCK_BUTTON_ID = 8457894;
    public static final Integer DIFFICULTY_BUTTON_ID=11;



}
