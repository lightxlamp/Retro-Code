package com.nav.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class navTest 
{	
	static WebDriver driver    	    = CreateDriver();
	static String    URL            = "http://nav.demo.incentient.net/navigator/wp-admin/";
	static String    NewEmail       = "ssheveliukhin@incentient.com";
	static String    FidelioIP      = "12.199.87.170";
	static String    FidelioTimeOut = "1";

	public static void main(String[] args) throws InterruptedException 
	{
		NavDemoRemoveMails();
	}
	
	public static void NavDemoRemoveMails() throws InterruptedException
	{
		OpenLink(URL);
		LoginToAdminPanel();
		ChangeForumEmails();
		ChangeFidelioSettings();
		ChangeReservationRequestsEmails();
		ChangeCalendarMails();
		
		driver.quit();
	}
	
	public static WebDriver CreateDriver()
	{
		WebDriver driver = new SafariDriver();
		return driver;
	}
	
	public static void OpenLink(String URL) throws InterruptedException
	{
		driver.get(URL);
		Thread.sleep(5000);
	}
	
	public static void LoginToAdminPanel() throws InterruptedException
	{
		TypeInField("//*[@id=\"user_login\"]", "incentient");
		TypeInField("//*[@id=\"user_pass\"]", "!120commercial");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.findElement(By.id("wp-submit")).click();
		Thread.sleep(7000);
	}
	
	public static void TypeInField(String xpath, String value) throws InterruptedException
	{
	    String val = value; 
	    WebElement element = driver.findElement(By.xpath(xpath));
	    element.clear();

	    for (int i = 0; i < val.length(); i++)
	    {
	        char c = val.charAt(i);
	        String s = new StringBuilder().append(c).toString();
	        element.sendKeys(s);
	        Thread.sleep(100);
	    }    
	    Thread.sleep(500);
	}
	
	public static void ChangeForumEmails() throws InterruptedException
	{
		Thread.sleep(8000);
		OpenLink("http://nav.demo.incentient.net/navigator/wp-admin/options-general.php?page=bbpress");
		TypeInField("//*[@id=\"_bbp_forum_emails_open_forum\"]", NewEmail);
		Thread.sleep(2000);
		TypeInField("//*[@id=\"_bbp_forum_emails_ask_management\"]", NewEmail);
		Thread.sleep(1000);
		PressSaveButtonAndWait();
	}
	
	public static void ChangeFidelioSettings() throws InterruptedException
	{
		OpenLink("http://nav.demo.incentient.net/navigator/wp-admin/options-general.php?page=fidelio_menu");
		TypeInField("//*[@id=\"fidelio-server_ip\"]", FidelioIP);
		Thread.sleep(1000);
		TypeInField("//*[@id=\"fidelio_request_timeout\"]", FidelioTimeOut);
		Thread.sleep(1000);
		PressSaveButtonAndWait();
	}
	
	public static void PressSaveButtonAndWait() throws InterruptedException
	{
		driver.findElement(By.name("submit")).click();
		Thread.sleep(6000);
	}
	
	public static void PressSaveButton2AndWait() throws InterruptedException
	{
		driver.findElement(By.name("save")).click();
		Thread.sleep(6000);
	}
	
	public static void PressSaveAllButtonAndWait() throws InterruptedException
	{
		driver.findElement(By.name("theme_options_submit")).click();
		Thread.sleep(6000);
	}
	
	public static void ChangeReservationRequestsEmails() throws InterruptedException
	{
		OpenLink("http://nav.demo.incentient.net/navigator/wp-admin/admin.php?page=wc-settings&tab=email");	
		//1 New order 
		OpenLink("http://nav.demo.incentient.net/navigator/wp-admin/admin.php?page=wc-settings&tab=email&section=wc_email_new_order");
		clickCheckBox();
		TypeInField("//*[@id=\"woocommerce_new_order_recipient_dining\"]", NewEmail);
		Thread.sleep(1000);
		TypeInField("//*[@id=\"woocommerce_new_order_recipient_spa\"]", NewEmail);
		Thread.sleep(1000);
		PressSaveButton2AndWait();
		//2 Cancelled order 
		OpenLink("http://nav.demo.incentient.net/navigator/wp-admin/admin.php?page=wc-settings&tab=email&section=wc_email_cancelled_order");
		clickCheckBox();
		TypeInField("//*[@id=\"woocommerce_cancelled_order_recipient_dining\"]", NewEmail);
		Thread.sleep(1000);
		TypeInField("//*[@id=\"woocommerce_cancelled_order_recipient_spa\"]", NewEmail);
		Thread.sleep(1000);
		PressSaveButton2AndWait();
		//3 Failed order 
		OpenLink("http://nav.demo.incentient.net/navigator/wp-admin/admin.php?page=wc-settings&tab=email&section=wc_email_failed_order");
		Thread.sleep(7000);
		clickCheckBox();
		TypeInField("//*[@id=\"woocommerce_failed_order_recipient_dining\"]", NewEmail);
		Thread.sleep(1000);
		TypeInField("//*[@id=\"woocommerce_failed_order_recipient_spa\"]", NewEmail);
		PressSaveButton2AndWait();
		OpenLink("http://nav.demo.incentient.net/navigator/wp-admin/admin.php?page=wc-settings&tab=email");	
		Thread.sleep(10000);
	}
	
	public static void clickCheckBox() throws InterruptedException
	{
		driver.findElement(By.cssSelector("input[type='checkbox']")).click();
		Thread.sleep(1000);
	}
	
	public static void ChangeCalendarMails() throws InterruptedException
	{
		OpenLink("http://nav.demo.incentient.net/navigator/wp-admin/edit.php?post_type=events&page=rhc");
		
		TypeInField("//*[@id=\"rhc_pending_status_recipients341\"]", NewEmail);
		TypeInField("//*[@id=\"rhc_pending_status_recipients143\"]", NewEmail);
		TypeInField("//*[@id=\"rhc_pending_status_recipients259\"]", NewEmail);
		TypeInField("//*[@id=\"rhc_pending_status_recipients321\"]", NewEmail);
		TypeInField("//*[@id=\"rhc_pending_status_recipients319\"]", NewEmail);
		TypeInField("//*[@id=\"rhc_pending_status_recipients5\"]"  , NewEmail);
		TypeInField("//*[@id=\"rhc_pending_status_recipients6\"]"  , NewEmail);
		TypeInField("//*[@id=\"rhc_pending_status_recipients17\"]" , NewEmail);
		TypeInField("//*[@id=\"rhc_pending_status_recipients7\"]"  , NewEmail);
		TypeInField("//*[@id=\"rhc_pending_status_recipients15\"]" , NewEmail);
		TypeInField("//*[@id=\"rhc_pending_status_recipients8\"]"  , NewEmail);
		TypeInField("//*[@id=\"rhc_pending_status_recipients9\"]"  , NewEmail);
		TypeInField("//*[@id=\"rhc_pending_status_recipients12\"]" , NewEmail);
		TypeInField("//*[@id=\"rhc_pending_status_recipients10\"]" , NewEmail);
		TypeInField("//*[@id=\"rhc_pending_status_recipients243\"]", NewEmail);
		TypeInField("//*[@id=\"rhc_pending_status_recipients16\"]" , NewEmail);
		
		PressSaveAllButtonAndWait();
	}
}
