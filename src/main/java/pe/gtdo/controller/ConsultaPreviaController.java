package pe.gtdo.controller;

import java.util.List;

import javax.ejb.ApplicationException;
import javax.faces.bean.ApplicationScoped;

import pe.gtdo.tipo.MensajeABDCP;
import pe.gtdo.tipo.TipoCabeceraMensaje;
import pe.gtdo.tipo.TipoCuerpoMensaje;
import pe.gtdo.tipo.TipoRangoNumeracion;
import pe.gtdo.util.constante.ConsultaPrevia;

@ApplicationScoped
public class ConsultaPreviaController {

	private String proceso="05";
	
	public void ejecutarProceso(MensajeABDCP mensaje){
		TipoCuerpoMensaje cuerpo = mensaje.getCuerpoMensaje();	
		TipoCabeceraMensaje cabecera = mensaje.getCabeceraMensaje();
		switch(ConsultaPrevia.fromType(cuerpo.getIdMensaje())){
		   
		    
		    case CP ://  se recibe el mensaje se hace la validacion  el adbcp
		    	      List<TipoRangoNumeracion> rangos = cuerpo.getConsultaPrevia().getNumeracionSolicitada().getRangoNumeracion();
		    	      for(TipoRangoNumeracion rango:rangos){
		    	    	  String destino = cabecera.getDestinatario(); 
		    	    	  
		    	    	  
		    	      }
		    	     
		    	 
		    	
		    	
		    	
		    break;	   

                          
		    
		    
		
        	case NONE:
			break;
		    	
		
		
		}
		
		
	}
	
	
	
	
	
}
