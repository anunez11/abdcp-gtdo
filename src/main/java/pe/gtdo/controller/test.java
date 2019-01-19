package pe.gtdo.controller;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import pe.gtdo.cliente.ClienteSoap;
import pe.gtdo.exception.AbdcpException;
import pe.gtdo.msg.MensajeABDCP;
import pe.gtdo.msg.TipoCabeceraMensaje;
import pe.gtdo.msg.TipoCuerpoMensaje;
import pe.gtdo.msg.TipoSolicitudPortabilidad;
import pe.gtdo.soap.ReceiveMessageResponse;

public class test {

	


	public static void main(String[] args) throws AbdcpException, JAXBException {
		// TODO Auto-generated method stub
		
		ClienteSoap soap= new ClienteSoap();
		
		soap.setConfig("http://localhost:8080/Portaflow/services/ABDCPWebService?wsdl", "http://ws.inpac.telcordia.com","ABDCPWebService", "http://localhost:8080/Portaflow/services/ABDCPWebService");
		ReceiveMessageResponse respuesta = soap.enviarMensaje(null, "", "", getXmlString( genenerarMensage()));
		
		System.out.println("========================================================>");
		System.out.println(respuesta.getResponse());
		System.out.println("========================================================>");
		
		
		
		

	}
	
	
	
	public static MensajeABDCP genenerarMensage(){
		MensajeABDCP mensajeABDCP= new MensajeABDCP();		
		TipoCabeceraMensaje cabecera=new TipoCabeceraMensaje();
		TipoCuerpoMensaje cuerpo= new TipoCuerpoMensaje();
		cabecera.setRemitente("46");
		cabecera.setDestinatario("00");
		cabecera.setFechaCreacionMensaje("20190119172045");
		cabecera.setIdentificadorProceso("46190119050000008");
		cabecera.setIdentificadorMensaje("46201901190000001");
		mensajeABDCP.setCabeceraMensaje(cabecera);	
		
		TipoSolicitudPortabilidad consultaPrevia=new TipoSolicitudPortabilidad();
		
		consultaPrevia.setCantidadNumeraciones("1");
		consultaPrevia.setCliente("1");
		consultaPrevia.setCodigoCedente("35");
		consultaPrevia.setCodigoReceptor("46");
		consultaPrevia.setDepartamentoSolicitud("");
		consultaPrevia.setEmailContacto("angel@gmail.com");
		consultaPrevia.setFaxContacto("abv123456789");
		consultaPrevia.setNombreContacto("Angel NUñez Berrospi");
		consultaPrevia.setNumeroConsultaPrevia("46201901190000001");
		consultaPrevia.setNumeroDocumentoIdentidad("42371327");
		consultaPrevia.setObservaciones("");
		consultaPrevia.setTelefonoContacto("954180317");
		consultaPrevia.setTipoDocumentoIdentidad("01");
		consultaPrevia.setTipoServicio("1");
		
		cuerpo.setIdMensaje("46201901190000001");
		cuerpo.setConsultaPrevia(consultaPrevia);
		
		mensajeABDCP.setCuerpoMensaje(cuerpo);
		
		return mensajeABDCP;
	}
	
	
	public static String getXmlString(MensajeABDCP c) throws JAXBException{
		
	      JAXBContext jaxbContext = JAXBContext.newInstance(MensajeABDCP.class);
	        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        StringWriter sw = new StringWriter();
	        jaxbMarshaller.marshal(c, sw);
	        System.out.println(sw.toString());
	        return  sw.toString();
	        
	}
	
	
	

}
