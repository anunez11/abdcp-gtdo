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
import pe.gtdo.tipo.MensajeABDCP;
import pe.gtdo.tipo.TipoCabeceraMensaje;
import pe.gtdo.tipo.TipoCuerpoMensaje;

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
		   
		   MensageAbdcp data= new MensageAbdcp();
		   data.setDireccionMensaje(direccion);
		   data.setCodigoMensage(cuerpo.getIdMensaje());
		   data.setDestino(cabecera.getDestinatario());
		   data.setEmisor(cabecera.getRemitente());
		   data.setIdMensaje(cabecera.getIdentificadorMensaje());
		   data.setIdProceso(cabecera.getIdentificadorProceso());
		   data.setIdSolicitud(cabecera.getIdentificadorProceso());
		   
		   
		   DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = db.parse(new ByteArrayInputStream(msg.getBytes("UTF-8")));		   
		   data.setRequest(document);
		   create(data);
		
		
	}
	
	
	
}
