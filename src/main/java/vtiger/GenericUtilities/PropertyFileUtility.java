package vtiger.GenericUtilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * this class contains of generic methods to read the data from property file
 * @author akarsh
 *
 */

public class PropertyFileUtility {
	/**]
	 * this method will read data from property file
	 * @param key
	 * @return
	 * @throws IOException
	 */
	
	public String readDataFromPropertyFile(String key) throws IOException
	{
		FileInputStream fis=new FileInputStream(IConstantUtilities.PropertyFilePath);
		Properties pro=new Properties();
		pro.load(fis);
		String value = pro.getProperty(key);
		return value;
	}
}
