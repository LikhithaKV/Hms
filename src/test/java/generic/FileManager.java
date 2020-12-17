package generic;

import java.io.FileInputStream;

import java.util.Properties;

import org.testng.Reporter;

public class FileManager {
	Properties pro;

	public FileManager() {
FileInputStream fis;
try {
	fis = new FileInputStream(System.getProperty("user.dir")+"/config.properties");
	 pro = new Properties();
	 pro.load(fis);
} catch (Exception e) {
	Reporter.log("property file not found", true);
}
	}

public String getQASurl() {
	String url = pro.getProperty("qat");
	if(url != null)
		return url; 
	else
		throw new RuntimeException("url not found");
}

public String getProdurl() {
	String url = pro.getProperty("prod");
	if(url != null)
		return url; 
	else
		throw new RuntimeException("url not found"); 
}

public String getPreProdurl() {
	String url = pro.getProperty("preprod");
	if(url != null)
		return url; 
	else
		throw new RuntimeException("url not found");
}

public long getImplicitlyWait() {
	 String url = pro.getProperty("implicitlywait");
	long val = Long.parseLong(url);
	return val;
}
}

