package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

public class ObjectsMap {

	public static Properties prop;

	public static String getObjects(String objects) {

		prop = new Properties();

		try {
			FileInputStream fis = new FileInputStream("src/main/resources/Objects.txt");
			prop.load(fis);
			fis.close();
			// return prop.getProperty(objects);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return prop.getProperty(objects);

	}

}
