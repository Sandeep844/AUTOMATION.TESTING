package AUTOMATION.TESTING.utility;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import org.openqa.selenium.WebElement;

import AUTOMATION.TESTING.testBase.TestBase;

import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.nio.file.FileSystems;

public class UploadFileClass extends TestBase {


	private static String fs =FileSystems.getDefault().getSeparator();
	
	String expectedFilename="";
	public static void uploadfileUsingSendKeys(WebElement element,String pathOfFile) {
		element.sendKeys(pathOfFile);	
	}
	public static void uploadFileUsingRobotClass(String imagepath){
		StringSelection strSel=new StringSelection(imagepath);
		Clipboard clipboardr=Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboardr.setContents(strSel,null);
		// Create an Object for Robot class
		Robot robot=null;
		try {
	    robot=new Robot();
		} catch(AWTException awt) {
	    	awt.printStackTrace();
	    }
		robot.delay(250);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(150);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	   // robot.delay(150);
	    System.out.println("File Uploaded Successfully");
	 }
	
	public static void ReadPDFFile(){
		
	}
	
	public boolean isFileAvalable() {
		File folder=new File(System.getProperty("user.dir")+fs+"test-output" + fs +"htmlReport"+ fs +"screenshot");
		File[] listofFiles=folder.listFiles();
		boolean isFileAvalable=false;
		for(File listofFile:listofFiles) {
			if(listofFile.isFile()){
				String filename=listofFile.getName();
				System.out.println(filename);
				if(filename.matches(expectedFilename)) {
					isFileAvalable=true;
				}
			}
		}
		return isFileAvalable;
	}
	}

