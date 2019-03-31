package pe.gtdo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;

import org.w3c.dom.Document;

import pe.gtdo.dao.ClienteDao;
import pe.gtdo.dao.MensajeDao;
import pe.gtdo.dao.MensajeRechazoDao;
import pe.gtdo.entity.AcreditacionPago;
import pe.gtdo.entity.BlacklistAbdcp;
import pe.gtdo.entity.Cliente;
import pe.gtdo.entity.MensajeAbdcp;
import pe.gtdo.entity.MensajeRechazo;
import pe.gtdo.entity.ProgramacionPortabilidad;
import pe.gtdo.tipo.MensajeABDCP;
import pe.gtdo.tipo.TipoCabeceraMensaje;
import pe.gtdo.tipo.TipoCuerpoMensaje;
import pe.gtdo.tipo.TipoObjecionConcesionarioCedente;
import pe.gtdo.tipo.TipoRangoNumeracion;
import pe.gtdo.tipo.TipoRechazadaABDCP;
import pe.gtdo.tipo.TipoSolicitudPortabilidad;
import pe.gtdo.tipo.TipoSolicitudProcedente;
import pe.gtdo.util.FechaUtil;
import pe.gtdo.util.Utilitario;
import pe.gtdo.util.constante.ConsultaPrevia;
import pe.gtdo.util.constante.Proceso;
import pe.gtdo.util.constante.SolicitudPortabilidad;

@ApplicationScoped
public class SolicitudPortabilidadController {
	
	@Inject
	ClienteDao clienteDao;
	
	@Inject 
	MensajeDao mensajeDao;
	
	@Inject
	MensajeRechazoDao mensajeRechazoDao; 
	
	@Inject
	MensajeController mensajeController;
	
	@Inject
	NotificacionErrorController notificacionErrorController;
	
	@Inject
	FechaUtil fechaUtil;
	
	@Inject
	Utilitario utilitario;
	
	
	@Inject
	HorarioController horarioController;
	
