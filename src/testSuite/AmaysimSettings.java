package testSuite;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmaysimSettings {
	  private WebDriver driver;
	  private String baseUrl = "https://accounts.amaysim.com.au";
	  private StringBuffer verificationErrors = new StringBuffer();
	  private String driverPath = System.getProperty("user.dir")+"\\resources\\geckodriver.exe";

	  @BeforeClass(alwaysRun = true)
	  public void setUp() throws Exception {
	  //open browser
		System.out.println("Launching firefox browser..."); 
		System.setProperty("webdriver.firefox.marionette", driverPath);
		driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }	  

	  @BeforeMethod
	  public void logIn() throws Exception {
	    //initiate login
	    driver.get(baseUrl + "/identity/login");
	    driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys("0468 827 174");
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("theHoff34");
	    driver.findElement(By.name("commit")).click();
	  }

	  @Test(priority=1)
	  public void assertValidNickname() throws Exception {
	  //ensure window is at default body-content
		driver.findElement(By.xpath("//body[@id='body-content']/div[11]")).click();
      	driver.switchTo().defaultContent();
	  //test a valid SIM nickname
	    String nickname = "testname123";
	    driver.findElement(By.linkText("Settings")).click();
	    driver.findElement(By.id("edit_settings_phone_label")).click();	    
	    driver.findElement(By.id("my_amaysim2_setting_phone_label")).clear();
	    driver.findElement(By.id("my_amaysim2_setting_phone_label")).sendKeys(nickname);
	    driver.findElement(By.id("my_amaysim2_setting_phone_label")).sendKeys(Keys.ENTER);
	    try {
	        assertEquals(driver.findElement(By.id("my_amaysim2_setting_phone_label")).getAttribute("value"), nickname);
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }	    
	  }

	  @Test(priority=2)
	  public void assertInvalidNickname() throws Exception {
	  //ensure window is at default body-content
		driver.findElement(By.xpath("//body[@id='body-content']/div[11]")).click();
      	driver.switchTo().defaultContent();
	  //test an invalid SIM nickname
	    String nickname = "@testname123";
	    driver.findElement(By.linkText("Settings")).click();
	    driver.findElement(By.id("edit_settings_phone_label")).click();	    
	    driver.findElement(By.id("my_amaysim2_setting_phone_label")).clear();
	    driver.findElement(By.id("my_amaysim2_setting_phone_label")).sendKeys(nickname);
	    driver.findElement(By.id("my_amaysim2_setting_phone_label")).sendKeys(Keys.ENTER);
	    try {
	        assertTrue(isTextPresent("Please enter a valid SIM name."));
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }	    
	  }

	  @Test(priority=3)
	  public void assertValidPin() throws Exception {
	  //ensure window is at default body-content
		driver.findElement(By.xpath("//body[@id='body-content']/div[11]")).click();
	    driver.switchTo().defaultContent();
	  //test a valid recharge PIN
	    String pin = "1234";
		driver.findElement(By.linkText("Settings")).click();
	    driver.findElement(By.id("edit_settings_recharge_pin")).click();
	    driver.findElement(By.name("my_amaysim2_setting[topup_pw]")).clear();
	    driver.findElement(By.name("my_amaysim2_setting[topup_pw]")).sendKeys(pin);
	    driver.findElement(By.name("my_amaysim2_setting[topup_pw]")).sendKeys(Keys.ENTER);
	    try {
	        assertEquals(driver.findElement(By.xpath("//div[@id='settings_recharge_pin']/div/div/div/div/div[2]")).getText(), pin);
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }	    
	  }

	  @Test(priority=4)
	  public void assertInvalidPin() throws Exception {
	  //ensure window is at default body-content
		driver.findElement(By.xpath("//body[@id='body-content']/div[11]")).click();
	    driver.switchTo().defaultContent();
	  //test an invalid recharge PIN
	    String pin = "-123";
		driver.findElement(By.linkText("Settings")).click();
	    driver.findElement(By.id("edit_settings_recharge_pin")).click();
	    driver.findElement(By.name("my_amaysim2_setting[topup_pw]")).clear();
	    driver.findElement(By.name("my_amaysim2_setting[topup_pw]")).sendKeys(pin);
	    driver.findElement(By.name("my_amaysim2_setting[topup_pw]")).sendKeys(Keys.ENTER);
	    try {
		        assertTrue(isTextPresent("Please enter a 4-digit top-up PIN number."));
		      } catch (Error e) {
		        verificationErrors.append(e.toString());
		      }	    
	  }

	  @Test(priority=5)
	  public void assertCallerID() throws Exception {
	  //ensure window is at default body-content
	    driver.findElement(By.xpath("//body[@id='body-content']/div[11]")).click();
	    driver.switchTo().defaultContent();
	  //test checkbox
		driver.findElement(By.linkText("Settings")).click();
	    driver.findElement(By.xpath("//label[@for='my_amaysim2_setting_caller_id_out']")).click();	    	
	    try {
	        assertTrue(isElementPresent(By.xpath("//p[@class='popup-content']")));
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }	    
	  }

	  @Test(priority=6)
	  public void assertCallWait() throws Exception {
	  //ensure window is at default body-content
		driver.findElement(By.xpath("//body[@id='body-content']/div[11]")).click();
	    driver.switchTo().defaultContent();
	  //test call waiting checkbox
		driver.findElement(By.linkText("Settings")).click();
        driver.findElement(By.cssSelector("#edit_setting_caller_waiting_1659584 > div.small-12.columns > label")).click();
	    try {
	        assertTrue(isElementPresent(By.xpath("//p[@class='popup-content']")));
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }	    
	  }

	  @Test(priority=7)
	  public void assertVoiceMail() throws Exception {
	  //ensure window is at default body-content
		driver.findElement(By.xpath("//body[@id='body-content']/div[11]")).click();
	    driver.switchTo().defaultContent();
	  //test voicemail checkbox
		driver.findElement(By.linkText("Settings")).click();
    	driver.findElement(By.xpath("//form[@id='edit_setting_voice_mail_1659584']/div[2]/label")).click();
	    try {
	        assertTrue(isElementPresent(By.xpath("//p[@class='popup-content']")));
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }	    
	  }

	  @Test(priority=8)
	  public void assertUsageAlerts() throws Exception {
	  //ensure window is at default body-content
	    driver.findElement(By.xpath("//body[@id='body-content']/div[11]")).click();
	    driver.switchTo().defaultContent();
	  //test checkbox
		driver.findElement(By.linkText("Settings")).click();
        driver.findElement(By.cssSelector("#edit_setting_usage_alert_1659584 > div.small-12.columns > label")).click();
	    try {
			assertTrue(isElementPresent(By.id("confirm_popup_yes")));
			driver.findElement(By.id("confirm_popup_yes")).click();
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }	    
	  }

	  @Test(priority=9)
	  public void assertIntlRoam() throws Exception {
	  //ensure window is at default body-content
	    driver.findElement(By.xpath("//body[@id='body-content']/div[11]")).click();
	    driver.switchTo().defaultContent();
	  //test checkbox
		driver.findElement(By.linkText("Settings")).click();
		try {
			driver.findElement(By.cssSelector("#edit_setting_intl_roaming_1659584 > div.small-12.columns > label")).click();
			System.out.println(driver.findElement(By.id("confirm_popup_yes")).isDisplayed());
			if (driver.findElement(By.id("confirm_popup_yes")).isDisplayed()) {
				driver.findElement(By.id("confirm_popup_yes")).click();
			} 
		} catch (Error e) {
	        verificationErrors.append(e.toString());
			}	    
	  }
	  
	  @Test(priority=10)
	  public void assertCallFwd() throws Exception {
	  //ensure window is at default body-content
		driver.findElement(By.xpath("//body[@id='body-content']/div[11]")).click();
	    driver.switchTo().defaultContent();
	  //test call forwarding
	    String fwdToNumber = "0212345678";
		driver.findElement(By.linkText("Settings")).click();
	    driver.findElement(By.id("edit_settings_call_forwarding")).click();
	    //Wait for element to be clickable
	    	WebDriverWait wait = new WebDriverWait(driver, 15);
	    	wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Confirm")));
	    	driver.findElement(By.linkText("Confirm")).click();
        driver.findElement(By.id("my_amaysim2_setting_call_divert_number")).clear();
        driver.findElement(By.id("my_amaysim2_setting_call_divert_number")).sendKeys(fwdToNumber);
        driver.findElement(By.id("my_amaysim2_setting_call_divert_number")).sendKeys(Keys.ENTER);
        assertTrue(driver.findElement(By.xpath("//div[@id='settings_call_forwarding']/div/div/div/div[2]/div")).getText().contains(fwdToNumber));
	  }

	  @Test(priority=11)
	  public void assertSMSLimit() throws Exception {
	  //ensure window is at default body-content
		driver.findElement(By.xpath("//body[@id='body-content']/div[11]")).click();
	    driver.switchTo().defaultContent();
	  //test SMS limit to set OFF
		driver.findElement(By.linkText("Settings")).click();
	    driver.findElement(By.id("edit_settings_premium_sms_limit")).click();
	    new Select(driver.findElement(By.id("my_amaysim2_setting_psms_spend"))).selectByVisibleText("OFF");
	    Thread.sleep(3000);
	    //Wait for element to be clickable
    		WebDriverWait wait = new WebDriverWait(driver, 15);
    		wait.until(ExpectedConditions.elementToBeClickable(By.name("commit")));
    		driver.findElement(By.name("commit")).click();
	  //test SMS limit to $30
   	    driver.findElement(By.id("edit_settings_premium_sms_limit")).click();
   	    new Select(driver.findElement(By.id("my_amaysim2_setting_psms_spend"))).selectByVisibleText("$30");
   	    Thread.sleep(3000);
   	    //Wait for element to be clickable
        	wait.until(ExpectedConditions.elementToBeClickable(By.name("commit")));
        	driver.findElement(By.name("commit")).click();
        assertEquals(driver.findElement(By.xpath("//div[@id='settings_premium_sms_limit']/div/div/div[2]")).getText(), "$30");    		
	    }

	  @Test(priority=12)
	  public void assertAutoRcg() throws Exception {
	  //ensure window is at default body-content
		driver.findElement(By.xpath("//body[@id='body-content']/div[11]")).click();
	    driver.switchTo().defaultContent();
	  //test auto recharge
		driver.findElement(By.linkText("Settings")).click();
	    driver.findElement(By.id("edit_settings_auto_recharge")).click();
	    new Select(driver.findElement(By.id("my_amaysim2_setting_auto_topup_min_balance"))).selectByVisibleText("$10");
   	    Thread.sleep(3000);
	    new Select(driver.findElement(By.id("my_amaysim2_setting_auto_topup_amount"))).selectByVisibleText("$20");
   	    Thread.sleep(3000);
	    //Wait for element to be clickable
	    	WebDriverWait wait = new WebDriverWait(driver, 15);
	    	wait.until(ExpectedConditions.elementToBeClickable(By.name("commit")));
	    	driver.findElement(By.name("commit")).click();
	    assertEquals(driver.findElement(By.xpath("//div[@id='settings_auto_recharge']/div/div/div/div[2]/div")).getText(), "Recharge my mobile service with $20 whenever the balance drops below $10");
	  }

	  @AfterMethod
	  public void logOut() throws Exception {
//		  driver.findElement(By.id("logout-link")).click();
	  }

	  @AfterClass(alwaysRun = true)
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      System.out.println(verificationErrorString);
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
		    try {
		      driver.findElement(by);
		      return true;
		    } catch (NoSuchElementException e) {
		      return false;
		    }
		  }

	  protected boolean isTextPresent(String text){
		    try{
		        boolean b = driver.getPageSource().contains(text);
		        return b;
		    }
		    catch(Exception e){
		        return false;
		    }
		  }

}
