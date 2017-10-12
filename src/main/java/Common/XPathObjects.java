package Common;

public class XPathObjects {

	public static String UserName="//input[@name='userName']";
	public static String Password="//input[@name='password']";
	public static String LoginButton="//input[@name='login']";
	
	public static String FlightFinderImg="//img[@src='/images/masts/mast_flightfinder.gif']";
	public static String SignOffLink="//a[@href='mercurysignoff.php']";
	
	public static String RoundTripRadioButton="//form[@name='findflight']//input[@value='roundtrip']";
	public static String OneWayTripRadioButton="//form[@name='findflight']//input[@value='oneway']";
	
	public static String NoOfPassengers="//select[@name='passCount']";
	public static String DepartureLoc="//select[@name='fromPort']";
	public static String DepartureMonth="//select[@name='fromMonth']";
	public static String DepartureDate="//select[@name='fromDay']";
	public static String ArrivalLoc="//select[@name='toPort']";
	public static String ArrivalMonth="//select[@name='toMonth']";
	public static String ArrivalDate="//select[@name='toDay']";
	
	public static String EconomyTicket="//input[@name='servClass' and @value='Coach']";
	public static String BusinessTicket="//input[@name='servClass' and @value='Business']";
	public static String FirstTicket="//input[@name='servClass' and @value='First']";
	
	public static String AirlinePreference="//select[@name='airline']";
	
	public static String ContinueButton="//input[@name='findFlights']";
	
	public static String ContinueButtonOne="//input[@name='reserveFlights']";
	
	public static String SelectFlightImg="//img[@src='/images/masts/mast_selectflight.gif']";
	
	public static String SelectFlightRadioButton="//input[@name='outFlight' and @value='Blue Skies Airlines$361$271$7:10']";
	public static String SelectFlightRadioButtonOne="//input[@name='inFlight' and @value='Blue Skies Airlines$631$273$14:30']";
	
	public static String BookFlightImg="//img[@src='/images/masts/mast_book.gif']";
	
	public static String FirstName="//input[@name='passFirst0']";
	public static String LastName="//input[@name='passLast0']";
	public static String CardNumber="//input[@name='creditnumber']";
	
	public static String SecurePurchase="//input[@name='buyFlights']";
	
	public static String FlightConfirmImg="//img[@src='/images/masts/mast_confirmation.gif']";
	public static String FlightBookConfirmMessage="//table/tbody/tr[3]/td/p/font/b/font[2]";
	
	public static String LogoutButton="//img[@src='/images/forms/Logout.gif']";
	public static String BackToHome="//img[@src='/images/forms/home.gif']";
}
