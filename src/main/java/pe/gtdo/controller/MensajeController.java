package pe.gtdo.controller;

import java.time.LocalDateTime;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.bind.JAXBException;

import pe.gtdo.cliente.ClienteSoap;
import pe.gtdo.dao.MensajeDao;
import pe.gtdo.entity.MensageAbdcp;
import pe.gtdo.exception.AbdcpException;
import pe.gtdo.msg.Builder;
import pe.gtdo.soap.ReceiveMessageResponse;
import pe.gtdo.tipo.MensajeABDCP;
import pe.gtdo.tipo.TipoAsignacionNumeroSolicitud;
import pe.gtdo.tipo.TipoCuerpoMensaje;
import pe.gtdo.tipo.TipoEnvioSolicitudCedente;
import pe.gtdo.tipo.TipoNoIntegridad;
import pe.gtdo.tipo.TipoNotificacionError;
import pe.gtdo.tipo.TipoObjecionConcesionarioCedente;
import pe.gtdo.tipo.TipoRechazadaABDCP;
import pe.gtdo.tipo.TipoSolicitudPortabilidad;
import pe.gtdo.util.FechaUtil;
import pe.gtdo.util.constante.Proceso;


@ApplicationScoped
public class MensajeController {
	
	@Inject
	MensajeDao mensajeDao;
	
	@Inject
	FechaUtil fechaUtil;
	
	public void enviarNI(String destinatario,String causaNoIntegridad,MensajeABDCP mensaje) throws Exception{
		
		Builder builder=new Builder();
	    builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo("00", Proceso.SP.getValue()));	    
	    builder.setCabeceraIdentificadorProceso(mensajeDao.generarCodigo("00", Proceso.DE.getValue()));
	    builder.setCabeceraRemitente("00");
	    builder.setCabeceraDestinatario(destinatario);
	    builder.setCabeceraFechaCreacionMensaje(fechaUtil.parseDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss"));
	    
	    TipoNoIntegridad noIntegridad=new TipoNoIntegridad();
	    noIntegridad.setFechaRecepcionMensajeAnterior(mensaje.getCabeceraMensaje().getFechaCreacionMensaje());
	    noIntegridad.setIdentificadorMensajeErroneo(mensaje.getCabeceraMensaje().getIdentificadorMensaje());
	    noIntegridad.setNumeroSecuencialSolicitud(mensaje.getCabeceraMensaje().getIdentificadorProceso());
	    noIntegridad.setCausaNoIntegridad(causaNoIntegridad);
	    builder.setNoIntegridad(noIntegridad);	
	    builder.setCuerpoIdMensaje("NI");
	    enviar(null,builder.build());
	}
	
