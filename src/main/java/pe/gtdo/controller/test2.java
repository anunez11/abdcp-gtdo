package pe.gtdo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import pe.gtdo.cliente.ClienteSoap;
import pe.gtdo.exception.AbdcpException;
import pe.gtdo.msg.Builder;
import pe.gtdo.soap.ReceiveMessageResponse;
import pe.gtdo.tipo.MensajeABDCP;
import pe.gtdo.tipo.TipoAcreditacionPagoDeuda;
import pe.gtdo.tipo.TipoCabeceraMensaje;
import pe.gtdo.tipo.TipoCuerpoMensaje;
import pe.gtdo.tipo.TipoListaRangosNumeracion;
import pe.gtdo.tipo.TipoRangoNumeracion;
import pe.gtdo.tipo.TipoSolicitudPortabilidad;
import pe.gtdo.tipo.TipoSolicitudRetorno;

public class test2 {

	


	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String data="46190119050000008";
		
		System.out.println("========================================================>");
		System.out.println(data.substring(8, 10));
		System.out.println("========================================================>");
		ClienteSoap soap= new ClienteSoap();		
		//soap.setConfig("http://10.117.1.46/XCEWS/services/abdcp?wsdl", "http://ws.inpac.telcordia.com","ABDCPWebService", "http://10.117.1.46/XCEWS/services/abdcp");


		soap.setConfig("http://localhost:8080/Wigo/services/ABDCPWebService?wsdl", "http://ws.inpac.telcordia.com","ABDCPWebService", "http://localhost:8080/Wigo/services/ABDCPWebService");
			
		
		
		ReceiveMessageResponse respuesta = soap.enviarMensaje(null, "dXNlcjQ2VGVzdGluZw==", "user46", getXmlStringAPD()	 );
		
