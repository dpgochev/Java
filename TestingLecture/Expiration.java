package bg.uni.sofia.fmi.mjt.cache;

 
import java.util.Calendar;
public class Expiration {
	private Calendar expirationDate;

	public Expiration(int year, int month, int date, int hrs, int min) {
		this.expirationDate =  Calendar.getInstance();
		this.expirationDate.set(year, month, date, hrs, min);
	}
	public Calendar getExpirationDate() {
		return this.expirationDate;
	}

}
