package pe.gtdo.controller;

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
	
}
