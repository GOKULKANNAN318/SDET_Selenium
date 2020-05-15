package Selenium;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.Table.Cell;

import Automation._Selenium.Base;

public class Assignment extends Base{

	static WebDriver driver1;
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();  	



	public void setfDriver() {
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Gokul  Kannan.B\\Downloads\\Jars\\chromeDriver_80\\chromedriver.exe"); //jar/chromedriver.exe
		this.driver1 = getFirefoxDriver();
		setWebDriver(driver1);
		driver().manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver().manage().window().maximize();
	}


	public void setCDriver() {

		this.driver1 = getChromeDriver();
		setWebDriver(driver1);
		driver().manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver().manage().window().maximize();
	}

	@AfterMethod
	public void closeDriver() {
		try {
			this.driver().close();
		}catch(Exception e) {

		}

	}
	public void setWebDriver(WebDriver localdriver) {
		driver.set(localdriver);
	}

	public WebDriver driver() {
		return driver.get();
	}


	@Test(dataProvider="data")
	public void selectall(String data) throws InterruptedException {

		if(data.equalsIgnoreCase("chrome")) {
			setCDriver();
		}else {
			setfDriver();
		}

		try {
			driver().get("https://demoqa.com/selectable/");


			Thread.sleep(5000);

			int count  = driver().findElements(By.xpath("//li[@class=\"ui-widget-content ui-selectee\"]")).size();

			for(int i=1 ; i<=count ; i++) {
				//ol[@id]//li[2]
				System.out.println("Selecting " + driver().findElement(By.xpath("(//ol[@id]//li["+i+"])")).getText());

				driver().findElement(By.xpath("(//ol[@id]//li["+i+"])")).click();

				Thread.sleep(5000);
			}
		}catch(Exception unknown) {
			closeDriver();
		}
	}





