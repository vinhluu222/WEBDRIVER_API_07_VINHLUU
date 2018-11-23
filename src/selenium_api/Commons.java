package selenium_api;

import java.util.Random;

public class Commons {
	
	public static int randomEmail() {
		  Random random = new Random();
		  int number = random.nextInt(999999);
		  return number;
	  }
	
}