	public void enviarNE(String destinatario,String codigo,String descripcionCodigoError,MensajeABDCP mensaje) throws Exception{
		
		Builder builder=new Builder();
	    builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo("00", Proceso.SP.getValue()));	    
	    builder.setCabeceraIdentificadorProceso(mensajeDao.generarCodigo("00", Proceso.DE.getValue()));
	    builder.setCabeceraRemitente("00");
	    builder.setCabeceraDestinatario(destinatario);
	    builder.setCabeceraFechaCreacionMensaje(fechaUtil.parseDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss"));	    
	    TipoNotificacionError notificacionError=new TipoNotificacionError();
	    notificacionError.setCodigoError(codigo);
	    notificacionError.setDescripcionCodigoError(descripcionCodigoError);
	    builder.setNotificacionError(notificacionError);
	    builder.setCuerpoIdMensaje("NE");
	    enviar(null,builder.build());
	}
	
	
	public void enviarANCP( MensajeABDCP mensaje,String destinatario,String numero ) throws Exception{
		
		 String fecha = fechaUtil.parseDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss");
		 Builder builder=new Builder();	    
		 builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo("00", Proceso.SP.getValue()));
		 builder.setCabeceraIdentificadorProceso(mensaje.getCabeceraMensaje().getIdentificadorProceso());
		 builder.setCabeceraRemitente("00");
		 builder.setCabeceraDestinatario(destinatario);
		 builder.setCabeceraFechaCreacionMensaje(fecha);
		 
		 
		 TipoAsignacionNumeroSolicitud asignacionNumeroConsultaPrevia= new TipoAsignacionNumeroSolicitud();
		 asignacionNumeroConsultaPrevia.setFechaRecepcionMensajeAnterior(mensaje.getCabeceraMensaje().getFechaCreacionMensaje());
		 asignacionNumeroConsultaPrevia.setFechaReferenciaABDCP(fecha);
		 asignacionNumeroConsultaPrevia.setNumeracion(numero);
		 asignacionNumeroConsultaPrevia.setIdentificacionSolicitud(mensajeDao.generarCodigo("00", Proceso.CP.getValue()));
		 builder.setAsignacionNumeroConsultaPrevia(asignacionNumeroConsultaPrevia);
		 builder.setCuerpoIdMensaje("ANCP");		 
		 enviar(null,builder.build());
		
	}
	
	
	

 	public void enviarCPRABD(MensajeABDCP mensaje,String idSolicitd,
 			String destinatario,
 			String numeracion,String causaRechazo,
 			String monto,
 			String moneda,
 			String fechaActivacion,
 			String fechaTerminoContratoEquipo,
 			String fechaVencimiento
 			
 			) throws Exception{
		Builder builder=new Builder();
	    builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo("00", Proceso.SP.getValue()));	    
	    builder.setCabeceraIdentificadorProceso(idSolicitd);
	    builder.setCabeceraRemitente("00");
	    builder.setCabeceraDestinatario(destinatario);	    
	    builder.setCabeceraFechaCreacionMensaje(fechaUtil.parseDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss"));	    
	    TipoRechazadaABDCP  consultaPreviaRechazadaABDCP= new TipoRechazadaABDCP();
	        
	    consultaPreviaRechazadaABDCP.setNumeracion(numeracion);
	    consultaPreviaRechazadaABDCP.setIdentificacionSolicitud(idSolicitd);
	    consultaPreviaRechazadaABDCP.setCausaRechazo(causaRechazo);
	    consultaPreviaRechazadaABDCP.setMonto(monto);
	    consultaPreviaRechazadaABDCP.setMoneda(moneda);
	    consultaPreviaRechazadaABDCP.setFechaActivacion(fechaActivacion);
	    consultaPreviaRechazadaABDCP.setFechaVencimiento(fechaVencimiento);
	    
	    consultaPreviaRechazadaABDCP.setFechaTerminoContratoEquipo(fechaTerminoContratoEquipo);
	    
        	    
	    builder.setConsultaPreviaRechazadaABDCP(consultaPreviaRechazadaABDCP);
	    builder.setCuerpoIdMensaje("CPRABD");
	    enviar(null,builder.build());
	
	}
	
	
	
	public void enviarECPC(MensajeABDCP mensajeCP,String idSolicitd,
 			String destinatario,
 			String numeracion,
 			String tipoPortabilidadCedente
 			) throws Exception{
		String fecha = fechaUtil.parseDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss");
		Builder builder=new Builder();
	    builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo("00", Proceso.SP.getValue()));	    
	    builder.setCabeceraIdentificadorProceso(idSolicitd);
	    builder.setCabeceraRemitente("00");
	    builder.setCabeceraDestinatario(destinatario);	    
	    builder.setCabeceraFechaCreacionMensaje(fecha);	
	    
	    
	    TipoEnvioSolicitudCedente envioSolicitudCedente= new TipoEnvioSolicitudCedente();
	    
	    TipoSolicitudPortabilidad consulta = mensajeCP.getCuerpoMensaje().getConsultaPrevia();
	    envioSolicitudCedente.setCliente(consulta.getCliente());
	    envioSolicitudCedente.setCodigoCedente(consulta.getCodigoCedente());
	    envioSolicitudCedente.setCodigoReceptor(consulta.getCodigoReceptor());
	    envioSolicitudCedente.setEmailContacto(consulta.getEmailContacto());
	    envioSolicitudCedente.setFaxContacto(consulta.getFaxContacto());
	    envioSolicitudCedente.setFechaReferenciaABDCP(fecha);
	    envioSolicitudCedente.setNombreContacto(consulta.getNombreContacto());
	    envioSolicitudCedente.setNumeracion(numeracion);
	    envioSolicitudCedente.setNumeroDocumentoIdentidad(consulta.getNumeroDocumentoIdentidad());
	    envioSolicitudCedente.setTelefonoContacto(consulta.getTelefonoContacto());
	    envioSolicitudCedente.setTipoDocumentoIdentidad(consulta.getTipoDocumentoIdentidad());
	    envioSolicitudCedente.setTipoServicio(consulta.getTipoServicio());
	    
	    envioSolicitudCedente.setTipoPortabilidadCedente(tipoPortabilidadCedente);
	    
	    	    
	    builder.setEnvioSolicitudCedente(envioSolicitudCedente);
	    builder.setCuerpoIdMensaje("ECPC");
	    enviar(null,builder.build());
	    
	}
	
	
	public void enviarCPOCC(String cedente
			,String destinatario
			,String idSolicitd 
			,String causaObjecion
			,String fechaActivacion
			,String fechaTerminoContratoEquipo
			,String fechaVencimiento
			,String moneda
			,String monto
			,String numeracion
			
			) throws Exception{
		
		Builder builder=new Builder();
		String fecha = fechaUtil.parseDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss");		
		builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo(cedente, Proceso.SP.getValue()));	    
	    builder.setCabeceraIdentificadorProceso(idSolicitd);
	    builder.setCabeceraRemitente(cedente);
	    builder.setCabeceraDestinatario(destinatario);	    
	    builder.setCabeceraFechaCreacionMensaje(fecha);	
		
	    TipoObjecionConcesionarioCedente consultaPreviaObjecionConcesionarioCedente= new TipoObjecionConcesionarioCedente();
	    
	    
	    consultaPreviaObjecionConcesionarioCedente.setCausaObjecion(causaObjecion);
	    consultaPreviaObjecionConcesionarioCedente.setFechaActivacion(fechaActivacion);
	    consultaPreviaObjecionConcesionarioCedente.setFechaTerminoContratoEquipo(fechaTerminoContratoEquipo);
	    consultaPreviaObjecionConcesionarioCedente.setFechaVencimiento(fechaVencimiento);
	    consultaPreviaObjecionConcesionarioCedente.setMoneda(moneda);
	    consultaPreviaObjecionConcesionarioCedente.setMonto(monto);	    
	    consultaPreviaObjecionConcesionarioCedente.setNumeracion(numeracion);
	    
	    
	    
	    builder.setConsultaPreviaObjecionConcesionarioCedente(consultaPreviaObjecionConcesionarioCedente);	    
	    builder.setCuerpoIdMensaje("CPOCC");
		enviar(null,builder.build());
	}
	
	
	

	private void enviar(byte[] archivo,String msg) throws AbdcpException{
		ClienteSoap soap= new ClienteSoap();		
		soap.setConfig("http://localhost:8080/Portaflow/services/ABDCPWebService?wsdl", "http://ws.inpac.telcordia.com","ABDCPWebService", "http://localhost:8080/Portaflow/services/ABDCPWebService");
		ReceiveMessageResponse respuesta = soap.enviarMensaje(archivo, "", "", msg);
		
	}
	

}