	@Test(dataProvider="data")
	public void Formfill(String data) throws InterruptedException {

		if(data.equalsIgnoreCase("chrome")) {
			setCDriver();
		}else {
			setfDriver();
		}
		try {
			driver().get("https://demoqa.com/html-contact-form/");


			Thread.sleep(5000);

			String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);

			driver().findElement(By.xpath("//input[@class=\"firstname\"]")).sendKeys("Automation");

			driver().findElement(By.xpath("//input[@id=\"lname\"]")).sendKeys("Testing");

			driver().findElement(By.xpath("//input[@name=\"country\"]")).sendKeys("Chennai");

			driver().findElement(By.xpath("//textarea[@id=\"subject\"]")).sendKeys("Automation Testing in Chennai");

			WebElement firstlink = driver().findElement(By.xpath("//a[contains(text(),\"Google Link\")][1]"));

			WebElement secondlink = driver().findElement(By.xpath("//a[contains(text(),\"Google Link\")][2]"));

			//Opening new empty tab--> 	driver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");


			Actions actions = new Actions(driver());
			actions.keyDown(Keys.LEFT_CONTROL)
			.click(firstlink)
			.click(secondlink)
			.keyUp(Keys.LEFT_CONTROL)
			.build()
			.perform();

			driver().findElement(By.xpath("//input[@type=\"submit\"]")).click();

			ArrayList<String> tabs = new ArrayList<String> (driver().getWindowHandles());

			System.out.println(tabs.size());


			driver().switchTo().window(tabs.get(2));
			driver().close();
			driver().switchTo().window(tabs.get(0));
			driver().switchTo().window(tabs.get(1));
			Thread.sleep(3000);
			driver().close();
			Thread.sleep(3000);
			driver().switchTo().window(tabs.get(0));


		}catch(Exception unknown) {
			closeDriver();
		}


	}

	@Test(dataProvider="data")
	public void DragandDrop(String data) throws InterruptedException {

		if(data.equalsIgnoreCase("chrome")) {
			setCDriver();
		}else {
			setfDriver();
		}

		try {
			driver().get("https://demoqa.com/droppable/");

			Thread.sleep(5000);

			Actions actions = new Actions(driver());

			WebElement firstElement = driver().findElement(By.xpath("//p[contains(.,\"Drag me to my target\")]"));

			WebElement secondElement = driver().findElement(By.xpath("//div[@id=\"droppable\"]"));

			actions.dragAndDrop(firstElement, secondElement).build().perform();

			Thread.sleep(10000);
		}catch(Exception unknown) {
			closeDriver();
		}

	}


	@Test(dataProvider="data")
	public void selectDropDown(String data) throws InterruptedException {

		if(data.equalsIgnoreCase("chrome")) {
			setCDriver();
		}else {
			setfDriver();
		}
		try {
			driver().get("https://demoqa.com/selectmenu/");

			Thread.sleep(5000);

			Select speeddropdown = new Select(driver().findElement(By.xpath("//select[@id=\"speed\"]")));

			List<WebElement> speed = speeddropdown.getOptions();

			//for(int i = 0 ; i < speed.size() ; i++) {

			//Thread.sleep(2000);
			//speed.get(i).click();
			//Thread.sleep(2000);
			//}
			//driver().findElement(By.xpath("//span[@id=\"speed-button\"]")).click();
			//	speeddropdown.selectByIndex(1);

			driver().findElement(By.xpath("//span[@id=\"speed-button\"]")).click();

			driver().findElement(By.xpath("//ul[@id=\"speed-menu\"]//li[contains(.,\"Faster\")]")).click();

			driver().findElement(By.xpath("//span[@id=\"files-button\"]")).click();

			driver().findElement(By.xpath("//ul//div[contains(.,\"ui.jQuery.js\")]")).click();

			driver().findElement(By.xpath("//span[@id=\"number-button\"]")).click();

			driver().findElement(By.xpath("//ul[@id=\"number-menu\"]//div[contains(.,\"5\") and (not (contains(.,\"15\")))]")).click();

			driver().findElement(By.xpath("//span[@id=\"salutation-button\"]")).click();

			driver().findElement(By.xpath("//ul[@id=\"salutation-menu\"]/li[contains(.,\"Mr.\")]")).click();
		}catch(Exception unknown) {
			closeDriver();
		}


	}






	@Test(dataProvider="data")
	public void BirthDayPicker(String data) throws InterruptedException {

		if(data.equalsIgnoreCase("chrome")) {
			setCDriver();
		}else {
			setfDriver();
		}
		try {
			driver().get("https://demoqa.com/datepicker/");

			Thread.sleep(5000);

			int day = 17;

			int month = getmonth("March");

			int year = 1996;

			driver().findElement(By.xpath("//input[@id=\"datepicker\"]")).click();

			int yr = Integer.parseInt(driver().findElement(By.xpath("//span[@class=\"ui-datepicker-year\"]")).getText());

			String mon = driver().findElement(By.xpath("//span[@class=\"ui-datepicker-month\"]")).getText();

			int yrdiff = yr - year ;

			int mont = getmonth(mon);

			int mondiff = Math.abs(month - mont );

			int actmon = (yrdiff*12) + mondiff;	

			for(int i = 0 ; i < actmon ; i++ ) {
				driver().findElement(By.xpath("//span[@class=\"ui-icon ui-icon-circle-triangle-w\"]")).click();
			}

			Thread.sleep(5000);

			driver().findElement(By.xpath("//a[@class=\"ui-state-default\" and contains(.,\""+day+"\")]")).click();


			Thread.sleep(5000);


		}catch(Exception unknown) {
			closeDriver();
		}	

	}  

	@Test(dataProvider="data")
	public void RentalCar(String data) throws InterruptedException {

		if(data.equalsIgnoreCase("chrome")) {
			setCDriver();
		}else {
			setfDriver();
		}
		try {
			driver().get("https://demoqa.com/controlgroup/");

			Thread.sleep(5000);

			Select speeddropdown = new Select(driver().findElement(By.xpath("//select[@id=\"car-type\"]")));

			driver().findElement(By.cssSelector("span#car-type-button")).click();

			driver().findElement(By.xpath("//ul[@id=\"car-type-menu\"]/li[contains(.,\"Van\")]")).click();

			driver().findElement(By.xpath("//label[@for=\"transmission-automatic\"]")).click();

			driver().findElement(By.xpath("//label[@for=\"insurance\"]")).click();

			driver().findElement(By.xpath("//label[@for=\"insurance\"]")).click();

			driver().findElement(By.xpath("//input[@id=\"horizontal-spinner\"]")).sendKeys("3");

			driver().findElement(By.xpath("//button[@class=\"ui-widget ui-controlgroup-item ui-button ui-corner-right\"]")).click();



			driver().findElement(By.xpath("//span[@id=\"ui-id-8-button\"]")).click();

			driver().findElement(By.xpath("//div[@id=\"ui-id-11\"]")).click();

			driver().findElement(By.xpath("//label[@for=\"transmission-standard-v\"]")).click();

			driver().findElement(By.xpath("//label[@for=\"insurance-v\"]")).click();

			driver().findElement(By.xpath("//input[@id=\"vertical-spinner\"]")).sendKeys("1");

			driver().findElement(By.xpath("//button[@id=\"book\"]")).click();


		}catch(Exception unknown) {
			closeDriver();
		}
	}



	@Test(dataProvider="data")
	public void makemytrip(String data) throws InterruptedException {

		if(data.equalsIgnoreCase("chrome")) {
			setCDriver();
		}else {
			setfDriver();
		}
		try {
			driver().get("https://www.makemytrip.com/");

			Thread.sleep(5000);

			String deptcity = "Chennai";

			String arrcity = "Coimbatore";

			driver().findElement(By.xpath("//input[@id=\"fromCity\"]")).click();

			Thread.sleep(2000);

			driver().findElement(By.xpath("//input[@placeholder=\"From\"]")).sendKeys(deptcity+Keys.RETURN);

			driver().findElement(By.xpath("//input[@placeholder=\"From\"]")).sendKeys(Keys.RETURN);

			//WebElement firstsug = driver().findElement(By.xpath("(//div[@class=\"makeFlex hrtlCenter\"])[1]"));

			Thread.sleep(2000);

			WebElement firstsug = driver().findElement(By.xpath("//li[@id=\"react-autowhatever-1-section-0-item-0\"]"));



			Actions actions = new Actions(driver());

			Thread.sleep(4000);
			//actions.moveToElement(firstsug);



			firstsug.click();


			Thread.sleep(2000);

			driver().findElement(By.xpath("//input[@placeholder=\"To\"]")).sendKeys(arrcity+Keys.RETURN);

			Thread.sleep(2000);

			WebElement secondsug = driver().findElement(By.xpath("//li[@id=\"react-autowhatever-1-section-0-item-0\"]"));

			//actions.moveToElement(secondsug).keyDown(Keys.RETURN).build().perform();

			Thread.sleep(2000);

			secondsug.click();

			//	driver().findElement(By.xpath("//input[@id=\"departure\"]")).click();



			for(int i = 0 ; i<12 ; i++) {
				String test = driver().findElement(By.xpath("(//div[@class=\"DayPicker-Caption\"])[2]")).getText();
				String test1 = driver().findElement(By.xpath("(//div[@class=\"DayPicker-Caption\"])[1]")).getText();
				if(!(driver().findElement(By.xpath("(//div[@class=\"DayPicker-Caption\"])[1]")).getText().contains("March") || driver().findElement(By.xpath("(//div[@class=\"DayPicker-Caption\"])[2]")).getText().contains("March"))) {
					driver().findElement(By.xpath("//span[@class=\"DayPicker-NavButton DayPicker-NavButton--next\"]")).click();
				} else {
					if(driver().findElement(By.xpath("(//div[@class=\"DayPicker-Caption\"])[1]")).getText().contains("March") ) {
						driver().findElement(By.xpath("(//div[@class=\"dateInnerCell\" and contains(.,\"17\")])[1]")).click();
					}

					if(driver().findElement(By.xpath("(//div[@class=\"DayPicker-Caption\"])[2]")).getText().contains("March") ) {
						driver().findElement(By.xpath("(//div[@class=\"dateInnerCell\" and contains(.,\"17\")])[2]")).click();
					}

					break;
				}

			}


			driver().findElement(By.xpath("//a[contains(.,\"Search\")]")).click();

			Thread.sleep(2000);


			try {
				driver().findElement(By.xpath("//span[@class=\"pointer\"]/span[contains(.,\"Price\")]/following::span[@class=\"down sort-arrow\"]")).isDisplayed();
			} catch (Exception E) {
				driver().findElement(By.xpath("//span[@class=\"up sort-arrow\"]")).click();
			}
			driver().findElement(By.xpath("(//button[@class=\"ViewFareBtn  text-uppercase \"])[1]")).click();
			driver().findElement(By.xpath("(//button[contains(.,\" Book Now \")])[1]")).click();

			ArrayList<String> tabs = new ArrayList<String> (driver().getWindowHandles());
			driver().switchTo().window(tabs.get(1));
			Thread.sleep(3000);

			//Verification
			driver().findElement(By.xpath("//h4[contains(.,\"Review your booking\")]")).isDisplayed();
			driver().findElement(By.xpath("//p[@class=\"dept-city\" and contains(.,\""+deptcity+"\")]")).isDisplayed();
			driver().findElement(By.xpath("//p[@class=\"arrival-city\" and contains(.,\""+arrcity+"\")]")).isDisplayed();

			driver().close();
			Thread.sleep(3000);
			driver().switchTo().window(tabs.get(0));
		}catch(Exception unknown) {
			closeDriver();
		}
	}

	@Test(dataProvider="data")
	public void Assignment2(String data) throws InterruptedException, FileNotFoundException, IOException, ParseException {

		if(data.equalsIgnoreCase("chrome")) {
			setCDriver();
		}else {
			setfDriver();
		}

		try {
			driver().navigate().to("https://www.olay.es/es-es/createprofilepage");


			XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream("DataSheets/UserDetails-Spain.xlsx"));

			XSSFSheet myExcelSheet = myExcelBook.getSheet("Spain");
			XSSFRow row = myExcelSheet.getRow(1);

			System.out.println( row.getCell(7));

			XSSFCell ce = row.getCell(2);

			String day = row.getCell(7).toString();

			String mon = row.getCell(7).toString();

			String ry = row.getCell(7).toString();

			//System.out.println( ce + "  " + sa);
			if(day.contains(".0")){
				day = day.replace(".0", "");
			}
			if(mon.contains(".0")){
				mon = mon.replace(".0", "");
			}
			if(ry.contains(".0")){
				ry = ry.replace(".0", "");
			}

			day = "5" ;

			if(row.getCell(0).toString().equalsIgnoreCase("female")) {
				driver().findElement(By.xpath("//img[@title=\"Mujer\"]")).click();
			} else {
				driver().findElement(By.xpath("//img[@title=\"Hombre\"]")).click();
			}




			Random rand = new Random(); //instance of random class
			int upperbound = 100;

			int generatedString = rand.nextInt(upperbound); 

			driver().findElement(By.xpath("//input[@data-key=\"firstName\"]")).sendKeys(row.getCell(1).toString());

			driver().findElement(By.xpath("//input[@data-key=\"lastName\"]")).sendKeys(row.getCell(2).toString());

			driver().findElement(By.xpath("//input[@data-key=\"middleName\"]")).sendKeys(row.getCell(3).toString());

			String mailid = row.getCell(4).toString().replace("@gmail.com", "")  + generatedString +"@gmail.com";

			driver().findElement(By.xpath("//input[@data-key=\"emailAddress\"]")).sendKeys(mailid);

			driver().findElement(By.xpath("//input[@data-key=\"newPassword\"]")).sendKeys(row.getCell(5).toString());

			driver().findElement(By.xpath("//input[@id=\"phdesktopbody_0_grs_account[password][confirm]\"]")).sendKeys(row.getCell(6).toString());

			JavascriptExecutor jse = (JavascriptExecutor)driver();
			jse.executeScript("arguments[0].scrollIntoView(true);",driver().findElement(By.xpath("//div[@class=\"pc_txtfld pc_BirthDay\"]")));
			driver().findElement(By.xpath("//div[@class=\"pc_txtfld pc_BirthDay\"]")).click();
			//Select day = new Select(driver().findElement(By.xpath("//select[@data-key=\"birthdate[dateselect_day]\"]")));
			driver().findElement(By.xpath("//div[@class=\"pc_txtfld pc_BirthDay\"]//option[contains(@value,"+mon+")]")).click();

			driver().findElement(By.xpath("//select[@data-key=\"birthdate[dateselect_month]\"]")).click();
			//Select mon = new Select(driver().findElement(By.xpath("//select[@data-key=\"birthdate[dateselect_month]\"]")));
			driver().findElement(By.xpath("//div[@class=\"pc_txtfld pc_BirthMonth\"]//option[contains(@value,\""+day+"\")]")).click();

			driver().findElement(By.xpath("//select[@data-key=\"birthdate[dateselect_year]\"]")).click();
			///Select yr = new Select(driver().findElement(By.xpath("//select[@data-key=\"birthdate[dateselect_year]\"]")));
			driver().findElement(By.xpath("//div[@class=\"pc_slct pc_year pc_birthday\"]//option[contains(@value,\""+ry+"\")]")).click();

			driver().findElement(By.xpath("//input[@data-key=\"phone\"]")).sendKeys("+3475623812");

			driver().findElement(By.xpath("//input[@class=\"button-link button\"]")).click();

			Thread.sleep(5000);

			try {

				driver().findElement(By.xpath("//input[@id=\"phdesktopbody_0_INICIAR SESIÓN\"]")).click();

				Thread.sleep(5000);
			} catch (Exception DataIssue) {
				System.out.println("Need unique data for spain users");
			}

			driver().manage().deleteAllCookies();



			Thread.sleep(5000);








			driver().navigate().to("https://www.olaz.de/de-de/createprofilepage");
			JSONParser jsonParser = new JSONParser();

			Object obj = jsonParser.parse(new FileReader("DataSheets/test.json"));

			JSONObject jsonObject = (JSONObject) obj;

			String gender = (String) jsonObject.get("Gender");

			if(gender.equalsIgnoreCase("male")) {
				driver().findElement(By.xpath("//img[@id=\"phdesktopbody_0_imgmale\"]")).click();
			}  else {
				driver().findElement(By.xpath("//li[@id=\"phdesktopbody_0_liF\"]")).click();
			}

			String First_Name = jsonObject.get("First_Name").toString();
			driver().findElement(By.xpath("//input[@id=\"phdesktopbody_0_grs_consumer[firstname]\"]")).sendKeys(First_Name);


			String Last_Name = (String) jsonObject.get("Last_Name");
			driver().findElement(By.xpath("//input[@id=\"phdesktopbody_0_grs_consumer[lastname]\"]")).sendKeys(Last_Name);



			String Email = (String) jsonObject.get("Email");
			Email = Email.replace("@gmail.com", "");
			driver().findElement(By.xpath("//input[@id=\"phdesktopbody_0_grs_account[emails][0][address]\"]")).sendKeys(Email+generatedString+"@gmail.com");

			String Password = (String) jsonObject.get("Password");
			driver().findElement(By.xpath("//input[@data-key=\"newPassword\"]")).sendKeys(Password);
			driver().findElement(By.xpath("//input[@id=\"phdesktopbody_0_grs_account[password][confirm]\"]")).sendKeys(Password);



			jse.executeScript("window.scrollBy(0,250)");

			String Date_Of_Birth = (String) jsonObject.get("Date_Of_Birth");
			String dob[] = Date_Of_Birth.split("-");
			jse.executeScript("arguments[0].scrollIntoView(true);",driver().findElement(By.xpath("//div[@class=\"pc_txtfld pc_BirthDay\"]")));
			driver().findElement(By.xpath("//div[@class=\"pc_txtfld pc_BirthDay\"]")).click();
			//Select day = new Select(driver().findElement(By.xpath("//select[@data-key=\"birthdate[dateselect_day]\"]")));
			driver().findElement(By.xpath("//div[@class=\"pc_txtfld pc_BirthDay\"]//option[contains(@value,\""+dob[2]+"\")]")).click();


			driver().findElement(By.xpath("//select[@data-key=\"birthdate[dateselect_month]\"]")).click();
			//Select mon = new Select(driver().findElement(By.xpath("//select[@data-key=\"birthdate[dateselect_month]\"]")));
			driver().findElement(By.xpath("//div[@class=\"pc_txtfld pc_BirthMonth\"]//option[contains(@value,\""+dob[1]+"\")]")).click();


			driver().findElement(By.xpath("//select[@data-key=\"birthdate[dateselect_year]\"]")).click();
			Select yr = new Select(driver().findElement(By.xpath("//select[@data-key=\"birthdate[dateselect_year]\"]")));
			driver().findElement(By.xpath("//div[@class=\"pc_slct pc_year pc_birthday\"]//option[contains(@value,\""+dob[0]+"\")]")).click();




			String Land = (String) jsonObject.get("Land");
			driver().findElement(By.xpath("//select[@data-key=\"addressCountry\"]")).click();
			Select address = new Select(driver().findElement(By.xpath("//select[@data-key=\"addressCountry\"]")));
			address.selectByVisibleText(Land);


			driver().findElement(By.xpath("//select[@data-key=\"addressCountry\"]")).click();

			String Address1 = (String) jsonObject.get("Address1");
			driver().findElement(By.xpath("//input[@data-key=\"addressStreet1\"]")).sendKeys(Address1);


			String Postalcode = (String) jsonObject.get("Postalcode");
			driver().findElement(By.xpath("//input[@data-key=\"addressPostalCode\"]")).sendKeys(Postalcode);

			String City = (String) jsonObject.get("City");
			driver().findElement(By.xpath("//input[@data-key=\"addressCity\"]")).sendKeys(City);


			jse.executeScript("window.scrollBy(0,250)");

			jse.executeScript("arguments[0].scrollIntoView(true);",driver().findElement(By.xpath("//input[@class=\"button-link button\"]")));

			driver().findElement(By.xpath("//input[@class=\"button-link button\"]")).click();


			Thread.sleep(5000);
			try {
				driver().findElement(By.xpath("//span[@id=\"phdesktopbody_0_ErrorMessage\"]")).isDisplayed();
				System.out.println("System error Account is not created"); 
			} catch (Exception Pass) {
				System.out.println("Account created"); 	
			}


			driver().manage().deleteAllCookies();





			Thread.sleep(5000);
			driver().navigate().to("https://www.olay.co.uk/en-gb/createprofilepage");        



			driver().findElement(By.xpath("//input[@id=\"phdesktopbody_0_grs_account[emails][0][address]\"]")).sendKeys("selTest"+generatedString+"@gmail.com");

			driver().findElement(By.xpath("//input[@data-key=\"newPassword\"]")).sendKeys("Test@123");
			driver().findElement(By.xpath("//input[@id=\"phdesktopbody_0_grs_account[password][confirm]\"]")).sendKeys("Test@123");

			jse.executeScript("window.scrollBy(0,250)");

			jse.executeScript("arguments[0].scrollIntoView(true);",driver().findElement(By.xpath("//div[@class=\"pc_txtfld pc_BirthDay\"]")));

			driver().findElement(By.xpath("//div[@class=\"pc_txtfld pc_BirthDay\"]")).click();
			driver().findElement(By.xpath("//div[@class=\"pc_txtfld pc_BirthDay\"]//option[contains(@value,\""+"12"+"\")]")).click();


			driver().findElement(By.xpath("//select[@data-key=\"birthdate[dateselect_month]\"]")).click();
			driver().findElement(By.xpath("//div[@class=\"pc_txtfld pc_BirthMonth\"]//option[contains(@value,\""+"10"+"\")]")).click();


			driver().findElement(By.xpath("//select[@data-key=\"birthdate[dateselect_year]\"]")).click();
			driver().findElement(By.xpath("//div[@class=\"pc_slct pc_year pc_birthday\"]//option[contains(@value,\""+"1998"+"\")]")).click();



			driver().findElement(By.xpath("//input[@class=\"button-link button\"]")).click();
			Thread.sleep(5000);
			driver().findElement(By.xpath("//a[@title=\"Skip This Step\"]")).click();
			Thread.sleep(5000);
			driver().findElement(By.xpath("//a[@title=\"Sign Out\"]")).click();

			driver().findElement(By.xpath("//a[@title=\"Logout\"]")).click();
			Thread.sleep(5000);

			driver().findElement(By.xpath("//a[@title=\"Sign Out\"]")).click();

			driver().findElement(By.xpath("//a[@title=\"Logout\"]")).click();
			Thread.sleep(5000);
			driver().findElement(By.xpath("//a[@class=\"event_profile_login\"]")).click();

			driver().findElement(By.xpath("//input[@data-key=\"signInEmailAddress\"]")).sendKeys("selTest"+generatedString+"@gmail.com");
			driver().findElement(By.xpath("//input[@data-key=\"currentPassword\"]")).sendKeys("Test@123");
			driver().findElement(By.xpath("//input[@id=\"phdesktopbody_0_SIGN IN\"]")).click();

			Thread.sleep(5000);

			driver().findElement(By.xpath("//div[@class=\"pc_txtfld pc_Email\" and contains(.,\"selTest"+generatedString+"@gmail.com\")]")).isDisplayed();



			driver().manage().deleteAllCookies();




			driver().get("https://www.olay.com.cn/");

			driver().findElement(By.xpath("//div[@class=\"errorTips_icon\"]"));

			System.out.println("Chinese Olay.com page is not loading");

			System.out.println("Error message : 亲，您要访问的页面正在维护中 ");

		}catch(Exception unknown) {
			closeDriver();
		}

	}







	@DataProvider(name = "data", parallel = true)
	public Object[][] testdata() {
		Object[][] sjk = new Object[2][1];
		sjk[0][0] = "chrome";
		sjk[1][0] = "firefox";
		return sjk;
	}

}
