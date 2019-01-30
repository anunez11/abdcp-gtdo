package pe.gtdo.dao;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import pe.gtdo.entity.MensageAbdcp;
import pe.gtdo.entity.MensajeAbdcp;
import pe.gtdo.tipo.MensajeABDCP;
import pe.gtdo.tipo.TipoCabeceraMensaje;
import pe.gtdo.tipo.TipoCuerpoMensaje;
import pe.gtdo.util.constante.ConsultaPrevia;
import pe.gtdo.util.constante.NotificacionError;
import pe.gtdo.util.constante.Proceso;
import pe.gtdo.util.constante.Retorno;
import pe.gtdo.util.constante.SolicitudPortabilidad;

@ApplicationScoped
public class MensajeDao extends TransactionDao {
   
	
	public String generarCodigo(String concesionario,String tipoProceso){
		
		// 00 IDmENSAGE
		// 
		
		Map<String,Object> parameters=new HashMap<String,Object>();
		parameters.put("concesionario",concesionario);
		parameters.put("tipoProceso",tipoProceso);		
		List<String> lista = crudService.findWithNativeQuery("select abdcp_stored_codigo(:concesionario,:tipoProceso);", parameters);
		return lista.get(0);
	}
	
	
	public void guardarMensaje(MensajeABDCP mensaje,String msg,String direccion) throws Exception{
		
		   TipoCabeceraMensaje cabecera = mensaje.getCabeceraMensaje();
		   TipoCuerpoMensaje cuerpo = mensaje.getCuerpoMensaje();
		   String idSolicitud=cabecera.getIdentificadorProceso();
		   String proceso=idSolicitud.substring(8, 10);
		   String numero=null;
		   MensajeAbdcp msgAbdcp =null;
		   switch(Proceso.fromType(proceso)){
		   
		   			case CP:if(ConsultaPrevia.ANCP.getValue().equals(cuerpo.getIdMensaje())){
						 			   idSolicitud= cuerpo.getAsignacionNumeroConsultaPrevia().getIdentificacionSolicitud();
									   numero=cuerpo.getAsignacionNumeroConsultaPrevia().getNumeracion();					   
							}else{
									   msgAbdcp = getMensajeAbdcp(idSolicitud,ConsultaPrevia.ANCP.getValue());
									   if(msgAbdcp!=null) numero=msgAbdcp.getNumero();
							}
		   			break;
		   			case RP:   msgAbdcp = getMensajeAbdcp(idSolicitud,Retorno.SR.getValue());
		   			          if(msgAbdcp!=null) numero=msgAbdcp.getNumero();
		   			break;	   			
		   			
		   			case SP : if(SolicitudPortabilidad.ANS.getValue().equals(cuerpo.getIdMensaje())){
			 			   			idSolicitud= cuerpo.getAsignacionNumeroSolicitud().getIdentificacionSolicitud();
			 			   			numero=cuerpo.getAsignacionNumeroSolicitud().getNumeracion();					   
				               }else{
					                msgAbdcp = getMensajeAbdcp(idSolicitud,SolicitudPortabilidad.ANS.getValue());
						            if(msgAbdcp!=null) numero=msgAbdcp.getNumero();
				                }     
		   			break;	
		   
		   }
		   
		   MensajeAbdcp data= new MensajeAbdcp();
		   data.setDireccionMensaje(direccion);
		   data.setCodigoMensaje(cuerpo.getIdMensaje());
		   data.setDestino(cabecera.getDestinatario());
		   data.setEmisor(cabecera.getRemitente());
		   data.setIdMensaje(cabecera.getIdentificadorMensaje());
		   data.setIdProceso(cabecera.getIdentificadorProceso());
		   data.setIdSolicitud(idSolicitud);
		   data.setNumero(numero);		   DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		   Document document = db.parse(new ByteArrayInputStream(msg.getBytes("UTF-8")));		   
		   data.setRequest(document);
		   create(data);
		
		
	}
	
	
	public MensajeAbdcp getMensajeAbdcp(String idSolicitud,String tipo){
		Map<String,Object> parameters=new HashMap<String,Object>();
		parameters.put("idSolicitud",idSolicitud);
		parameters.put("codigoMensaje",tipo);		
		List<MensajeAbdcp> lista = crudService.findWithQuery("select u from MensajeAbdcp u  where u.idSolicitud=:idSolicitud and u.codigoMensaje=:codigoMensaje ", parameters);
		if(lista.size()>0) return lista.get(0);
		return null;
	}
}
