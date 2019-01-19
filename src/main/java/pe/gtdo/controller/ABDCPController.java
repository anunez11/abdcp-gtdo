package pe.gtdo.controller;


import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import pe.gtdo.core.logging.AbdcpLogger;
import pe.gtdo.dao.MensajeErrorDao;
import pe.gtdo.util.Utilitario;

//import pe.gtdo.logging.AbdcpLogger;
//import utilitario.Utilitario;


@ApplicationScoped
public class ABDCPController {
  
	
	@Inject
	Utilitario utilitario;
	   
	@Inject
	AbdcpLogger LOG;
	
	@Inject
	MensajeErrorDao msgError;	
	
	private Boolean isMediador=false;
	private Integer consecionario=46;
	
	public String getResultado(String userID, String password, String xmlMsg,
			byte[] attachedDoc) {
		
		
		
		
		// validomos que el usuario exiws
		// validamos que el paswor existe
		
		// validamos qie el mesaje sea correcto ..	

		//xmlMsg="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+xmlMsg;
		if(!utilitario.validarMsg(xmlMsg)) return msgError.getError("ERRSOAP012");
		try {
			utilitario.stringToXml(xmlMsg);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "ack";
		
	}
	
	
	
	
	
}