		System.out.println("========================================================>");
		System.out.println(respuesta.getResponse());
		System.out.println("========================================================>");
	
	}
	
	
	public static String getXmlStringRetorno() throws JAXBException{
		
		TipoSolicitudRetorno solicitudRetorno=new TipoSolicitudRetorno();
		solicitudRetorno.setCodigoCedente("35");
		solicitudRetorno.setCodigoReceptor("46");
		
		solicitudRetorno.setEmailContacto("angel@gmail.com");
		solicitudRetorno.setFaxContacto("abv123456789");
		solicitudRetorno.setNombreContacto("Angel NUñez Berrospi");
		solicitudRetorno.setFechaEjecucionRetorno("20190131151020");
		solicitudRetorno.setMotivoRetorno("03");
		solicitudRetorno.setNumeracionARetornar("954180340");
		solicitudRetorno.setObservaciones(" PRUEBA IZ -SOAP- 001 ");
		solicitudRetorno.setTelefonoContacto("954180317");
		
		Builder builder= new Builder();
		builder.setCodigoMsg("SR");
		builder.setCabeceraDestinatario("00");
		builder.setCabeceraFechaCreacionMensaje("20190129172045");
		builder.setCabeceraIdentificadorMensaje("46201901290000001");
		builder.setCabeceraIdentificadorProceso("46190129020000008");
		builder.setCabeceraRemitente("46");
		builder.setSolicitudRetorno(solicitudRetorno);
		
		
		
       
         return  getXmlString( builder.build()) ;    
		
		
		
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
		
		List<TipoRangoNumeracion> numeracionSolicitada=new ArrayList<TipoRangoNumeracion>();
		TipoRangoNumeracion rango=new TipoRangoNumeracion();
		rango.setInicioRango("954180317");
		rango.setTipoPortabilidadCedente("01");
		
		numeracionSolicitada.add(rango);
	//	numeracionSolicitada.add(rango);
		
		
		TipoListaRangosNumeracion lista= new TipoListaRangosNumeracion();
		lista.setRangoNumeracion(numeracionSolicitada);
		
		consultaPrevia.setCantidadNumeraciones("1");
		consultaPrevia.setCliente("1");
		consultaPrevia.setCodigoCedente("35");
		consultaPrevia.setCodigoReceptor("46");
		consultaPrevia.setDepartamentoSolicitud("05");
		consultaPrevia.setEmailContacto("angel@gmail.com");
		consultaPrevia.setFaxContacto("abv123456789");
		consultaPrevia.setNombreContacto("Angel NUñez Berrospi");
		consultaPrevia.setNumeroConsultaPrevia("46201901190000001");
		consultaPrevia.setNumeroDocumentoIdentidad("42371327");
		consultaPrevia.setObservaciones("");
		consultaPrevia.setTelefonoContacto("954180317");
		consultaPrevia.setTipoDocumentoIdentidad("01");
		consultaPrevia.setTipoServicio("1");
		consultaPrevia.setNumeracionSolicitada(lista);
		cuerpo.setIdMensaje("CP");
		cuerpo.setConsultaPrevia(consultaPrevia);		
		mensajeABDCP.setCuerpoMensaje(cuerpo);		
		return mensajeABDCP;
		
		
	}
	
	
	public static String getXmlStringAPD() throws Exception{
		
		
		TipoAcreditacionPagoDeuda deuda= new TipoAcreditacionPagoDeuda();
		deuda.setDocumentoAdjunto("46201906020000009.jpg");
		deuda.setEntidadPago("scotiabank");
		
		//deuda.setFechaPago("20190115153010");
		deuda.setFechaPago("20190602110800");
		deuda.setMoneda("01");
		deuda.setMonto("100");
		deuda.setNumeracion("17436925");
		deuda.setNumeroOperacionPago("123456789101");
		
		Builder builder= new Builder();
		builder.setCodigoMsg("APD");
		builder.setCabeceraDestinatario("00");
		builder.setCabeceraFechaCreacionMensaje("20190602111320");
		builder.setCabeceraIdentificadorMensaje("46201906020000009");
		builder.setCabeceraIdentificadorProceso("46190602010000016");
		builder.setCabeceraRemitente("46");
		builder.setAcreditacionPagoDeuda(deuda);
       
    return  getXmlString( builder.build()) ;    
		
	}
	
	public static String getXmlStringCP() throws JAXBException{
		
		
         TipoSolicitudPortabilidad consultaPrevia=new TipoSolicitudPortabilidad();
		
		List<TipoRangoNumeracion> numeracionSolicitada=new ArrayList<TipoRangoNumeracion>();
		TipoRangoNumeracion rango=new TipoRangoNumeracion();
		rango.setInicioRango("17436300");
		rango.setTipoPortabilidadCedente("02");
		
		numeracionSolicitada.add(rango);

		
		TipoListaRangosNumeracion lista= new TipoListaRangosNumeracion();
		lista.setRangoNumeracion(numeracionSolicitada);
		
		consultaPrevia.setCantidadNumeraciones("1");
		consultaPrevia.setCliente("2");
		consultaPrevia.setCodigoCedente("19");
		consultaPrevia.setCodigoReceptor("46");
		consultaPrevia.setDepartamentoSolicitud("1");
		consultaPrevia.setEmailContacto("angelanb11@gmail.com");
		//consultaPrevia.setFaxContacto("abv123456789");
		consultaPrevia.setNombreContacto("ANGEL NUÑEZ  BERROSPI");
		//consultaPrevia.setNumeroConsultaPrevia("46201901190000001");
		consultaPrevia.setNumeroDocumentoIdentidad("42371327");
		consultaPrevia.setObservaciones("PRUEBA 1");
		consultaPrevia.setTelefonoContacto("954180317");
		consultaPrevia.setTipoDocumentoIdentidad("01");
		consultaPrevia.setTipoServicio("1");
		consultaPrevia.setNumeracionSolicitada(lista);
		
		
		Builder builder= new Builder();
		builder.setCodigoMsg("CP");
		builder.setCabeceraDestinatario("00");
		builder.setCabeceraFechaCreacionMensaje("20190219205540");
		builder.setCabeceraIdentificadorMensaje("46190219050000009");
		builder.setCabeceraIdentificadorProceso("46190219050000009");
		builder.setCabeceraRemitente("46");
		builder.setConsultaPrevia(consultaPrevia);
       
    return  getXmlString( builder.build()) ;    
    }
	
	
   public static byte[] getArchivo() throws IOException{
	   
	
	   
	   File file = new File("C:\\Users\\Angel A\\Documents\\ANGEL\\DOC\\pdf2.pdf");
	   //init array with file length
	   byte[] bytesArray = new byte[(int) file.length()]; 

	   FileInputStream fis = new FileInputStream(file);
	   fis.read(bytesArray); //read file into bytes[]
	   fis.close();
	 			
	   return bytesArray;
	   
   }
 public static byte[] getArchivoCero() throws IOException{
	   
	
	   
	  // File file = new File("C:\\Users\\Angel A\\Documents\\ANGEL\\DOC\\pdf.pdf");
	   //init array with file length
	   byte[] bytesArray = new byte[1]; 

	  // FileInputStream fis = new FileInputStream(file);
	 //  fis.read(bytesArray); //read file into bytes[]
	 //  fis.close();
	 			
	   return bytesArray;
	   
   }
	
	
	
	
	public static String getXmlString(MensajeABDCP c) throws JAXBException{
		
	        JAXBContext jaxbContext = JAXBContext.newInstance(MensajeABDCP.class);
	        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	        jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
	        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        StringWriter sw = new StringWriter();
	        jaxbMarshaller.marshal(c, sw);
	        System.out.println(sw.toString());
	        return  sw.toString();
	        
	}
	
	
	
	
	
	

}
