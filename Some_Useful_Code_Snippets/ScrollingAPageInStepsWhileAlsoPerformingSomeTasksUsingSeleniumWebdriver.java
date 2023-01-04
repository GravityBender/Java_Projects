// Add the necessary imports in the pom.xml of the maven project

public class Practise {
  
  private static void scrollPageInSteps(Webdriver driver) {
  
    long prevLen = -1L, currLen = 0L;
		int stepSize = 400;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		while(prevLen != currLen) {

			// Perform the necessary tasks

			jse.executeScript("window.scrollBy(0," + stepSize + ")");
			prevLen = currLen;
			currLen = (Long) (jse.executeScript("return window.pageYOffset"));
		}
  
  }
 
  public static void main(String[] args) {
    
    // Initialize the webdriver
    scrollPageInSteps(driver);
  
  }
  
}
