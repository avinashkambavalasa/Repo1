package ZBBasics;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ZBBasics.ZBConstants;

public class ZBTestCases extends ZBBase {

	public String sheetname = "Transfer Funds";
	
	public ZBTestCases() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeTest
	public void setUp() throws InterruptedException {
		initialization();
	}

	
	@DataProvider
	public Object[][] getTFTestData() {
		Object [][] TFdata = ZBConstants.getTestData(sheetname);
		return TFdata;
	}
	
	  @Test(priority=1, dataProvider="getTFTestData")
	  public void transferFunds(String From_Acct, String To_Acct, String Amt, String desc) {
		 
		  System.out.println(From_Acct+" "+To_Acct);
		  
		  int f_acct = Float.valueOf(From_Acct).intValue();
		  int t_acct = Float.valueOf(To_Acct).intValue();
		  System.out.println(f_acct+" "+t_acct);

		  driver.findElement(By.linkText("Transfer Funds")).click();
		  {
		      Select fromAccount = new Select(driver.findElement(By.id("tf_fromAccountId")));
		      fromAccount.selectByIndex(f_acct-1);
		  }
		  driver.findElement(By.id("tf_fromAccountId")).click();
		  
		  {
		      Select toAccount = new Select(driver.findElement(By.id("tf_toAccountId")));
		      toAccount.selectByIndex(t_acct-1);
		  }
		  driver.findElement(By.id("tf_toAccountId")).click();
		  		 		  
		  driver.findElement(By.id("tf_amount")).click();
		  driver.findElement(By.id("tf_amount")).sendKeys(Amt);
		  
		  driver.findElement(By.id("tf_description")).click();
		  driver.findElement(By.id("tf_description")).sendKeys(desc);
		  
		  driver.findElement(By.id("btn_submit")).click();
		  driver.findElement(By.id("btn_submit")).click();
		  
	  }

	  
	@DataProvider
	public Object[][] getPBTestData() {
		sheetname = "Pay Bills";
		Object [][] PBdata = ZBConstants.getTestData(sheetname);
		return PBdata;
	}
	  
	@Test(priority=2, dataProvider="getPBTestData")
	public void billPayment(String Payee, String From_Account_Value, String Amount, String Date, String description) {
	    
		driver.findElement(By.linkText("Pay Bills")).click();
	    
		{
	      Select payee = new Select(driver.findElement(By.id("sp_payee")));
	      payee.selectByVisibleText(Payee);
	    }
	   
		/*
		int f_acct_idx = Float.valueOf(From_Account_Value).intValue();
		Select from_Account_Value = new Select(driver.findElement(By.id("sp_account")));
	    from_Account_Value.selectByIndex(f_acct_idx-1);
	    */
	    
	    {
	      Select from_Account_Value = new Select(driver.findElement(By.id("sp_account")));
	      from_Account_Value.selectByVisibleText(From_Account_Value);
	    }
	    
	    driver.findElement(By.id("sp_amount")).click();
	    driver.findElement(By.id("sp_amount")).sendKeys(Amount);
	    
	    driver.findElement(By.id("sp_date")).click();
	    driver.findElement(By.id("sp_date")).sendKeys(Date);
	    
	    driver.findElement(By.id("sp_description")).click();
	    driver.findElement(By.id("sp_description")).sendKeys(description);
	    
	    driver.findElement(By.id("pay_saved_payees")).click();
	    
	  }
	
	@DataProvider
	public Object[][] getANPTestData() {
		sheetname = "Add Payee";
		Object [][] ANPdata = ZBConstants.getTestData(sheetname);
		return ANPdata;
	}
	  
	@Test(priority=3, dataProvider="getANPTestData")
	public void addNewPayee(String PayeeName, String Address, String Account_Type, String Payee_Details) {
		
		driver.findElement(By.linkText("Pay Bills")).click();
	    driver.findElement(By.linkText("Add New Payee")).click();
	    
	    driver.findElement(By.id("np_new_payee_name")).click();
	    driver.findElement(By.id("np_new_payee_name")).sendKeys(PayeeName);
	    
	    driver.findElement(By.id("np_new_payee_address")).sendKeys(Address);
	    driver.findElement(By.id("np_new_payee_account")).sendKeys(Account_Type);
	    driver.findElement(By.id("np_new_payee_details")).sendKeys(Payee_Details);
	    driver.findElement(By.id("add_new_payee")).click();
	}
	
	
	@DataProvider
	public Object[][] getPFCTestData() {
		sheetname = "Purchase CCY";
		Object [][] PFCdata = ZBConstants.getTestData(sheetname);
		return PFCdata;
	}
	  
