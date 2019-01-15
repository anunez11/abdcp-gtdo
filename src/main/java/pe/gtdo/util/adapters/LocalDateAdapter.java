package pe.gtdo.util.adapters;

import javax.inject.Inject;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import pe.gtdo.util.FechaUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

	@Inject	
	private FechaUtil fecha;//= new FechaUtil();
	
	@Override
	public LocalDate unmarshal(String date) throws Exception {
		// TODO Auto-generated method stub		
		return fecha.parseStringToDate(date);
	}

	@Override
	public String marshal(LocalDate date) throws Exception {
		return fecha.parseDateToString(date);		
	}    
}