	public void ejecutarProceso(byte[] archivo,MensajeABDCP mensaje) throws Exception{
		
		TipoCuerpoMensaje cuerpo = mensaje.getCuerpoMensaje();	
		TipoCabeceraMensaje cabecera = mensaje.getCabeceraMensaje();
		switch(SolicitudPortabilidad.fromType(cuerpo.getIdMensaje())){
				        
		
			case SP:
			      System.out.println("=================================================================>");
			      System.out.println(" SP =================>"+cuerpo.getSolicitudPortabilidad());
			      System.out.println("=================================================================>");
				      List<TipoRangoNumeracion> rangos = cuerpo.getSolicitudPortabilidad().getNumeracionSolicitada().getRangoNumeracion();
	    	          for(TipoRangoNumeracion rango:rangos){
	    	        	  
	    	        	  String numero=rango.getInicioRango();
		    	          String tipoPortabilidad=rango.getTipoPortabilidadCedente();
		    	          String cedente=cuerpo.getSolicitudPortabilidad().getCodigoCedente();
		    	          String receptor=cuerpo.getSolicitudPortabilidad().getCodigoReceptor();
		    	    	  BlacklistAbdcp rechazo = mensajeRechazoDao.getListanegra(null, SolicitudPortabilidad.SP.getValue(), numero);
	    	        	  
		    	    	     if(rechazo!=null){
			    	             // se envia un mensa de rechazo
			    	    		     
			    	    		   MensajeRechazo msgrRechazo = mensajeRechazoDao.getMensajeRechazo(rechazo.getCodigoRechazo());
			    	    		   
			    	    		   // NI  O -// NE
			    	    		   notificacionErrorController.enviarMensaje(msgrRechazo, receptor, mensaje);
			    	    		   
			    	    		   
			    	    		  
			    	    	  }else{
			    	    		 // se envia un mensaje ANS
			    	    		  // pregunamos si el  numero tine rechazo despues de enviar un mensaje ANCP
			    	    		  String idSolicitud=mensajeDao.generarCodigo("00", Proceso.SP.getValue());
			    	    		  mensajeController.enviarANS(mensaje,receptor,
			    	    				  idSolicitud
			    	    				  , rango.getInicioRango());
			    	    		  
			    	    		  BlacklistAbdcp rechazo2 = mensajeRechazoDao.getListanegra(SolicitudPortabilidad.ANS.getValue(),null, numero);
			    	    		  // SI LO tienen  enviamos el mensaje CPRABD
			    	    		  if(rechazo2!=null){		    	    			
			    	    			  mensajeController.enviarRABDCP( idSolicitud,  receptor,
			    	    					  rango.getInicioRango(), rechazo2.getCodigoRechazo(), null, null, 
			    	    					  null, 
			    	    					  null,
			    	    					  null);
			    	    			  
			    	    		  }else {
			    	    			  
			    	    			  
			    	    			      MensajeAbdcp consultaPreviaProcedente = mensajeDao.getMensajeAbdcpTipoNumeroDia(numero,LocalDate.now(),ConsultaPrevia.CPPR.getValue());
			    	    			      
			    	    			      if(consultaPreviaProcedente!=null){
			    	    			    	  String fechaLimiteProgamacion =horarioController.getFechaLimiteProgamcionPortabilidad(cuerpo.getSolicitudPortabilidad().getTipoServicio(), LocalDateTime.now());
			    	    			    	  String fechaLiminteEjecucion=horarioController.getFechaLimiteEjecucionPortabilidad(cuerpo.getSolicitudPortabilidad().getCliente(), LocalDateTime.now());
			    	    			    	  mensajeController.enviarCPSPR(idSolicitud, consultaPreviaProcedente.getIdSolicitud(), receptor, fechaLimiteProgamacion, fechaLiminteEjecucion);
				    	    			      mensajeController.enviarCPSPR(idSolicitud, consultaPreviaProcedente.getIdSolicitud(), cedente, fechaLimiteProgamacion, fechaLiminteEjecucion);  
			    	    			     
			    	    			      }else{
			    	    			    	  mensajeController.enviarECPC(mensaje, idSolicitud, cedente, numero,tipoPortabilidad);
					    	    			  
			    	    			      }
			    	    			      
			    	    			      
			    	    			      
			    	    			      
			    	    			      
			    	    			     
			    	    			  
			    	    			  
			    	    		  } 
			    	    			
			    	    		 
			    	    	  }
			    	    	  
		    	    	  
		    	    	  
		    	    	  
	    	        	  
	    	        	  
	    	        	  
	    	    	  
	    	          }
				
				
				break;
			case ANS:
				break;
			case ESC:      Cliente cliente = clienteDao.getClienteByNumero(cuerpo.getEnvioSolicitudCedente().getNumeracion(), cabecera.getDestinatario());
					       if(cliente!=null){		    	        	 
					        	 envioCedente(cabecera,cuerpo, cliente);
					         }else{
					        	 
					        	 mensajeController.enviarOCC(
					        			 cabecera.getDestinatario(),					        			 
					        			 cabecera.getIdentificadorProceso(), 
					        			 "REC01PRT05",
					        			 null, 
					        			 null,
					        			 null, 
					        			 null,
					        			 null,
					        			 cuerpo.getEnvioSolicitudCedente().getNumeracion()); 
					         }
				
				
				break;
			case OCC:
				          TipoObjecionConcesionarioCedente msgOCC = cuerpo.getConsultaPreviaObjecionConcesionarioCedente();
	                      mensajeController.enviarRABDCP//(idSolicitud, destinatario, numeracion, causaRechazo, monto, moneda, fechaActivacion, fechaTerminoContratoEquipo, fechaVencimiento);
	                      ( cabecera.getIdentificadorProceso(), "46",
	                    		  msgOCC.getNumeracion(), msgOCC.getCausaObjecion(),
	                    		  msgOCC.getMonto(), msgOCC.getMoneda(), msgOCC.getFechaActivacion(), 
	                    		  msgOCC.getFechaTerminoContratoEquipo(), msgOCC.getFechaVencimiento());
				
				
				
				break;
			case SAC:  //  se envia solicitud rpcedene para tosods para el emisor y e receptor				          
						   MensajeAbdcp ansMsg = mensajeDao.getMensajeAbdcp(cabecera.getIdentificadorProceso(), SolicitudPortabilidad.ANS.getValue());
						   String cedente=cabecera.getRemitente();
						   String receptor= ansMsg.getDestino();
						   String fechaLimiteProgamacion =horarioController.getFechaLimiteProgamcionPortabilidad(cuerpo.getSolicitudPortabilidad().getTipoServicio(), LocalDateTime.now());
	    			       String fechaLiminteEjecucion=horarioController.getFechaLimiteEjecucionPortabilidad(cuerpo.getSolicitudPortabilidad().getCliente(), LocalDateTime.now());
						   mensajeController.enviarSPR(cabecera.getIdentificadorProceso(), cedente, null, null, fechaLimiteProgamacion, fechaLiminteEjecucion);
						   mensajeController.enviarSPR(cabecera.getIdentificadorProceso(), receptor, null, null, fechaLimiteProgamacion, fechaLiminteEjecucion);
						   
				
				
				break;
			case APD:      MensajeAbdcp spMsg = mensajeDao.getMensajeAbdcp(cabecera.getIdentificadorProceso(), SolicitudPortabilidad.SP.getValue());
                		   Document mensajeSp = spMsg.getRequest();
                		   MensajeABDCP mesageAbdcp = utilitario.converXmlToObject(MensajeABDCP.class, utilitario.converDocumentToString(mensajeSp));
                		   TipoSolicitudPortabilidad solictud = mesageAbdcp.getCuerpoMensaje().getSolicitudPortabilidad();
                		   String receptorSP=solictud.getCodigoReceptor();
                		   String cedenteSP=solictud.getCodigoCedente();                		   
				           mensajeController.enviarAPDC(cedenteSP, mensaje, archivo);
				           
				           String fechaLimiteProgamacionAPD =horarioController.getFechaLimiteProgamcionPortabilidad(cuerpo.getSolicitudPortabilidad().getTipoServicio(), LocalDateTime.now());
	    			       String fechaLiminteEjecucionAPD=horarioController.getFechaLimiteEjecucionPortabilidad(cuerpo.getSolicitudPortabilidad().getCliente(), LocalDateTime.now());
						   mensajeController.enviarSPR(cabecera.getIdentificadorProceso(), cedenteSP, null, null, fechaLimiteProgamacionAPD, fechaLiminteEjecucionAPD);
						   mensajeController.enviarSPR(cabecera.getIdentificadorProceso(), receptorSP, null, null, fechaLimiteProgamacionAPD, fechaLiminteEjecucionAPD);
					
				           
				             
				break;
			case APDC: 
				break;
			case RABDCP:  
				        // en el caso de se por deuda se registra la acreditacion de deuda ...
				         
				         TipoRechazadaABDCP rechazo = cuerpo.getRechazadaABDCP();
				         if(rechazo.getCausaRechazo().equals("REC01PRT09")){				        	 
				            /* rechazo.getFechaActivacion();
					         rechazo.getFechaTerminoContratoEquipo();
					         rechazo.getFechaVencimiento();*/					     					         
					         AcreditacionPago  acreditacion= new AcreditacionPago();
					         acreditacion.setIdProceso( rechazo.getIdentificacionSolicitud());
					         acreditacion.setMoneda(rechazo.getMoneda());
					         acreditacion.setNumero( rechazo.getNumeracion());
					         acreditacion.setMonto(Double.valueOf(rechazo.getMonto()));
					         mensajeDao.create(acreditacion);
				         }
				
				
				break;
			case SPR:  //  registar la programcion de portabilidad...  em el receptor		
				TipoSolicitudProcedente solicitud = cuerpo.getSolicitudProcedente();
				ProgramacionPortabilidad programacion=new ProgramacionPortabilidad();
				
				
				programacion.setFechaLimiteEnvio(fechaUtil.parseStringToLocalDateTime(solicitud.getFechaLimiteProgramacionPortabilidad(), "yyyyMMddHHmmss"));
				programacion.setFechaLimiteEjecucion(fechaUtil.parseStringToLocalDateTime(solicitud.getFechaLimiteEjecucionPortabilidad(), "yyyyMMddHHmmss"));
				 mensajeDao.create(programacion);
				
				
				break;
				
				
				
				
			case CPSPR: //  registar la programcion de portabilidad... en el receptor
				TipoSolicitudProcedente solicitud1 = cuerpo.getSolicitudProcedenteConsultaPreviaProcedente();
				
				ProgramacionPortabilidad programacion1=new ProgramacionPortabilidad();				
				programacion1.setFechaLimiteEnvio(fechaUtil.parseStringToLocalDateTime(solicitud1.getFechaLimiteProgramacionPortabilidad(), "yyyyMMddHHmmss"));
				programacion1.setFechaLimiteEjecucion(fechaUtil.parseStringToLocalDateTime(solicitud1.getFechaLimiteEjecucionPortabilidad(), "yyyyMMddHHmmss"));

				 mensajeDao.create(programacion1);
				        
				break;
				
			case CNPF:  //el abdcp en via este mesnaje cunado se a superado el tiempo para que el receptor envie su progamacion de portabilidad
				break;
				
				
			case PP: //  aca hay dos posibilidades que se envie un mesaje FLEP o que se envie un mensaje  PEP 
				        if(cabecera.getDestinatario().equals("00")){
				           
				        	 MensajeAbdcp ppSPMsg = mensajeDao.getMensajeAbdcp(cabecera.getIdentificadorProceso(), SolicitudPortabilidad.SP.getValue());
				        	   MensajeABDCP mesageSPAbdcp = utilitario.converXmlToObject(MensajeABDCP.class, utilitario.converDocumentToString(ppSPMsg.getRequest()));
	                		   TipoSolicitudPortabilidad solictudP = mesageSPAbdcp.getCuerpoMensaje().getSolicitudPortabilidad();
	                		   String receptorppSP=solictudP.getCodigoReceptor();
	                		   String cedenteppSP=solictudP.getCodigoCedente();                		   
					     
				        	
				        	MensajeAbdcp ppMsg = mensajeDao.getMensajeAbdcp(cabecera.getIdentificadorProceso(), Arrays.asList(SolicitudPortabilidad.SPR.getValue(),SolicitudPortabilidad.CPSPR.getValue()));
				            Document mensajePp = ppMsg.getRequest();
	                	    MensajeABDCP mesagePPAbdcp = utilitario.converXmlToObject(MensajeABDCP.class, utilitario.converDocumentToString(mensajePp));
	                	    
	                	    String fechaLiminteProgramacionSP=fechaUtil.parseDateTimeToString(LocalDateTime.now(),"yyyyMMddHHmmss");
	                	    String fechaLiminteEjecucionSP=fechaUtil.parseDateTimeToString(LocalDateTime.now(),"yyyyMMddHHmmss");
	                	    
	                	    if(ppMsg.getCodigoMensaje().equals(SolicitudPortabilidad.SPR)){
	                	    	fechaLiminteEjecucionSP=mesagePPAbdcp.getCuerpoMensaje().getSolicitudProcedente().getFechaLimiteEjecucionPortabilidad();	
	                	    	fechaLiminteProgramacionSP=mesagePPAbdcp.getCuerpoMensaje().getSolicitudProcedente().getFechaLimiteProgramacionPortabilidad();
	                	    }
	                	    if(ppMsg.getCodigoMensaje().equals(SolicitudPortabilidad.CPSPR)){
	                	    	fechaLiminteEjecucionSP= mesagePPAbdcp.getCuerpoMensaje().getSolicitudProcedenteConsultaPreviaProcedente().getFechaLimiteEjecucionPortabilidad();
	                	    	fechaLiminteProgramacionSP=mesagePPAbdcp.getCuerpoMensaje().getSolicitudProcedenteConsultaPreviaProcedente().getFechaLimiteProgramacionPortabilidad();
	                	    }	                	    
	                	   
	                	    
	                	   //if( fechaUtil.parseStringToLocalDateTime(fechaLiminteProgramacionSP, "yyyyMMddHHmmss"))
	                	    if(ChronoUnit.SECONDS.between(fechaUtil.parseStringToLocalDateTime(fechaLiminteProgramacionSP, "yyyyMMddHHmmss"), LocalDateTime.now())>0L){
	                	    	String fechaLiminteEjecucionPP=cuerpo.getProgramacionPortabilidad().getFechaEjecucionPortabilidad();	                	   
		                	    mensajeController.enviarPEP(cabecera.getIdentificadorProceso(), receptorppSP, fechaLiminteEjecucionPP);
		                	    mensajeController.enviarPEP(cabecera.getIdentificadorProceso(), cedenteppSP , fechaLiminteEjecucionPP);
		                	    	
	                	    }else{
	                	    	mensajeController.enviarFLEP(cabecera.getIdentificadorProceso(), receptorppSP, fechaLiminteProgramacionSP, fechaLiminteEjecucionSP);	
	                	    }
				        }
				break;
			case FLEP:  //  este mensaje se da c undo se a recibido una programcion de portabilidad fuera de limite 
				
				break;
			case PEP:   //  este mensaje se da cundo recibe la programcion de portabilidad ...
				break;
		
		
		}
		
		
		
		
	}
	
private void envioCedente(TipoCabeceraMensaje cabecera,TipoCuerpoMensaje cuerpo,Cliente cliente) throws Exception{
		
		if(cliente.getEstadoServicio().equals("02")) {
			/*
			String cedente			
			,String idSolicitd 
			,String causaObjecion
			,String fechaActivacion
			,String fechaTerminoContratoEquipo
			,String fechaVencimiento
			,String moneda
			,String monto
			,String numeracion
			*/
			
			mensajeController.enviarOCC(cabecera.getDestinatario(), cabecera.getIdentificadorProceso()
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
			mensajeController.enviarOCC(cabecera.getDestinatario(), cabecera.getIdentificadorProceso()
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
			mensajeController.enviarOCC(cabecera.getDestinatario(), cabecera.getIdentificadorProceso()
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
			mensajeController.enviarOCC(cabecera.getDestinatario()
					, cabecera.getIdentificadorProceso()
					, "REC01PRT09"
					, fechaUtil.parseDateToString(cliente.getFechaActivacion(), "yyyyMMdd")
					,fechaUtil.parseDateToString(cliente.getFechaTerminoContratoEquipo(), "yyyyMMdd") 
					, fechaUtil.parseDateToString(cliente.getFechaVencimientoUltimaFactura(), "yyyyMMdd") 
					, cliente.getMoneda()
					, cliente.getMontoDeuda().toString()
					, cuerpo.getEnvioSolicitudCedente().getNumeracion());
			
			return ;
		}
		
		Long dias = ChronoUnit.DAYS.between(cliente.getFechaActivacion(), LocalDate.now());
		if(dias <=30 ) {
			mensajeController.enviarOCC(cabecera.getDestinatario(), cabecera.getIdentificadorProceso()
					, "REC01PRT10"
					, null
					, null 
					, null
					, null
					, null
					, cuerpo.getEnvioSolicitudCedente().getNumeracion());
			
			return ;
			
			
		}


		String fechaLimiteProgamacion =horarioController.getFechaLimiteProgamcionPortabilidad(cuerpo.getSolicitudPortabilidad().getTipoServicio(), LocalDateTime.now());
  	    String fechaLiminteEjecucion=horarioController.getFechaLimiteEjecucionPortabilidad(cuerpo.getSolicitudPortabilidad().getCliente(), LocalDateTime.now());
		mensajeController.enviarSPR(
				cabecera.getIdentificadorProceso(),
				cabecera.getDestinatario(),
				fechaUtil.parseDateToString(cliente.getFechaActivacion(), "yyyyMMdd"),
				fechaUtil.parseDateToString(cliente.getFechaTerminoContratoEquipo(), "yyyyMMdd"),
				fechaLimiteProgamacion,
				fechaLiminteEjecucion
				
				);
		
	}
	
	
}
