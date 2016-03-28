package mata.difficultylocker.handler;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by Mata on 2/28/2016.
 *
 * This MOD is Copyright © Matthew Smeets and is the intellectual property of the author. It may be not be reproduced under any circumstances except for personal, private use as long as it remains in its unaltered, unedited form. It may not be placed on any web site or otherwise distributed publicly without advance written permission. Use of this mod on any other website or as a part of any public display is strictly prohibited, and a violation of copyright.
 *
 */
public class ConfigHandler {
    private Configuration config;

    public ConfigHandler(File file){
        config = new Configuration(file);
        loadConfig();
        saveConfig();
    }

    /**
     * Loads Configuration File.
     */
    public void loadConfig(){
        config.load();
    }

    /**
     * Saves Configuration File.
     */
    public void saveConfig(){
        config.save();
    }

    /**
     * Get Configuration File.
     * @return Configuration
     */
    public Configuration getConfig(){
        return config;
    }

}
