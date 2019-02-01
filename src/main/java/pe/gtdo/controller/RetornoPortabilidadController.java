package pe.gtdo.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import pe.gtdo.dao.ClienteDao;
import pe.gtdo.dao.MensajeDao;
import pe.gtdo.dao.MensajeRechazoDao;
import pe.gtdo.entity.BlacklistAbdcp;
import pe.gtdo.tipo.MensajeABDCP;
import pe.gtdo.tipo.TipoCabeceraMensaje;
import pe.gtdo.tipo.TipoCuerpoMensaje;
import pe.gtdo.tipo.TipoSolicitudRetorno;
import pe.gtdo.util.constante.ConsultaPrevia;
import pe.gtdo.util.constante.Retorno;

@ApplicationScoped
public class RetornoPortabilidadController {

	@Inject
	ClienteDao clienteDao;
	
	@Inject 
	MensajeDao mensajeDao;
	
	@Inject
	MensajeRechazoDao mensajeRechazoDao; 
	
	@Inject
	MensajeController mensajeController;
	
	
	public void ejecutarProceso(byte[] archivo,MensajeABDCP mensaje) throws Exception{
		
		
		TipoCuerpoMensaje cuerpo = mensaje.getCuerpoMensaje();	
		TipoCabeceraMensaje cabecera = mensaje.getCabeceraMensaje();
		switch(Retorno.fromType(cuerpo.getIdMensaje())){
		
			case SR:  
				
				
					  TipoSolicitudRetorno solicitud = cuerpo.getSolicitudRetorno();
					  
					  if("00".equals(cabecera.getRemitente())) return ;
					  
				      BlacklistAbdcp rechazo = mensajeRechazoDao.getListanegra(null, Retorno.SR.getValue(), solicitud.getNumeracionARetornar());
				      // aca  hay dos posibilidades
				      if(rechazo!=null){
				    	  
				    	  // o en su defecto que se envie una  dene gacion de retorno 
				    	  mensajeController.enviarDR(mensaje, rechazo.getCodigoRechazo());
				    	  
				      }else{
				    	  //  que se envie un sr al cedente  y una aceptacion de retorono
				    	  
				    	  mensajeController.enviarAR(mensaje);
				    	  mensajeController.enviarSRC(mensaje);
				    	  
				    	  
				      }
				      
				      
				    
				    
				  
				
				
			break;
			
			case AR : // no se hace nada
			break;
				
			case DR: // nose ahce nada
			break;
		    
		
		}
		
		
	}
	
}
