package herman;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Hashtable;
import java.util.List;

public class Keys {

    File fi;
    Hashtable<String, Integer> hshT;
    int count = 0;//count of how many times key words appear in a hashtable

    public Keys(File fi, Hashtable<String, Integer> hshT){//creates keys class with attributes file and hashtable
        this.fi = fi;
        this.hshT = hshT;
    }

    public int keyWordFind() throws IOException{

        List<String> search = Files.readAllLines(Path.of(String.valueOf(this.fi)));//reads lines of file

        for (String s : search){ //for each string in the file

            if (s.trim().length() > 0){//removes blank space

                String[] temp = s.split("\\s");//separates words into string array

                for (String val : temp){//for each word in string array

                    if (this.hshT.containsKey(val.trim())){count++;}//if the hashtable contains the keyword it counts it

                }

            }

        }

        return count;

    }

}
