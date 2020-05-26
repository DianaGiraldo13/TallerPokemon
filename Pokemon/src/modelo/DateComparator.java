package modelo;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class DateComparator implements Comparator<Jugador>{

	@Override
	public int compare(Jugador o1, Jugador o2) {
		// TODO Auto-generated method stub
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date1;
		Date date2;
		try {
			date1 = format.parse(o1.getFechaRegistro());
			date2 = format.parse(o2.getFechaRegistro());
			return date1.compareTo(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return 0;
		}
		
		
	}

}
