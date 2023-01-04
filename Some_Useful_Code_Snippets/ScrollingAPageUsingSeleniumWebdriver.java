// Add the necessary dependencies in the pom.xml of the maven project and install the respective webdriver

public class Practise {

  private static void scrollPage(WebDriver driver) {
		try {
			long lastHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

			while (true) {
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
				TimeUnit.MILLISECONDS.sleep(2000);

				long newHeight = (long) ((JavascriptExecutor) driver)
						.executeScript("return document.body.scrollHeight");
				if (newHeight == lastHeight) {
					break;
				}
				lastHeight = newHeight;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
  
  public static void main(String[] args) {
    // Initialize the webdriver variable
    scrollPage(driver);
  }

}