	@Test(priority=4, dataProvider="getPFCTestData")
	public void purchaseCurrency(String ccy, String p_ccy, String Amount) {
		
		driver.findElement(By.linkText("Pay Bills")).click();
	    driver.findElement(By.linkText("Purchase Foreign Currency")).click();
	    driver.findElement(By.id("pc_currency")).click();
	  	   
	    {
	    	//WebElement currency = driver.findElement(By.id("pc_currency"));
	    	//currency.findElement(By.xpath("//select[@id='pc_currency']/option[contains(text(),ccy)]")).click();
	    	//currency.findElement(By.xpath("//option[contains(text(),ccy)]")).click();
	    	
	    	Select currency = new Select(driver.findElement(By.id("pc_currency")));
			currency.selectByValue(ccy);
    	}
	    
	    if (p_ccy.equals("USD")) {
	    	driver.findElement(By.id("pc_inDollars_true")).click();
	    }else {
		    driver.findElement(By.id("pc_inDollars_false")).click();
	    }
	    
	    driver.findElement(By.id("pc_amount")).click();
	    driver.findElement(By.id("pc_amount")).sendKeys(Amount);
	    
	    driver.findElement(By.id("pc_calculate_costs")).click();
	    driver.findElement(By.id("purchase_cash")).click();  
	}
	
	
	@DataProvider
	public Object[][] getFTTestData() {
		sheetname = "Finding Transaction";
		Object [][] FTdata = ZBConstants.getTestData(sheetname);
		return FTdata;
	}
	  
	@Test(priority=5, dataProvider="getFTTestData")
	public void findTransaction(String description, String start_date, String end_date, String min_amount, String max_amount) {
		
	    driver.findElement(By.linkText("Account Activity")).click();
	    driver.findElement(By.linkText("Find Transactions")).click();
	    
	    driver.findElement(By.id("aa_description")).sendKeys(description);
	    
	    driver.findElement(By.id("aa_fromDate")).sendKeys(start_date);
	    driver.findElement(By.id("aa_toDate")).sendKeys(end_date);
	    
	    driver.findElement(By.id("aa_fromAmount")).sendKeys(min_amount);
	    driver.findElement(By.id("aa_toAmount")).sendKeys(max_amount);
	    driver.findElement(By.cssSelector(".btn")).click();
	    
	    List<WebElement> transactions = driver.findElements(By.xpath("//div[@id='filtered_transactions_for_account']"
	    		+ "/table[@class='table table-condensed table-hover']/tbody/tr"));
	    int no_of_transactions = transactions.size();
	    System.out.println("number of transactions found were: "+no_of_transactions);
	    
	    if (no_of_transactions!=0) {
		    //Printing data
		    //Get number of rows In table.
		    int Row_count = transactions.size();
		    System.out.println("Number Of Rows = "+Row_count);
		    
		    //Get number of columns In table.
		    int Col_count = driver.findElements(By.xpath("//div[@id='filtered_transactions_for_account']"
		    		+ "/table[@class='table table-condensed table-hover']/thead/tr/th")).size();
		    System.out.println("Number Of Columns = "+Col_count);
		    
		    for (int k=1; k<=Col_count; k++){
		    String Table_Head = driver.findElement(By.xpath("//div[@id='filtered_transactions_for_account']"
		    		+ "/table[@class='table table-condensed table-hover']/thead/tr/th["+k+"]")).getText();
		    System.out.print(Table_Head +"||");
		    }
		    System.out.print("\n");
		    //divided xpath In three parts to pass Row_count and Col_count values.
		    String first_part = "//div[@id='filtered_transactions_for_account']/"
		    		+ "table[@class='table table-condensed table-hover']/tbody/tr[";
		    String second_part = "]/td[";
		    String third_part = "]";
		    
		    for (int i=1; i<=Row_count; i++){
		    	for(int j=1; j<=Col_count; j++){
		    		String final_xpath = first_part+i+second_part+j+third_part;
				    String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
				    System.out.print(Table_data +"||");
		    	}
		    System.out.println("\n");
		    } 
		    
	    }
	    
	}
	
	  @AfterTest
	  public void tearDown() {
		  driver.findElement(By.linkText("username")).click();
		  driver.findElement(By.id("logout_link")).click();
		  driver.quit();
	  }
	  
	
}
