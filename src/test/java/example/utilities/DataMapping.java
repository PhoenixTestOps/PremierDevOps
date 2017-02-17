package example.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author : JULIA TUCKER
 * @date : 22/12/2016
 */
public class DataMapping extends HashMap<String, String> {

        public void fill_from_file(String filePath) throws IOException {
          
        	BufferedReader br = new BufferedReader(new FileReader(filePath));
        	String line;

            while((line=br.readLine())!=null){
                String str[] = line.split(",");
                    put(str[0], str[1]);
            }
            
            br.close();
        }

}