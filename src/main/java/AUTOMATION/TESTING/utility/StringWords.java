package AUTOMATION.TESTING.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Properties;
import java.util.Random;

import org.apache.log4j.Logger;

public class StringWords {


	public static final Logger logg=Logger.getLogger(StringWords.class.getName());
	static String fs= FileSystems.getDefault().getSeparator();
	 static Properties prop;
	 public static String getRandomWord(String[] words) {
		
	        Random random = new Random();
	        int index = random.nextInt(words.length);
	        return words[index];
	    }	
	public static String enterTestData(String propertykey) {
		 try {
	      String getEnv=CommonUtilities.getEnvironment();
         prop = new Properties();
         if(getEnv.equals("dev")) {
		      FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+fs+"src"+fs+"main"+fs+"resources"+fs+"TestData"+fs+"dev"+"_testData.properties");
	            prop.load(fis);
	         // Replace "key" with the actual key in your property file
	            String[] words = prop.getProperty(propertykey).split(",");
	           String randomWord = getRandomWord(words);
	           logg.info("Randomly selected word: " + randomWord);
	           return randomWord.trim();
		 }else if(getEnv.equals("uat")) {
			 FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+fs+"src"+fs+"main"+fs+"resources"+fs+"TestData"+fs+"uat"+"_testData.properties");
			prop.load(fis);
			// Replace "key" with the actual key in your property file
            String[] words = prop.getProperty(propertykey).split(",");
           String randomWord = getRandomWord(words); 
           logg.info("Randomly selected word: " + randomWord);
           return randomWord.trim();
		}else if(getEnv.equals("newdc")){
			 FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+fs+"src"+fs+"main"+fs+"resources"+fs+"TestData"+fs+"newdc"+"_testData.properties");
				prop.load(fis);
				// Replace "key" with the actual key in your property file
	            String[] words = prop.getProperty(propertykey).split(",");
	           String randomWord = getRandomWord(words);
	           logg.info("Randomly selected word: " + randomWord);
	           return randomWord.trim();
		}else if(getEnv.equals("demo")) {
			 FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+fs+"src"+fs+"main"+fs+"resources"+fs+"TestData"+fs+"demo"+"_testData.properties");
				prop.load(fis);
				// Replace "key" with the actual key in your property file
	            String[] words = prop.getProperty(propertykey).split(",");
	           String randomWord = getRandomWord(words);
	           logg.info("Randomly selected word: " + randomWord);
	           return randomWord.trim();
		}
	} catch (FileNotFoundException f) {
		f.printStackTrace();  
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		return null;	
	}
	
}
