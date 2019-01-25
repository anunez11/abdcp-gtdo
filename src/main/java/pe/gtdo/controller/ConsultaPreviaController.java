package pe.gtdo.controller;

import java.util.List;

import javax.ejb.ApplicationException;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;

import pe.gtdo.dao.ClienteDao;
import pe.gtdo.dao.MensajeRechazoDao;
import pe.gtdo.entity.Cliente;
import pe.gtdo.entity.MensajeRechazo;
import pe.gtdo.tipo.MensajeABDCP;
import pe.gtdo.tipo.TipoCabeceraMensaje;
import pe.gtdo.tipo.TipoCuerpoMensaje;
import pe.gtdo.tipo.TipoRangoNumeracion;
import pe.gtdo.util.constante.ConsultaPrevia;
import pe.gtdo.util.constante.Proceso;

@ApplicationScoped
public class ConsultaPreviaController {

	private String proceso="05";
	private String abdcp="00";
	
	
	@Inject
	ClienteDao clienteDao;
	
	@Inject
	MensajeRechazoDao mensajeRechazoDao; 
	
	@Inject
	MensajeController mensajeController;
	
	public void ejecutarProceso(MensajeABDCP mensaje){
		TipoCuerpoMensaje cuerpo = mensaje.getCuerpoMensaje();	
		TipoCabeceraMensaje cabecera = mensaje.getCabeceraMensaje();
		switch(ConsultaPrevia.fromType(cuerpo.getIdMensaje())){
		   
		    
		    case CP : //  se recibe el mensaje se hace la validacion  el adbcp
		    	      List<TipoRangoNumeracion> rangos = cuerpo.getConsultaPrevia().getNumeracionSolicitada().getRangoNumeracion();
		    	      for(TipoRangoNumeracion rango:rangos){
		    	    	  String destino = cabecera.getDestinatario();
		    	    	  String emisor = cabecera.getRemitente();
   		    	          //
		    	    	  MensajeRechazo rechazo = mensajeRechazoDao.getMensajeRechazoAbdcp(cuerpo.getIdMensaje(), Proceso.CP.getValue());
		    	      }
		    break;		
        	case NONE:
			break;
			
		}
		
		
	}
	
	
	
	
	
	
	
	
}
