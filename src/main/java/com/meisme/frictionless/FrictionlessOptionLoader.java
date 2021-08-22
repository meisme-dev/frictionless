package com.meisme.frictionless;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FrictionlessOptionLoader {
    public static float getFrictionValue(){
        float frictionValue = 1.1f;
        try {
            FileReader fr = new FileReader("optionsF.txt");
            Scanner sc = new Scanner(fr);
            while(sc.hasNextLine())
            {
                String line = sc.nextLine();
                if(line.startsWith("friction")){
                    frictionValue = Float.parseFloat(line.substring(line.lastIndexOf(":") + 1));
                }
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return frictionValue;
    }

}
