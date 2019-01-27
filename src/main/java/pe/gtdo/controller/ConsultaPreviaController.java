package pe.gtdo.controller;

import java.util.List;

import javax.ejb.ApplicationException;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;

import pe.gtdo.dao.ClienteDao;
import pe.gtdo.dao.MensajeDao;
import pe.gtdo.dao.MensajeRechazoDao;
import pe.gtdo.entity.BlacklistAbdcp;
import pe.gtdo.entity.Cliente;
import pe.gtdo.entity.MensajeRechazo;
import pe.gtdo.tipo.MensajeABDCP;
import pe.gtdo.tipo.TipoCabeceraMensaje;
import pe.gtdo.tipo.TipoCuerpoMensaje;
import pe.gtdo.tipo.TipoObjecionConcesionarioCedente;
import pe.gtdo.tipo.TipoRangoNumeracion;
import pe.gtdo.tipo.TipoSolicitudAceptadaCedente;
import pe.gtdo.util.FechaUtil;
import pe.gtdo.util.constante.ConsultaPrevia;
import pe.gtdo.util.constante.Proceso;

@ApplicationScoped
public class ConsultaPreviaController {

	private String proceso="05";
	private String abdcp="00";
	
	
	@Inject
	ClienteDao clienteDao;
	
	@Inject 
	MensajeDao mensajeDao;
	
	@Inject
	MensajeRechazoDao mensajeRechazoDao; 
	
	@Inject
	MensajeController mensajeController;
	
	
	@Inject
	FechaUtil fechaUtil;
	
	@Inject
	NotificacionErrorController notificacionErrorController;
	
