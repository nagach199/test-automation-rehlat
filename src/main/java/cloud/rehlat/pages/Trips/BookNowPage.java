package cloud.rehlat.pages.Trips;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import cloud.rehlat.constants.common.BrowserConstants;
import cloud.rehlat.utils.DataUtils;
import cloud.rehlat.utils.PageUtils;
import cloud.rehlat.utils.TestDataUtils;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class BookNowPage {
	public WebDriver driver;
	private PageUtils pageUtils;
	String userAccount;
	String SelectedFromCity;
	String SelectedToCity;

	public BookNowPage(WebDriver driver) {
		this.driver = driver;
		this.pageUtils = PageUtils.getInstance();
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(linkText = "Price")
	private WebElement btnPrice;
	@FindBy(xpath = "(//a[contains(text(),'Low to High')])[2]")
	private WebElement priceLowToHigh;
	@FindBy(xpath = "(//a[contains(text(),'High to Low')])[2]")
	private WebElement priceHighToLow;
	@FindBy(xpath = "(//a[contains(text(),'Flight Details')])[2]")
	private WebElement linkFlightDetailsTop;
	@FindBy(linkText = "Fare Rules")
	private WebElement btnFarerulesPopUp;
	@FindBy(xpath = "//div[@id='fareRulesPop1']/div/div/div[2]/table/tbody/tr[2]/td")
	private WebElement txtOfAdultName;
	@FindBy(xpath = "//div[@id='fareRulesPop1']/div/div/div[2]/table/tbody/tr[3]/td")
	private WebElement txtOfChildName;
	@FindBy(xpath = "//div[@id='fareRulesPop1']/div/div/div[2]/table/tbody/tr[4]/td")
	private WebElement txtOfInfantsName;
	@FindBy(xpath = "//div[@id='fareRulesPop1']/div/div/div[2]/table/tbody/tr[5]/td")
	private WebElement txtOftaxname;
	@FindBy(xpath = "//div[@id='fareRulesPop1']/div/div/div[2]/table/tbody/tr[5]/td[2]")
	private WebElement txtOfTotalValue;
	@FindBy(xpath = "//div[@id='fareRulesPop1']/div/div/div[2]/table/tbody/tr[6]/td")
	private WebElement txtOfPriceName;
	@FindBy(xpath = "//div[@id='fareRulesPop1']/div/div/div[2]/table/tbody/tr[6]/td[2]")
	private WebElement txtOfPriceValue;
	@FindBy(linkText = "Baggage Information")
	private WebElement btnBaggageInformation;
	@FindBy(xpath = "(//img[contains(@src,'/Content/images/close-icon.png')])[3]")
	private WebElement iconPopUpClose;
	@FindBy(xpath = "//div[2]/div/div/div[2]/button")
	private WebElement btnBookNowFirst;
	@FindBy(xpath = "//div[2]/div/div/div[2]/button")
	private List<WebElement> numberOfFlights;
	
	
	@FindBy(xpath = "//div[2]/ul/li/a/span[1]")
	private List<WebElement> listOfAirlines;
	@FindBy(xpath = "(//a[contains(text(),'Flight Details')])[2]")
	private WebElement linkFlightDetailsTopRoundTrip;
	@FindBy(xpath = "(//a[contains(text(),'Flight Details')])")
	private WebElement linkFlightDetailsTopMulticity;
	@FindBy(xpath = ".//*[@id='customeTab']/li[3]/a")
	private WebElement btnBaggageInformation_Mul;
	@FindBy(xpath = "//div[2]/div/div[2]/button")
	private WebElement btnBookNowFirstMulti;
	@FindBy(xpath = ".//*[@id='InnerDiv']/div[2]/div[2]/div/div[3]/table/tbody/tr/td[2]")
	private List<WebElement> airLineNames;
	
	//ShowMore Airlines Link
    @FindBy(xpath = ".//*[@id='InnerDiv']/div[2]/div[2]/div/div[3]/span[1]/a")
    private WebElement lnkAirLineShomeMore;
	public void listOfAirlineNames()
	{
		for(WebElement Airlines:listOfAirlines)
		{
			String AirlineNames=Airlines.getText();
			System.out.println("AirlineNames:"+AirlineNames);
		}
	}

	/**
	 * This method is used to filter the value(Hight to low OR Low to High) in
	 * book now page
	 * 
	 * @param pricefilter
	 */

	public void filter(String pricefilter) {
		pageUtils.clickElement(driver, btnPrice);
		if ("LOW TO HIGH".equalsIgnoreCase(pricefilter)) {
			pageUtils.clickElement(driver, priceLowToHigh);
		} else if ("HIGH TO LOW".equalsIgnoreCase(pricefilter)) {
			pageUtils.clickElement(driver, priceHighToLow);
		}
	}

	/**
	 * This method is used to get the details in flight Details PopUp page
	 * 
	 * @throws InterruptedException
	 */
	public void flightDetailsPopUp() throws InterruptedException {
		pageUtils.clickElement(driver, linkFlightDetailsTop);
		pageUtils.clickElement(driver, btnFarerulesPopUp);
		pageUtils.waitForFixedTime(BrowserConstants.WAIT_SMALL_ENGINE);

		try {
			if (txtOfAdultName.isDisplayed()) {
				System.out.println(1);
				String AdultName = pageUtils.getTextOfElement(driver, txtOfAdultName);
				String[] get = DataUtils.splitString(AdultName, " ");
				String Numof = get[0];

				String[] get1 = DataUtils.splitString(AdultName, "\\(");
				String[] spli = get1[1].split(" ");
				String[] spli1 = spli[2].split("\\)");
				String PerPersonValue = spli1[0];
				Double Multi = Double.parseDouble(Numof) * Double.parseDouble(PerPersonValue);
				Double AdultsTotal = new Double(Multi);
				// System.out.println("Adults Total Value:"+AdultsTotal);
				System.out.println(AdultName + "------" + Numof + "*" + PerPersonValue + "=" + AdultsTotal);

			}
		} catch (Exception e) {
			System.out.println("Adults Details are Not Displayed");
		}

		try {
			if (txtOfChildName.isDisplayed()) {
				String childName = pageUtils.getTextOfElement(driver, txtOfChildName);
				String[] get = DataUtils.splitString(childName, " ");
				String numofChilds = get[0];
				String[] ch2 = DataUtils.splitString(childName, "\\(");
				String[] ch3 = ch2[1].split(" ");
				String[] ch4 = ch3[2].split("\\)");
				String PerPersonChildvalue = ch4[0];
				Double MultiChild = Double.parseDouble(numofChilds) * Double.parseDouble(PerPersonChildvalue);
				Double ChildTotal = new Double(MultiChild);
				// System.out.println("Child Total Value::" + ChildTotal);
				System.out.println(childName + "------" + numofChilds + "*" + PerPersonChildvalue + "=" + ChildTotal);

			}
		} catch (Exception e) {
			System.out.println("Child Details are Not Displayed");
		}
		try {
			if (txtOfInfantsName.isDisplayed()) {
				String infantsName = pageUtils.getTextOfElement(driver, txtOfInfantsName);
				String[] get = DataUtils.splitString(infantsName, " ");
				String numofinfants = get[0];
				String[] I2 = DataUtils.splitString(infantsName, "\\(");
				String[] I3 = I2[1].split(" ");
				String[] I4 = I3[2].split("\\)");
				String PerPersonInfantvalue = I4[0];
				Double MultiInfants = Double.parseDouble(numofinfants) * Double.parseDouble(PerPersonInfantvalue);
				Double InfantTotal = new Double(MultiInfants);
				// System.out.println("Infants Total value::" + InfantTotal);
				System.out
						.println(infantsName + "-----" + numofinfants + "*" + PerPersonInfantvalue + "=" + InfantTotal);
			}

		} catch (Exception e) {
			System.out.println("Infants Details are Not Displayed");
		}
		String taxName = pageUtils.getTextOfElement(driver, txtOftaxname);
		String taxValue = pageUtils.getTextOfElement(driver, txtOfTotalValue);
		System.out.println(taxName + "---------------" + taxValue);

		String priceName = pageUtils.getTextOfElement(driver, txtOfPriceName);
		String priceValue = pageUtils.getTextOfElement(driver, txtOfPriceValue);
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println(priceName + "-----------------" + priceValue);

		pageUtils.clickElement(driver, btnBaggageInformation);
	}

	public void flightDetailsPopUp_OneWay() throws InterruptedException {
		pageUtils.clickElement(driver, linkFlightDetailsTop);
		pageUtils.clickElement(driver, btnFarerulesPopUp);
		pageUtils.waitForFixedTime(BrowserConstants.WAIT_VERY_SMALL_ENGINE);

		try {
			if (txtOfAdultName.isDisplayed()) {
				System.out.println(1);
				String AdultName = pageUtils.getTextOfElement(driver, txtOfAdultName);
				String[] get = DataUtils.splitString(AdultName, " ");
				String Numof = get[0];

				String[] get1 = DataUtils.splitString(AdultName, "\\(");
				String[] spli = get1[1].split(" ");
				String[] spli1 = spli[2].split("\\)");
				String PerPersonValue = spli1[0];
				Double Multi = Double.parseDouble(Numof) * Double.parseDouble(PerPersonValue);
				Double AdultsTotal = new Double(Multi);
				// System.out.println("Adults Total Value:"+AdultsTotal);
				System.out.println(AdultName + "------" + Numof + "*" + PerPersonValue + "=" + AdultsTotal);

			}
		} catch (Exception e) {
			System.out.println("Adults Details are Not Displayed");
		}

		try {
			if (txtOfChildName.isDisplayed()) {
				String childName = pageUtils.getTextOfElement(driver, txtOfChildName);
				String[] get = DataUtils.splitString(childName, " ");
				String numofChilds = get[0];
				String[] ch2 = DataUtils.splitString(childName, "\\(");
				String[] ch3 = ch2[1].split(" ");
				String[] ch4 = ch3[2].split("\\)");
				String PerPersonChildvalue = ch4[0];
				Double MultiChild = Double.parseDouble(numofChilds) * Double.parseDouble(PerPersonChildvalue);
				Double ChildTotal = new Double(MultiChild);
				// System.out.println("Child Total Value::" + ChildTotal);
				System.out.println(childName + "------" + numofChilds + "*" + PerPersonChildvalue + "=" + ChildTotal);

			}
		} catch (Exception e) {
			System.out.println("Child Details are Not Displayed");
		}
		try {
			if (txtOfInfantsName.isDisplayed()) {
				String infantsName = pageUtils.getTextOfElement(driver, txtOfInfantsName);
				String[] get = DataUtils.splitString(infantsName, " ");
				String numofinfants = get[0];
				String[] I2 = DataUtils.splitString(infantsName, "\\(");
				String[] I3 = I2[1].split(" ");
				String[] I4 = I3[2].split("\\)");
				String PerPersonInfantvalue = I4[0];
				Double MultiInfants = Double.parseDouble(numofinfants) * Double.parseDouble(PerPersonInfantvalue);
				Double InfantTotal = new Double(MultiInfants);
				// System.out.println("Infants Total value::" + InfantTotal);
				System.out
						.println(infantsName + "-----" + numofinfants + "*" + PerPersonInfantvalue + "=" + InfantTotal);
			}

		} catch (Exception e) {
			System.out.println("Infants Details are Not Displayed");
		}
		String taxName = pageUtils.getTextOfElement(driver, txtOftaxname);
		String taxValue = pageUtils.getTextOfElement(driver, txtOfTotalValue);
		System.out.println(taxName + "---------------" + taxValue);

		String priceName = pageUtils.getTextOfElement(driver, txtOfPriceName);
		String priceValue = pageUtils.getTextOfElement(driver, txtOfPriceValue);
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println(priceName + "-----------------" + priceValue);

		pageUtils.clickElement(driver, btnBaggageInformation);
		pageUtils.waitForFixedTime(BrowserConstants.WAIT_VERY_SMALL_ENGINE);
	}

	
	public void flightDetailsPopUp_RoundTrip() throws InterruptedException {
		pageUtils.clickElement(driver, linkFlightDetailsTopRoundTrip);

		pageUtils.clickElement(driver, btnFarerulesPopUp);
		pageUtils.waitForFixedTime(BrowserConstants.WAIT_VERY_SMALL_ENGINE);
		try {
			if (txtOfAdultName.isDisplayed()) {
				System.out.println(1);
				String AdultName = pageUtils.getTextOfElement(driver, txtOfAdultName);
				String[] get = DataUtils.splitString(AdultName, " ");
				String Numof = get[0];

				String[] get1 = DataUtils.splitString(AdultName, "\\(");
				String[] spli = get1[1].split(" ");
				String[] spli1 = spli[2].split("\\)");
				String PerPersonValue = spli1[0];
				Double Multi = Double.parseDouble(Numof) * Double.parseDouble(PerPersonValue);
				Double AdultsTotal = new Double(Multi);
				// System.out.println("Adults Total Value:"+AdultsTotal);
				System.out.println(AdultName + "------" + Numof + "*" + PerPersonValue + "=" + AdultsTotal);

			}
		} catch (Exception e) {
			System.out.println("Adults Details are Not Displayed");
		}

		try {
			if (txtOfChildName.isDisplayed()) {
				String childName = pageUtils.getTextOfElement(driver, txtOfChildName);
				String[] get = DataUtils.splitString(childName, " ");
				String numofChilds = get[0];
				String[] ch2 = DataUtils.splitString(childName, "\\(");
				String[] ch3 = ch2[1].split(" ");
				String[] ch4 = ch3[2].split("\\)");
				String PerPersonChildvalue = ch4[0];
				Double MultiChild = Double.parseDouble(numofChilds) * Double.parseDouble(PerPersonChildvalue);
				Double ChildTotal = new Double(MultiChild);
				// System.out.println("Child Total Value::" + ChildTotal);
				System.out.println(childName + "------" + numofChilds + "*" + PerPersonChildvalue + "=" + ChildTotal);

			}
		} catch (Exception e) {
			System.out.println("Child Details are Not Displayed");
		}
		try {
			if (txtOfInfantsName.isDisplayed()) {
				String infantsName = pageUtils.getTextOfElement(driver, txtOfInfantsName);
				String[] get = DataUtils.splitString(infantsName, " ");
				String numofinfants = get[0];
				String[] I2 = DataUtils.splitString(infantsName, "\\(");
				String[] I3 = I2[1].split(" ");
				String[] I4 = I3[2].split("\\)");
				String PerPersonInfantvalue = I4[0];
				Double MultiInfants = Double.parseDouble(numofinfants) * Double.parseDouble(PerPersonInfantvalue);
				Double InfantTotal = new Double(MultiInfants);
				// System.out.println("Infants Total value::" + InfantTotal);
				System.out
						.println(infantsName + "-----" + numofinfants + "*" + PerPersonInfantvalue + "=" + InfantTotal);
			}

		} catch (Exception e) {
			System.out.println("Infants Details are Not Displayed");
		}
		String taxName = pageUtils.getTextOfElement(driver, txtOftaxname);
		String taxValue = pageUtils.getTextOfElement(driver, txtOfTotalValue);
		System.out.println(taxName + "---------------" + taxValue);

		String priceName = pageUtils.getTextOfElement(driver, txtOfPriceName);
		String priceValue = pageUtils.getTextOfElement(driver, txtOfPriceValue);
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println(priceName + "-----------------" + priceValue);

		pageUtils.clickElement(driver, btnBaggageInformation);
		pageUtils.waitForFixedTime(BrowserConstants.WAIT_VERY_SMALL_ENGINE);
	}

	public void flightDetailsPopUp_MultiCity() throws InterruptedException {
		pageUtils.clickElement(driver, linkFlightDetailsTopMulticity);

		pageUtils.clickElement(driver, btnFarerulesPopUp);
		pageUtils.waitForFixedTime(BrowserConstants.WAIT_VERY_SMALL_ENGINE);


		pageUtils.clickElement(driver, btnBaggageInformation_Mul);
		pageUtils.waitForFixedTime(BrowserConstants.WAIT_VERY_SMALL_ENGINE);
	}
	
	
	/**
	 * This method is used to Click On Book now Top Button
	 */
	public void clickOnBookNow_One_Way() {
		pageUtils.clickElement(driver, btnBookNowFirst);
		
	}

public void clickOnBookNow_RoundTrip() {
	pageUtils.clickElement(driver, btnBookNowFirst);
 }
public void clickOnBookNow_MultiCity() {
	pageUtils.clickElement(driver, btnBookNowFirstMulti);
 }


public void airLineShowMoreLink() throws InterruptedException
{
    pageUtils.scrollDown(driver);
    try{
        if(lnkAirLineShomeMore.isDisplayed()){
    pageUtils.clickElement(driver, lnkAirLineShomeMore);
        }
    }
    catch(Exception e)
    {
        
    }
    //airLineName("Emirates");
    
}
	/**
	 * This method is used to close the flight Details PopUp page
	 */
	public void clickOnPopUpClose() {
		pageUtils.clickElement(driver, iconPopUpClose);
	}
	
	 public  void SelectAirline(String value) throws InterruptedException, RowsExceededException, WriteException
	    {
	        
	        for (int i = 1; i < airLineNames.size(); i++) {
	            String test = driver.findElement(By.xpath(".//*[@id='InnerDiv']/div[2]/div[2]/div/div[3]/table/tbody/tr[" + i + "]/td[2]")).getText();
	            System.out.println("AirLine Name:" + test);
	            
	            
	            
	            if (value.equals(test)) {
	                System.out.println("value:"+value);
	                String one=test;
	                WebElement e = driver.findElement(By.xpath(".//*[@id='InnerDiv']/div[2]/div[2]/div/div[3]/table/tbody/tr[" + i + "]/td[1]"));
	               e.click();
	              pageUtils.waitForFixedTime(BrowserConstants.WAIT_VERY_SMALL);
	              break;
	            }
	            else
	            {
	                 System.out.println("No Airlines Found");
	                
	                 
	            }
	        
	    
	    }
	        
	    }

	/**
	 * this method is used to get the number of flights available in book now
	 * page
	 * 
	 * @throws InterruptedException
	 */

	public void numberOfFlights() throws InterruptedException {
		List<WebElement> currentpage = numberOfFlights;
		System.out.println("Number Of Flights In Current Page:" + currentpage.size());
		pageUtils.waitForFixedTime(BrowserConstants.WAIT_SMALL_ENGINE);
	}

	/**
	 * This method is used to Click On Book now Top Button
	 */
	public void clickOnBookNow() {
		pageUtils.clickElement(driver, btnBookNowFirst);
	}
}
