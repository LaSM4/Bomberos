package py.edu.facitec.reutilizacion.util;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class FechaUtil {
	public static LocalDate toLocalDate(Date date) {
		if (date == null) return null;
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public static Date toLegacyDate(LocalDate localDate) {
		if (localDate == null) return null;
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
}
