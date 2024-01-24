package CommonUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFiles1 {
	
	public String getdatafromPropertFile(String key) throws IOException
	{
		FileInputStream fi = new FileInputStream("src\\test\\resources\\LeadTest.properties");
		Properties p = new Properties();
		p.load(fi);
		String value = p.getProperty(key);
		return value;
	}
	
	

}
