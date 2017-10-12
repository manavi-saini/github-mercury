package Beans;

public class FlightDetailsOne {
	
	/*public FlightDetailsOne(String NoOfPassengers,String DepartureLocation, String DepartureMonth, String DepartureDate, String ArrivalLocation, 
			String ArrivalMonth,String ArrivalDate) {
		this.NoOfPassengers = NoOfPassengers;
		this.DepartureLocation = DepartureLocation;
		this.DepartureMonth = DepartureMonth; 
		this.DepartureDate = DepartureDate;
		this.ArrivalLocation = ArrivalLocation;
		this.ArrivalMonth = ArrivalMonth; 
		this.ArrivalDate = ArrivalDate;
	}*/

	public String NoOfPassengers;
	public String DepartureLocation;
	public String DepartureMonth;
	public String DepartureDate;
	public String ArrivalLocation;
	public String ArrivalMonth;
	public String ArrivalDate;

	public String getNoOfPassengers() {
		return NoOfPassengers;
	}
	public void setNoOfPassengers(String NoOfPassengers) {
		this.NoOfPassengers = NoOfPassengers;
	}

	public String getDepartureLocation() {
		return DepartureLocation;
	}
	public void setDepartureLocation(String DepartureLocation) {
		this.DepartureLocation = DepartureLocation;
	}

	public String getDepartureMonth() {
		return DepartureMonth;
	}
	public void setDepartureMonth(String DepartureMonth) {
		this.DepartureMonth = DepartureMonth;
	}

	public String getDepartureDate() {
		return DepartureDate;
	}
	public void setDepartureDate(String DepartureDate) {
		this.DepartureDate = DepartureDate;
	}

	public String getArrivalLocationn() {
		return ArrivalLocation;
	}
	public void setArrivalLocation(String ArrivalLocation) {
		this.ArrivalLocation = ArrivalLocation;
	}

	public String getArrivalMonth() {
		return ArrivalMonth;
	}
	public void setArrivalMonth(String ArrivalMonth) {
		this.ArrivalMonth = ArrivalMonth;
	}

	public String getArrivalDate() {
		return ArrivalDate;
	}
	public void setArrivalDate(String ArrivalDate) {
		this.ArrivalDate = ArrivalDate;
	}
}
