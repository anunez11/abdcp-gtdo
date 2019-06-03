package pe.gtdo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Response.Status;
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
import pe.gtdo.tipo.TipoObjecionConcesionarioCedente;
import pe.gtdo.tipo.TipoProgramacionPortabilidad;
import pe.gtdo.tipo.TipoRangoNumeracion;
import pe.gtdo.tipo.TipoSolicitudAceptadaCedente;
import pe.gtdo.tipo.TipoSolicitudPortabilidad;
import pe.gtdo.tipo.TipoSolicitudRetorno;

public class test {

	private static DateTimeFormatter formatter;


	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String data="46190119050000008";
		
		System.out.println("========================================================>");
		System.out.println(data.substring(8, 10));
		System.out.println("========================================================>");
		ClienteSoap soap= new ClienteSoap();		
		soap.setConfig("http://10.117.1.46/XCEWS/services/abdcp?wsdl", "http://ws.inpac.telcordia.com","ABDCPWebService", "http://10.117.1.46/XCEWS/services/abdcp");


		//soap.setConfig("http://10.209.20.130:8080/Wigo/services/ABDCPWebService?wsdl", "http://ws.inpac.telcordia.com","ABDCPWebService", "http://10.209.20.130:8080/Wigo/services/ABDCPWebService");
		
		
		
		
		ReceiveMessageResponse respuesta = soap.enviarMensaje(getArchivo(), "dXNlcjQ2VGVzdGluZw==", "user46", getXmlStringAPD() );
	//ReceiveMessageResponse respuesta = soap.enviarMensaje(null, "dXNlcjQ2VGVzdGluZw==", "user46", getXmlStringCP() );
		
