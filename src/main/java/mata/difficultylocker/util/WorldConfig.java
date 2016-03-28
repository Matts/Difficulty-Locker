package mata.difficultylocker.util;

import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumDifficulty;

import java.io.*;

/**
 * Created by Matt on 2/29/2016.
 */
public class WorldConfig {

    public boolean lockDifficultyOnWorld(File file, EnumDifficulty difficulty){
            File locked = new File(file.getAbsolutePath() + "/locked");
            try {
                if (!locked.exists()) {
                    locked.createNewFile();
                } else {
                    locked.delete();
                }

                FileWriter fw = new FileWriter(locked);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(difficulty.name());
                bw.close();

                LogHelper.info("Locked Difficulty of " + file.getName() + " to " + difficulty.name());

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;


    }

    public boolean doesWorldHaveDifficultyLocked(File file){
        File locked = new File(file.getAbsolutePath() + "/locked");
        if(locked.exists()) {
            return true;
        }
        return false;
    }

    public EnumDifficulty getLockedDifficulty(File file) throws IOException {
        File locked = new File(file.getAbsolutePath() + "/locked");
        if(!locked.exists()) {
            LogHelper.warn(StatCollector.translateToLocal("log.file_defaulting"));
            return EnumDifficulty.HARD;
        }
        try {
            FileReader fw = new FileReader(locked.getAbsoluteFile());
            BufferedReader br = new BufferedReader(fw);

            String line;
            while ((line = br.readLine()) != null) {
                EnumDifficulty difficulty = EnumDifficulty.valueOf(line);
                return difficulty;
            }



        } catch (FileNotFoundException e) {
            LogHelper.warn(StatCollector.translateToLocal("log.file_not_found"));
            e.printStackTrace();
        }
        return EnumDifficulty.HARD;
    }


}
