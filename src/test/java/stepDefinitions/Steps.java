package stepDefinitions;



import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import generic.FileManager;
import generic.Screenshot;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pom.AddAppointmentPage;
import pom.AppointmentPage;
import pom.DoctorDashboardPage;
import pom.HmsLoginPage;


public class Steps {
	 WebDriver driver;
	 FileManager fm=new FileManager();
	 HmsLoginPage lp;
	 DoctorDashboardPage dp;
	 AppointmentPage ap;
	 AddAppointmentPage aap;
	 
	 @Before
	 public void setUp() {
		 WebDriverManager.chromedriver().setup();
	     driver=new ChromeDriver();
	     driver.get(fm.getPreProdurl());
	     driver.manage().window().maximize();
	     driver.manage().timeouts().implicitlyWait(fm.getImplicitlyWait(), TimeUnit.SECONDS);
	     lp=new HmsLoginPage(driver);
	     dp=new DoctorDashboardPage(driver);
	     ap=new AppointmentPage(driver);
	     aap=new AddAppointmentPage(driver);
	 }
	 
	 @After
     public void tearDown(Scenario scenario) {
		String tcName = scenario.getName(); 
		if(scenario.isFailed()) {
			new Screenshot().capture(driver, tcName);
		}
   	  driver.quit();  
     }
	
	 @Given("User is on Login Page")
	public void user_is_on_login_page() throws Throwable {
		 
	     System.out.println("User is on login page");
	 }

	 @When("User enters valid Username {string} and Password {string}") 
	    public void user_enters_valid_username_something_and_password_something(String Username, String Password) throws Throwable {
        lp.setUsername(Username);
        lp.setPassword(Password);
        
    }
    
    @And("clicks on Login button")
    public void clicks_on_login_button() throws Throwable {
    	lp.clickLogin();
    }
    
    @Then("Message should be displayed saying logged in successfully {string}")
    public void message_should_be_displayed_saying_logged_in_successfully(String etitle) throws InterruptedException {
    	
    	lp.verifyTitle(4, etitle);
      String atitle = driver.getTitle();
      Assert.assertEquals(etitle, atitle);
      }
    
    @Given("select payment mode type {string}")
    public void select_payment_mode_type(String mode) {
    System.out.println(mode);
    }
    
    @Then("order should be placed")
    public void order_should_be_placed(io.cucumber.datatable.DataTable dataTable) {
    	List<Map<String, String>> val = dataTable.asMaps(String.class, String.class);
    	for (int i = 0; i < val.size(); i++) {
		String paymentmode = val.get(i).get("mode");
    	String discount = val.get(i).get("discount");
    	String offer = val.get(i).get("offer");
    	if(paymentmode.contains("cod"))
    		System.out.println("Mode is "+paymentmode+", Discount is "+discount+", Offer is "+offer);
    	}
    }
    
    @When("verify user is on Doctor dashboard  page {string}")
    public void verify_user_is_on_doctor_dashboard_page(String etitle) {
       lp.verifyTitle(4, etitle);
       String atitle = driver.getTitle();
       Assert.assertEquals(etitle, atitle);
    }

    @When("clicks on Appointment")
    public void clicks_on_appointment() {
        dp.clickAppointment();
    }
    @When("clicks on AppointmentList")
    public void clicks_on_appointment_list() {
        dp.clickAppointmentList();
    }
    @When("verify user is on appointment page {string}")
    public void verify_user_is_on_appointment_page(String etitle) {
     dp.verifyTitle(4, etitle); 
     String atitle = driver.getTitle();
     Assert.assertEquals(etitle, atitle);
    }
    @When("clicks on AddAppointment")
    public void clicks_on_add_appointment() {
        ap.clickAddAppointment();
    }
    /*@When("verify user in on AddAppointment page {string}")
    public void verify_user_in_on_add_appointment_page(String etitle) {
        ap.verifyTab(6, 2);
    	//aap.verifyTitle(4, etitle);
        String atitle = driver.getTitle();
       // Assert.assertEquals(etitle, atitle);
        System.out.println(atitle);
    }*/
    
    @When("user clicks on date text box")
    public void user_clicks_on_date_text_box() {
        aap.clickDatebox();
    }
    @When("user selects the required date")
    public void user_selects_the_required_date() {
        aap.SelectDate();
    }
    @When("user clicks on time")
    public void user_clicks_on_time() {
        aap.clickTimebox();
    }
    @When("user selects required time")
    public void user_selects_required_time() {
        aap.selectHour();
        aap.selectMinute();
    }
    @When("user clicks on selectpatient dropdown")
    public void user_clicks_on_selectpatient_dropdown() {
        aap.clickSelectPatient();
    }
    @When("user enters patient name {string}")
    public void user_enters_patient_name(String name) {
        aap.enterPatientName(name);
    }
    @When("user clicks on submit button")
    public void user_clicks_on_submit_button() {
        aap.clickSubmitButton();
    }
    @Then("user should be able to provide appointment successfully")
    public void user_should_be_able_to_provide_appointment_successfully() {
        System.out.println("Appointment provided successfully to the patient");
    }
    
    }
    
    


   
