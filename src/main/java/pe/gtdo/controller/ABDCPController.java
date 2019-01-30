package pe.gtdo.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import pe.gtdo.core.logging.AbdcpLogger;
import pe.gtdo.dao.MensajeDao;
import pe.gtdo.dao.MensajeErrorDao;
import pe.gtdo.tipo.MensajeABDCP;
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
	
	@Inject
	MensajeDao mensajeDao;
	
	@Inject
	ConsultaPreviaController consultaPreviaController;
	 
	@Inject 
	NotificacionErrorController notificacionErrorController;
	
	@Inject
	RetornoPortabilidadController retornoPortabilidadController;
	
	
	//private Boolean isMediador=false;
	//private Integer consecionario=46;
	
	public String getResultado(String userID, String password, String xmlMsg,
			byte[] attachedDoc) {
				
		// validomos que el usuario exiws
		// validamos que el paswor existe
		
		
		try {			
			    MensajeABDCP mensaje = utilitario.converXmlToObject(MensajeABDCP.class, xmlMsg);
			    
			    
			    mensajeDao.guardarMensaje(mensaje,xmlMsg ,"IN");			    
			    if(!utilitario.validarMsg(xmlMsg)) return msgError.getError("ERRSOAP012");		
				consultaPreviaController.ejecutarProceso(mensaje);
				notificacionErrorController.ejecutarProceso(mensaje);
				retornoPortabilidadController.ejecutarProceso(mensaje);
			//	TimeUnit.SECONDS.sleep(10);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "ack";
		
	}
	
	
	
	
	
}
