package pe.gtdo.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import pe.gtdo.entity.MensajeRechazo;
import pe.gtdo.tipo.MensajeABDCP;
import pe.gtdo.tipo.TipoCabeceraMensaje;
import pe.gtdo.tipo.TipoCuerpoMensaje;
import pe.gtdo.util.constante.ConsultaPrevia;
import pe.gtdo.util.constante.NotificacionError;


@ApplicationScoped
public class NotificacionErrorController {

	@Inject
	MensajeController mensajeController;
	
	public void ejecutarProceso(MensajeABDCP mensaje){
		TipoCuerpoMensaje cuerpo = mensaje.getCuerpoMensaje();	
		TipoCabeceraMensaje cabecera = mensaje.getCabeceraMensaje();
		switch(NotificacionError.fromType(cuerpo.getIdMensaje())){
		    
		    case NI: // guaardmos el mensaj
			break;
			
		    case NE:  // huardamos el mensaje
				break;
			
		}
		
	}
   
	
	public void enviarMensaje(MensajeRechazo rechazo,String destinatario,MensajeABDCP mensaje) throws Exception{
		
		switch(NotificacionError.fromType(rechazo.getTipoMensaje())){
			    
			    case NI: mensajeController.enviarNI(destinatario, rechazo.getCodigo(), mensaje);
			    	  
				break;
				
			    case NE:  mensajeController.enviarNE(destinatario, rechazo.getCodigo(), rechazo.getDescripcion(), mensaje);
					break;
				
	}
		
	}
	
}