	public void ejecutarProceso(MensajeABDCP mensaje) throws Exception{
		TipoCuerpoMensaje cuerpo = mensaje.getCuerpoMensaje();	
		TipoCabeceraMensaje cabecera = mensaje.getCabeceraMensaje();
		switch(ConsultaPrevia.fromType(cuerpo.getIdMensaje())){
		   
		    
		    case CP : //  se recibe el mensaje se hace la validacion  el adbcp
		    	      List<TipoRangoNumeracion> rangos = cuerpo.getConsultaPrevia().getNumeracionSolicitada().getRangoNumeracion();
		    	      for(TipoRangoNumeracion rango:rangos){
		    	    
		    	    	  BlacklistAbdcp rechazo = mensajeRechazoDao.getListanegra(null, ConsultaPrevia.CP.getValue(), rango.getInicioRango());
		    	    	  if(rechazo!=null){
		    	             // se envia un mensa de rechazo
		    	    		     
		    	    		   MensajeRechazo msgrRechazo = mensajeRechazoDao.getMensajeRechazo(rechazo.getCodigoRechazo());
		    	    		   
		    	    		   // NI  O -// NE
		    	    		   notificacionErrorController.enviarMensaje(msgrRechazo, cabecera.getRemitente(), mensaje);
		    	    		   
		    	    		   
		    	    		  
		    	    	  }else{
		    	    		 // se envia un mensaje ANCP
		    	    		  // pregunamos si el  numero tine rechazo despues de enviar un mensaje ANCP
		    	    		  String idSolicutud=mensajeDao.generarCodigo("00", Proceso.CP.getValue());
		    	    		  mensajeController.enviarANCP(mensaje, cabecera.getRemitente(),
		    	    				  idSolicutud
		    	    				  , rango.getInicioRango());
		    	    		  BlacklistAbdcp rechazo2 = mensajeRechazoDao.getListanegra(ConsultaPrevia.ANCP.getValue(),null, rango.getInicioRango());
		    	    		  // SI LO tienen  enviamos el mensaje CPRABD
		    	    		  if(rechazo2!=null){
		    	    			  //
		    	    			  //MensajeRechazo msgrRechazo2 = mensajeRechazoDao.getMensajeRechazo(rechazo2.getCodigoRechazo());
		    	    			  mensajeController.enviarCPRABD( idSolicutud,  cabecera.getRemitente(),
		    	    					  rango.getInicioRango(), rechazo2.getCodigoRechazo(), null, null, 
		    	    					  null, 
		    	    					  null,
		    	    					  null);
		    	    			  
		    	    		  }else{
		    	    			  
		    	    			  mensajeController.enviarECPC(mensaje, idSolicutud, cabecera.getDestinatario(), rango.getInicioRango(), rango.getTipoPortabilidadReceptor());
		    	    			  
		    	    		  }
		    	    		  // deelo contrario enviamos el mensaje 
		    	    	  }
		    	    	  
		    	    	  
		    	    	  
		    	    	  
		    	      }
		    break;		    
		    
		    case ECPC : // aca hay dos posiblidades  o enviar una cosnulta proceden o inprocendente 
		    	        // buscamos el cliente  
		    	         Cliente cliente = clienteDao.getClienteByNumero(cuerpo.getEnvioSolicitudCedente().getNumeracion(), cabecera.getDestinatario());
		    	         if(cliente!=null){		    	        	 
		    	        	 envioCedente(cabecera,cuerpo, cliente);
		    	         }else{
		    	        	 
		    	        	 mensajeController.enviarCPOCC(
		    	        			 cabecera.getDestinatario(), 
		    	        			 "00",
		    	        			 cabecera.getIdentificadorProceso(), 
		    	        			 "REC01PRT05",
		    	        			 null, 
		    	        			 null,
		    	        			 null, 
		    	        			 null,
		    	        			 null,
		    	        			 cuerpo.getEnvioSolicitudCedente().getNumeracion()); 
		    	         }
		    	          
		    break ;
		    
		    case CPOCC :
		    	              TipoObjecionConcesionarioCedente msgCPOCC = cuerpo.getObjecionConcesionarioCedente();
		    	              mensajeController.enviarCPRABD( cabecera.getIdentificadorProceso(), "46",
		    	            		  msgCPOCC.getNumeracion(), msgCPOCC.getCausaObjecion(),
		    	            		  msgCPOCC.getMonto(), msgCPOCC.getMoneda(), msgCPOCC.getFechaActivacion(), 
		    	            		  msgCPOCC.getFechaTerminoContratoEquipo(), msgCPOCC.getFechaVencimiento());
		    break;	

		    case CPAC :     
		    	             TipoSolicitudAceptadaCedente msgCPAC = cuerpo.getConsultaPreviaAceptadaCedente();
		    	            mensajeController.enviarCPPR(cabecera.getIdentificadorProceso(), "46", msgCPAC.getFechaActivacion(), msgCPAC.getFechaTerminoContratoEquipo());
		    break;
		    
		}
		
		
	}
	
	
	private void envioCedente(TipoCabeceraMensaje cabecera,TipoCuerpoMensaje cuerpo,Cliente cliente) throws Exception{
		
		if(cliente.getEstadoServicio().equals("02")) {
			mensajeController.enviarCPOCC(cabecera.getDestinatario(), "00", cabecera.getIdentificadorProceso()
					, "REC01PRT01"
					, fechaUtil.parseDateToString(cliente.getFechaActivacion(), "yyyyMMdd")
					,fechaUtil.parseDateToString(cliente.getFechaTerminoContratoEquipo(), "yyyyMMdd") 
					, null
					, null
					, null
					, cuerpo.getEnvioSolicitudCedente().getNumeracion());
			
			return ;
		}
		
		if(!cliente.getTipoServicio().equals(cuerpo.getEnvioSolicitudCedente().getTipoServicio())) {
			mensajeController.enviarCPOCC(cabecera.getDestinatario(), "00", cabecera.getIdentificadorProceso()
					, "REC01PRT06"
					, fechaUtil.parseDateToString(cliente.getFechaActivacion(), "yyyyMMdd")
					,fechaUtil.parseDateToString(cliente.getFechaTerminoContratoEquipo(), "yyyyMMdd") 
					, null
					, null
					, null
					, cuerpo.getEnvioSolicitudCedente().getNumeracion());
			
			return ;
			
		}
		
		if(!cliente.getDocIdentidad().equals(cuerpo.getEnvioSolicitudCedente().getNumeroDocumentoIdentidad())) {
			mensajeController.enviarCPOCC(cabecera.getDestinatario(), "00", cabecera.getIdentificadorProceso()
					, "REC01PRT07"
					, null
					, null 
					, null
					, null
					, null
					, cuerpo.getEnvioSolicitudCedente().getNumeracion());
			
			return ;
			
		}
		
		if(cliente.getMontoDeuda()>0D) {
			mensajeController.enviarCPOCC(cabecera.getDestinatario(), "00", cabecera.getIdentificadorProceso()
					, "REC01PRT09"
					, fechaUtil.parseDateToString(cliente.getFechaActivacion(), "yyyyMMdd")
					,fechaUtil.parseDateToString(cliente.getFechaTerminoContratoEquipo(), "yyyyMMdd") 
					, fechaUtil.parseDateToString(cliente.getFechaVencimientoUltimaFactura(), "yyyyMMdd") 
					, cliente.getMoneda()
					, cliente.getMontoDeuda().toString()
					, cuerpo.getEnvioSolicitudCedente().getNumeracion());
			
			return ;
		}
		
		
		mensajeController.enviarCPPR(cabecera.getDestinatario(), "00",
				fechaUtil.parseDateToString(cliente.getFechaActivacion(), "yyyyMMdd"),
				fechaUtil.parseDateToString(cliente.getFechaTerminoContratoEquipo(), "yyyyMMdd") );
		
	}
	
	
	
	
	
}