		System.out.println("========================================================>");
		System.out.println(respuesta.getResponse());
		System.out.println("========================================================>");
	
	}
	
	
	public static String getXmlStringRetornoSR() throws JAXBException{
		
		TipoSolicitudRetorno solicitudRetorno=new TipoSolicitudRetorno();
		solicitudRetorno.setCodigoCedente("19");
		solicitudRetorno.setCodigoReceptor("46");
		
		solicitudRetorno.setEmailContacto("angel@gmail.com");
		solicitudRetorno.setFaxContacto("abv123456789");
		solicitudRetorno.setNombreContacto("Angel NUñez Berrospi");
		solicitudRetorno.setFechaEjecucionRetorno("20190324045600");
		solicitudRetorno.setMotivoRetorno("03");
		solicitudRetorno.setNumeracionARetornar("17437607");
		solicitudRetorno.setObservaciones(" PRUEBA IZ -SOAP- 001 ");
		solicitudRetorno.setTelefonoContacto("954180317");
		
		Builder builder= new Builder();
		builder.setCodigoMsg("SR");
		builder.setCabeceraDestinatario("00");
		builder.setCabeceraFechaCreacionMensaje("20190318205600");
		builder.setCabeceraIdentificadorMensaje("46201903230000018");
		builder.setCabeceraIdentificadorProceso("46190323020000004");
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
		cabecera.setIdentificadorProceso("46190322050000001");
		cabecera.setIdentificadorMensaje("46201903220000001");
		mensajeABDCP.setCabeceraMensaje(cabecera);	
		
		TipoSolicitudPortabilidad consultaPrevia=new TipoSolicitudPortabilidad();
		
		List<TipoRangoNumeracion> numeracionSolicitada=new ArrayList<TipoRangoNumeracion>();
		TipoRangoNumeracion rango=new TipoRangoNumeracion();
		rango.setInicioRango("74854512");
		rango.setTipoPortabilidadCedente("02");
		
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
		consultaPrevia.setNumeroDocumentoIdentidad("74854512");
		consultaPrevia.setObservaciones("");
		consultaPrevia.setTelefonoContacto("954180317");
		consultaPrevia.setTipoDocumentoIdentidad("01");
		consultaPrevia.setTipoServicio("2");
		consultaPrevia.setNumeracionSolicitada(lista);
		cuerpo.setIdMensaje("CP");
		cuerpo.setConsultaPrevia(consultaPrevia);		
		mensajeABDCP.setCuerpoMensaje(cuerpo);		
		return mensajeABDCP;
		
		
	}
	
	public static String getXmlStringPP() throws Exception{
		
		
		TipoProgramacionPortabilidad programacion= new TipoProgramacionPortabilidad();
	//	16/03/2019 06:00:00
		programacion.setFechaEjecucionPortabilidad("20190314054000");
		
		Builder builder= new Builder();
		builder.setCodigoMsg("PP");
		builder.setCabeceraDestinatario("00");
		builder.setCabeceraFechaCreacionMensaje("20190313210318");
		builder.setCabeceraIdentificadorMensaje("46201903130000036");
		builder.setCabeceraIdentificadorProceso("46190312010000014");
		builder.setCabeceraRemitente("46");
		builder.setProgramacionPortabilidad(programacion);
       
    return  getXmlString( builder.build()) ;    
		
	}
	public static String getXmlStringAPD() throws Exception{
		
		// 46190313010000014  caso ●	FU -P01- 064 ●	FU -P01- 065
		TipoAcreditacionPagoDeuda deuda= new TipoAcreditacionPagoDeuda();
		deuda.setDocumentoAdjunto("462019060200009-P.jpg");
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
	
	
	public static String getXmlStringCPAC() throws Exception{
		
		
		TipoSolicitudAceptadaCedente CPAC= new TipoSolicitudAceptadaCedente();
		CPAC.setFechaActivacion(parseDateToString(LocalDate.now().plusDays(1),"yyyyMMdd"));
	
		//CPAC.setFechaActivacion("00000000");
		CPAC.setFechaTerminoContratoEquipo(parseDateToString(LocalDate.now().plusDays(365),"yyyyMMdd"));
		CPAC.setObservaciones("");
		
		Builder builder= new Builder();
		builder.setCodigoMsg("CPAC");
		builder.setCabeceraDestinatario("00");
		builder.setCabeceraRemitente("46");
		builder.setCabeceraFechaCreacionMensaje("20190316153607");
		builder.setCabeceraIdentificadorMensaje("46201903200000026");
		builder.setCabeceraIdentificadorProceso("46190320050000002");
		
		builder.setConsultaPreviaAceptadaCedente(CPAC);
       
    return  getXmlString( builder.build()) ;    
		
	}
	
   public static String getXmlStringSAC() throws Exception{
		
		
		TipoSolicitudAceptadaCedente SAC= new TipoSolicitudAceptadaCedente();
		SAC.setFechaActivacion(parseDateToString(LocalDate.now().plusDays(1),"yyyyMMdd"));
	///	SAC.setFechaActivacion("00000000");
		SAC.setFechaTerminoContratoEquipo(parseDateToString(LocalDate.now().plusDays(365),"yyyyMMdd"));
		SAC.setObservaciones("");
		
		Builder builder= new Builder();
		builder.setCodigoMsg("SAC");
		builder.setCabeceraDestinatario("00");
		builder.setCabeceraRemitente("46");
		builder.setCabeceraFechaCreacionMensaje("20190323074100");
		builder.setCabeceraIdentificadorMensaje("46201903230000013");
		builder.setCabeceraIdentificadorProceso("19190323010000008");
		
		builder.setSolicitudAceptadaCedente(SAC);
       
    return  getXmlString( builder.build()) ;    
		
	}
	
   public static String getXmlStringOCC() throws Exception{
		
		
		TipoObjecionConcesionarioCedente OOC= new TipoObjecionConcesionarioCedente();
		
		OOC.setCausaObjecion("REC01PRT10");
		//OOC.setMoneda("01");
		OOC.setNumeracion("17439060");
		//OOC.setMonto("100.00");
	  //  OOC.setFechaVencimiento(parseDateToString(LocalDate.now().minusDays(10),"yyyyMMdd"));
		OOC.setFechaActivacion(parseDateToString(LocalDate.now().plusDays(1),"yyyyMMdd"));
		//CPOC.setFechaActivacion("20190305");
		//OOC.setFechaTerminoContratoEquipo(parseDateToString(LocalDate.now().plusDays(365),"ddMMyyyy"));
		//CPAC.setObservaciones("");
		//17439499
		Builder builder= new Builder();
		builder.setCodigoMsg("OCC");
		builder.setCabeceraDestinatario("00");
		builder.setCabeceraRemitente("46");
		builder.setCabeceraFechaCreacionMensaje("20190322124100");
		builder.setCabeceraIdentificadorMensaje("46201903230000011");
		builder.setCabeceraIdentificadorProceso("19190323010000006");
		//19190315010000014
	
		builder.setObjecionConcesionarioCedente(OOC);
       
    return  getXmlString( builder.build()) ;    
		
	}
	
	
   public static String getXmlStringCPOCC() throws Exception{
		
		
		TipoObjecionConcesionarioCedente CPOC= new TipoObjecionConcesionarioCedente();
		
		CPOC.setCausaObjecion("REC00ABD02");
		//CPOC.setMoneda("01");
		CPOC.setNumeracion("17436445");
		//CPOC.setMonto("100.00");
		// CPOC.setFechaVencimiento(parseDateToString(LocalDate.now().minusDays(10),"yyyyMMdd"));
		// CPOC.setFechaActivacion(parseDateToString(LocalDate.now().plusDays(1),"yyyyMMdd"));
		CPOC.setFechaActivacion("20190305");
		//CPOC.setFechaTerminoContratoEquipo(parseDateToString(LocalDate.now().plusDays(365),"yyyyMMdd"));
		//CPAC.setObservaciones("");
		//17439499
		Builder builder= new Builder();
		builder.setCodigoMsg("CPOCC");
		builder.setCabeceraDestinatario("00");
		builder.setCabeceraRemitente("46");
		builder.setCabeceraFechaCreacionMensaje("20190306200815");
		builder.setCabeceraIdentificadorMensaje("46201903200000030");
		builder.setCabeceraIdentificadorProceso("46190320050000005");
	
		builder.setConsultaPreviaObjecionConcesionarioCedente(CPOC);
       
    return  getXmlString( builder.build()) ;    
		
	}
	

   public static String getXmlStringSP() throws JAXBException{
		
	   TipoSolicitudPortabilidad solicitudPortabilidad=new TipoSolicitudPortabilidad();		
		List<TipoRangoNumeracion> numeracionSolicitada=new ArrayList<TipoRangoNumeracion>();
		List<String> numero= Arrays.asList("17437765");
		for(int i=0;i<numero.size();i++){
			
		//	numero=numero+i;
			TipoRangoNumeracion rango=new TipoRangoNumeracion();
			rango.setInicioRango(numero.get(i));		     
			rango.setTipoPortabilidadCedente("02");
			rango.setTipoPortabilidadReceptor("02");
			numeracionSolicitada.add(rango);	
		}
		
		
		
		/*TipoRangoNumeracion rango1=new TipoRangoNumeracion();
		rango1.setInicioRango("17436920"); // NO PERTENEE AL CONECIONARIO
		rango1.setTipoPortabilidadCedente("02");		
		rango1.setTipoPortabilidadReceptor("02");
		numeracionSolicitada.add(rango1);*/
		
		TipoListaRangosNumeracion lista= new TipoListaRangosNumeracion();
		lista.setRangoNumeracion(numeracionSolicitada);
		
		solicitudPortabilidad.setCantidadNumeraciones(Integer.toString(numeracionSolicitada.size()));
		solicitudPortabilidad.setCliente("2");
		solicitudPortabilidad.setCodigoCedente("19");
		solicitudPortabilidad.setCodigoReceptor("46");
		solicitudPortabilidad.setDepartamentoSolicitud("1");
		solicitudPortabilidad.setEmailContacto("angelanb11@gmail.com");
		
		//solicitudPortabilidad.setFaxContacto("abv123456789");
		solicitudPortabilidad.setNombreContacto("ANGEL NUÑEZ  BERROSPI");
		//solicitudPortabilidad.setNumerosolicitudPortabilidad("46201901190000001");
		solicitudPortabilidad.setNumeroDocumentoIdentidad("42371327");
		solicitudPortabilidad.setObservaciones("PRUEBA 1");
		solicitudPortabilidad.setTelefonoContacto("954180317");
		solicitudPortabilidad.setTipoDocumentoIdentidad("01");
		solicitudPortabilidad.setTipoServicio("2");
		solicitudPortabilidad.setNumeracionSolicitada(lista);
	//	solicitudPortabilidad.setNumeroConsultaPrevia("46190320050000016");
		Builder builder= new Builder();
		builder.setCodigoMsg("SP");
		builder.setCabeceraDestinatario("00");
		builder.setCabeceraFechaCreacionMensaje("20190322211100");
		builder.setCabeceraIdentificadorMensaje("46201903230000006");
		builder.setCabeceraIdentificadorProceso("46190323010000002");
		builder.setCabeceraRemitente("46");
		builder.setSolicitudPortabilidad(solicitudPortabilidad);
        return  getXmlString( builder.build()) ; 
       
      
   }
   
   
	public static String getXmlStringCP() throws JAXBException{
		
		TipoSolicitudPortabilidad consultaPrevia=new TipoSolicitudPortabilidad();		
		List<TipoRangoNumeracion> numeracionSolicitada=new ArrayList<TipoRangoNumeracion>();
	
		List<String> numero= Arrays.asList("17436400","17436401","17436402");
		for(int i=0;i<numero.size();i++){
			TipoRangoNumeracion rango=new TipoRangoNumeracion();
		//	numero=numero+i;
			
			rango.setInicioRango(numero.get(i));		     
			rango.setTipoPortabilidadCedente("02");
		//	rango.setTipoPortabilidadReceptor("02");
			numeracionSolicitada.add(rango);	
		}
		
	
		
		TipoListaRangosNumeracion lista= new TipoListaRangosNumeracion();
		lista.setRangoNumeracion(numeracionSolicitada);
		
		consultaPrevia.setCantidadNumeraciones(String.valueOf(numeracionSolicitada.size()));
		consultaPrevia.setCliente("2");
		consultaPrevia.setCodigoCedente("19");
		consultaPrevia.setCodigoReceptor("46");
		//consultaPrevia.setDepartamentoSolicitud("1");
	//	consultaPrevia.setEmailContacto("angelanb11@gmail.com");
		//consultaPrevia.setFaxContacto("abv123456789");
	//	consultaPrevia.setNombreContacto("ANGEL NUÑEZ  BERROSPI");
		//consultaPrevia.setNumeroConsultaPrevia("46190312050000005");
		consultaPrevia.setNumeroDocumentoIdentidad("42371327");
		consultaPrevia.setObservaciones("");
		consultaPrevia.setTelefonoContacto("954183017");
		consultaPrevia.setTipoDocumentoIdentidad("01");
		consultaPrevia.setTipoServicio("2");
		consultaPrevia.setNumeracionSolicitada(lista);
		
		Builder builder= new Builder();
		builder.setCodigoMsg("CP");
		builder.setCabeceraDestinatario("00");
		builder.setCabeceraFechaCreacionMensaje("20190505174100");
		builder.setCabeceraIdentificadorMensaje("46201905050000007");
		builder.setCabeceraIdentificadorProceso("46190505050000007");
		builder.setCabeceraRemitente("46");
		builder.setConsultaPrevia(consultaPrevia);
		
        return  getXmlString( builder.build()) ; 
       
    }
	
	
   public static byte[] getArchivo() throws IOException{
	   
	
	   
	   File file = new File("C:\\Users\\Angel A\\Downloads\\factura01.jpg");
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
	
	
	
	public static String parseDateToString(LocalDate date,String formato) throws Exception{
		if(date==null) return null;
		try{
			formatter=DateTimeFormatter.ofPattern(formato);
			return date.format(formatter);
		}catch(Exception e){
			throw new AbdcpException(Status.BAD_REQUEST, "  La fecha debe tener el siguiente formato "+formato);
		}
	}
	
	

}
