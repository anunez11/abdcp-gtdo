package pe.gtdo.controller;

import javax.faces.bean.ApplicationScoped;

import pe.gtdo.tipo.MensajeABDCP;
import pe.gtdo.tipo.TipoCabeceraMensaje;
import pe.gtdo.tipo.TipoCuerpoMensaje;
import pe.gtdo.util.constante.ConsultaPrevia;
import pe.gtdo.util.constante.SolicitudPortabilidad;

@ApplicationScoped
public class SolicitudPortabilidadController {

	
	public void ejecutarProceso(MensajeABDCP mensaje) throws Exception{
		
		TipoCuerpoMensaje cuerpo = mensaje.getCuerpoMensaje();	
		TipoCabeceraMensaje cabecera = mensaje.getCabeceraMensaje();
		switch(SolicitudPortabilidad.fromType(cuerpo.getIdMensaje())){
		
		        
		
			case SP:
				break;
			case ANS:
				break;
			case ESC:
				break;
			case OCC:
				break;
			case SAC:
				break;
			case APD:
				break;
			case APDC:
				break;
			case RABDCP:
				break;
			case SPR:
				break;
			case CPSPR:
				break;
			case CNPF:
				break;
			case PP:
				break;
			case FLEP:
				break;
			case PEP:
				break;
		
		
		}
		
		
		
		
	}	
	
	
}
