package pe.gtdo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import pe.gtdo.util.FechaUtil;

@ApplicationScoped
public class HorarioController {
	
	
	@Inject
	FechaUtil fechaUtil;
	
	public String getFechaLimiteProgamcionPortabilidad(String tipoServicio,LocalDateTime fechaMensajeSP) throws Exception{
		
		return fechaUtil.parseDateTimeToString(LocalDateTime.now().plusDays(1), "yyyyMMddHHmmss");
	}
	
	public String getFechaLimiteEjecucionPortabilidad(String tipoCliente,LocalDateTime fechaMensajePP) throws Exception{
		return fechaUtil.parseDateTimeToString(LocalDateTime.now().plusDays(1), "yyyyMMddHHmmss");
	}
	
	public String getFechaLimiteEjecucionRetorno(LocalDateTime fechaMensajeSR) throws Exception{		
		return fechaUtil.parseDateTimeToString(LocalDateTime.now().plusDays(1), "yyyyMMddHHmmss");
	}
	
	public String getFechaLimiteAcreditacionPago(String tipoServicio,LocalDateTime fechaMensajeSP) throws Exception{		
		return fechaUtil.parseDateTimeToString(LocalDateTime.now().plusDays(1), "yyyyMMddHHmmss");
	}
	
	public LocalDateTime getFechaLimiteEnvioAcredtacion() throws Exception{
		String fechaHoy = fechaUtil.parseDateToString(LocalDate.now(), "yyyy-MM-dd");
		LocalDateTime fechaLiminate = fechaUtil.parseStringToDateTime(fechaHoy+" 22:00");
		if(LocalDateTime.now().getHour()>22 ){
			fechaHoy = fechaUtil.parseDateToString(LocalDate.now().plusDays(1L), "yyyy-MM-dd");
			 fechaLiminate = fechaUtil.parseStringToDateTime(fechaHoy+" 22:00");
		}
		
		return fechaLiminate;
		
	}
	
}
