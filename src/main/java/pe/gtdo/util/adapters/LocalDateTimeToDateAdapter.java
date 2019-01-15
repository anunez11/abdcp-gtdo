package pe.gtdo.util.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class LocalDateTimeToDateAdapter extends XmlAdapter<String, LocalDateTime> {

	@Override
	public LocalDateTime unmarshal(String v) throws Exception {
		// TODO Auto-generated method stub
		return LocalDateTime.parse(v, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	@Override
	public String marshal(LocalDateTime v) throws Exception {
		return  v.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
		
	}    
}