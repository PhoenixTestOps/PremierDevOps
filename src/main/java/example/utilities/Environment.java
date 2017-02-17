package example.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Environment {
	
	private static InputStream inputStream;
	private static Properties properties;
	
    public static Properties getProperties() throws IOException{
	
    	properties = new Properties();

		try{
			String env = "local";
			//String env = System.getProperty("env");
			String envFile = "src/main/resources/" + env + ".properties";
						
			inputStream = new FileInputStream(envFile);
 
			if (inputStream != null) {
				properties.load(inputStream);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			inputStream.close();
		}
		
		return properties;
	}
}
