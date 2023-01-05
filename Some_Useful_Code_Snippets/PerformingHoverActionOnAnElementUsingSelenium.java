//  Add the necessary dependencies in the pom.xml of the maven project

public class Practise {
 
  public static void main(String[] args) {
    // Initialise the webdriver
    
    Actions hoverAction = new Actions(driver);
    WebElement webElement = driver.findElement(By.cssSelector("####");
				
    hoverAction.moveToElement(webElement).build().perform();
    (new WebDriverWait(driver, 10)).until(ExpectedConditions.attributeToBeNotEmpty(webElement, "attributeName"));
    String attrValue = postDateAElement.getAttribute("aria-describedby");

    WebElement myDynamicElement = (new WebDriverWait(driver, 10))
          .until(ExpectedConditions.presenceOfElementLocated(By.id(attrValue)));
  }
  
}
