package pe.gtdo.controller;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import pe.gtdo.core.logging.AbdcpLogger;
import pe.gtdo.util.Utilitario;

//import pe.gtdo.logging.AbdcpLogger;
//import utilitario.Utilitario;


@ApplicationScoped
public class ABDCPController {
  
	
	@Inject
	Utilitario utilitario;
	   
	@Inject
	AbdcpLogger LOG;
	
	public String getResultado(String userID, String password, String xmlMsg,
			byte[] attachedDoc){
		
		// validomos que el usuario exiws
		// validamos que el paswor existe
		// validamos qie el mesaje sea correcto ..
		
	  
		utilitario.validarMsg(xmlMsg);	
		
		return "ack";
		
	}
	
	
}
