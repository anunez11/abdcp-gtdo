package pe.gtdo.util.adapters;

import javax.inject.Inject;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import pe.gtdo.util.FechaUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

	@Inject	
	private FechaUtil fecha;
	
	@Override
	public LocalDateTime unmarshal(String date) throws Exception {
		// TODO Auto-generated method stub
		return fecha.parseStringToDateTime(date);
	}

	@Override
	public String marshal(LocalDateTime date) throws Exception {
		return  fecha.parseDateTimeToString(date);
		
	}    
}