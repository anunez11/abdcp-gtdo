package pe.gtdo.controller;

import java.time.LocalDateTime;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


import pe.gtdo.cliente.ClienteSoap;
import pe.gtdo.dao.MensajeDao;

import pe.gtdo.msg.Builder;
import pe.gtdo.soap.ReceiveMessageResponse;
import pe.gtdo.tipo.MensajeABDCP;
import pe.gtdo.tipo.TipoAceptacionRetorno;
import pe.gtdo.tipo.TipoAcreditacionPagoDeuda;
import pe.gtdo.tipo.TipoAsignacionNumeroSolicitud;
import pe.gtdo.tipo.TipoCabeceraMensaje;
import pe.gtdo.tipo.TipoCancelacionNoProgramacionFecha;
import pe.gtdo.tipo.TipoCuerpoMensaje;
import pe.gtdo.tipo.TipoDenegacionRetorno;
import pe.gtdo.tipo.TipoEnvioSolicitudCedente;
import pe.gtdo.tipo.TipoFueraLimiteEjecutarPortabilidad;
import pe.gtdo.tipo.TipoNoIntegridad;
import pe.gtdo.tipo.TipoNotificacionError;
import pe.gtdo.tipo.TipoObjecionConcesionarioCedente;
import pe.gtdo.tipo.TipoProgramacionPortabilidad;
import pe.gtdo.tipo.TipoProgramadaEjecutarPortabilidad;
import pe.gtdo.tipo.TipoRechazadaABDCP;
import pe.gtdo.tipo.TipoSolicitudAceptadaCedente;
import pe.gtdo.tipo.TipoSolicitudPortabilidad;
import pe.gtdo.tipo.TipoSolicitudProcedente;
import pe.gtdo.tipo.TipoSolicitudRetorno;
import pe.gtdo.util.FechaUtil;
import pe.gtdo.util.Utilitario;
import pe.gtdo.util.constante.Proceso;
import pe.gtdo.util.constante.ServicioExterno;


@ApplicationScoped
public class MensajeController {
	
	@Inject
	MensajeDao mensajeDao;
	
	@Inject
	FechaUtil fechaUtil;
	
	@Inject
	HorarioController horarioController;
	
	
	@Inject
	Utilitario utilitario;
	
	
	@Inject
	ServicioExterno servicioExteno;
	
	
	public void enviarNI(String destinatario,String causaNoIntegridad,MensajeABDCP mensaje) throws Exception{
		
		Builder builder=new Builder();
	    builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo(servicioExteno.getAbdcp(), Proceso.SP.getValue()));	    
	    builder.setCabeceraIdentificadorProceso(mensajeDao.generarCodigo(servicioExteno.getAbdcp(), Proceso.DE.getValue()));
	    builder.setCabeceraRemitente(servicioExteno.getAbdcp());
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
	    builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo(servicioExteno.getAbdcp(), Proceso.SP.getValue()));	    
	    builder.setCabeceraIdentificadorProceso(mensajeDao.generarCodigo(servicioExteno.getAbdcp(), Proceso.DE.getValue()));
	    builder.setCabeceraRemitente(servicioExteno.getAbdcp());
	    builder.setCabeceraDestinatario(destinatario);
	    builder.setCabeceraFechaCreacionMensaje(fechaUtil.parseDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss"));	    
	    TipoNotificacionError notificacionError=new TipoNotificacionError();
	    notificacionError.setCodigoError(codigo);
	    notificacionError.setDescripcionCodigoError(descripcionCodigoError);
	    builder.setNotificacionError(notificacionError);
	    builder.setCuerpoIdMensaje("NE");
	    enviar(null,builder.build());
	}
	
	
	public void enviarANCP( MensajeABDCP mensaje,String destinatario,String idSolicitud,String numero ) throws Exception{
		
		 String fecha = fechaUtil.parseDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss");
		 Builder builder=new Builder();	    
		 builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo(servicioExteno.getAbdcp(), Proceso.SP.getValue()));
		 builder.setCabeceraIdentificadorProceso(mensaje.getCabeceraMensaje().getIdentificadorProceso());
		 builder.setCabeceraRemitente(servicioExteno.getAbdcp());
		 builder.setCabeceraDestinatario(destinatario);
		 builder.setCabeceraFechaCreacionMensaje(fecha);
		 
		 
		 TipoAsignacionNumeroSolicitud asignacionNumeroConsultaPrevia= new TipoAsignacionNumeroSolicitud();
		 asignacionNumeroConsultaPrevia.setFechaRecepcionMensajeAnterior(mensaje.getCabeceraMensaje().getFechaCreacionMensaje());
		 asignacionNumeroConsultaPrevia.setFechaReferenciaABDCP(fecha);
		 asignacionNumeroConsultaPrevia.setNumeracion(numero);
		 asignacionNumeroConsultaPrevia.setIdentificacionSolicitud(idSolicitud);
		 builder.setAsignacionNumeroConsultaPrevia(asignacionNumeroConsultaPrevia);
		 builder.setCuerpoIdMensaje("ANCP");		 
		 enviar(null,builder.build());
		
	}
	
	
	

 	public void enviarCPRABD(String idSolicitd,
 			String destinatario,
 			String numeracion,String causaRechazo,
 			String monto,
 			String moneda,
 			String fechaActivacion,
 			String fechaTerminoContratoEquipo,
 			String fechaVencimiento
 			
 			) throws Exception{
		Builder builder=new Builder();
	    builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo(servicioExteno.getAbdcp(), Proceso.SP.getValue()));	    
	    builder.setCabeceraIdentificadorProceso(idSolicitd);
	    builder.setCabeceraRemitente(servicioExteno.getAbdcp());
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
	    builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo(servicioExteno.getAbdcp(), Proceso.SP.getValue()));	    
	    builder.setCabeceraIdentificadorProceso(idSolicitd);
	    builder.setCabeceraRemitente(servicioExteno.getAbdcp());
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
	    envioSolicitudCedente.setTipoPortabilidadCedente(tipoPortabilidadCedente);
	    envioSolicitudCedente.setNombreContacto(consulta.getNombreContacto());
	    envioSolicitudCedente.setNumeracion(numeracion);
	    envioSolicitudCedente.setNumeroDocumentoIdentidad(consulta.getNumeroDocumentoIdentidad());
	    envioSolicitudCedente.setTelefonoContacto(consulta.getTelefonoContacto());
	    envioSolicitudCedente.setTipoDocumentoIdentidad(consulta.getTipoDocumentoIdentidad());
	    envioSolicitudCedente.setTipoServicio(consulta.getTipoServicio());
	   
	   
	    
	    	    
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
	
	
	public void enviarCPAC(String cedente
			,String idSolicitud
			,String fechaActivacion
			,String fechaTerminoContratoEquipo
			,String observaciones
			) throws Exception{
		
		
		Builder builder=new Builder();
		String fecha = fechaUtil.parseDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss");		
		builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo(cedente, Proceso.SP.getValue()));	    
	    builder.setCabeceraIdentificadorProceso(idSolicitud);
	    builder.setCabeceraRemitente(cedente);
	    builder.setCabeceraDestinatario(servicioExteno.getAbdcp());	    
	    builder.setCabeceraFechaCreacionMensaje(fecha);	
	    
	    TipoSolicitudAceptadaCedente  consultaPreviaAceptadaCedente=new TipoSolicitudAceptadaCedente();
	    consultaPreviaAceptadaCedente.setFechaActivacion(fechaActivacion);
	    consultaPreviaAceptadaCedente.setFechaTerminoContratoEquipo(fechaTerminoContratoEquipo);
	    consultaPreviaAceptadaCedente.setObservaciones(observaciones);
	    builder.setConsultaPreviaAceptadaCedente(consultaPreviaAceptadaCedente);
	    builder.setCuerpoIdMensaje("CPAC");
	    enviar(null,builder.build());
	}
	
	
	
	public void enviarCPPR(String idSolicitud
			,String destinatario
			,String fechaActivacion
			,String fechaTerminoContratoEquipo
			) throws Exception{

		Builder builder=new Builder();
		String fecha = fechaUtil.parseDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss");		
		builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo(servicioExteno.getAbdcp(), Proceso.SP.getValue()));	    
	    builder.setCabeceraIdentificadorProceso(idSolicitud);
	    builder.setCabeceraRemitente(servicioExteno.getAbdcp());
	    builder.setCabeceraDestinatario(destinatario);	    
	    builder.setCabeceraFechaCreacionMensaje(fecha);	
	    
	    TipoSolicitudProcedente consultaPreviaProcedente = new TipoSolicitudProcedente();
	    consultaPreviaProcedente.setNumeroConsultaPrevia(idSolicitud);
	    consultaPreviaProcedente.setFechaActivacion(fechaActivacion);
	    consultaPreviaProcedente.setFechaTerminoContratoEquipo(fechaTerminoContratoEquipo);
	    builder.setConsultaPreviaProcedente(consultaPreviaProcedente);
	    builder.setCuerpoIdMensaje("CPPR");
	    MensajeABDCP data = builder.build();
	    enviar(null,data);
	}
	
	
	
	public void enviarSRC(MensajeABDCP mensaje) throws Exception{
		
		String idSolicitud=mensaje.getCabeceraMensaje().getIdentificadorProceso();
		String destinatario=mensaje.getCuerpoMensaje().getSolicitudRetorno().getCodigoCedente();
		String receptor=mensaje.getCuerpoMensaje().getSolicitudRetorno().getCodigoReceptor();
		TipoSolicitudRetorno data = mensaje.getCuerpoMensaje().getSolicitudRetorno();
		
		
		Builder builder=new Builder();
		String fecha = fechaUtil.parseDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss");		
		builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo(servicioExteno.getAbdcp(), Proceso.SP.getValue()));	    
	    builder.setCabeceraIdentificadorProceso(idSolicitud);
	    builder.setCabeceraRemitente(servicioExteno.getAbdcp());
	    builder.setCabeceraDestinatario(destinatario);	    
	    builder.setCabeceraFechaCreacionMensaje(fecha);
	    
	    TipoSolicitudRetorno solicitudRetorno=new TipoSolicitudRetorno();
	    solicitudRetorno.setCodigoCedente(destinatario);
	    solicitudRetorno.setCodigoReceptor(receptor);
	    solicitudRetorno.setEmailContacto(data.getEmailContacto());
	    solicitudRetorno.setFaxContacto(data.getFaxContacto());
	    solicitudRetorno.setFechaEjecucionRetorno(data.getFechaEjecucionRetorno());
	    solicitudRetorno.setMotivoRetorno(data.getMotivoRetorno());
	    solicitudRetorno.setNombreContacto(data.getNombreContacto());
	    solicitudRetorno.setNumeracionARetornar(data.getNumeracionARetornar());
	    solicitudRetorno.setObservaciones(data.getObservaciones());
	    solicitudRetorno.setTelefonoContacto(data.getTelefonoContacto());
	    builder.setSolicitudRetorno(solicitudRetorno);
	    builder.setCuerpoIdMensaje("SR");
	    MensajeABDCP dato = builder.build();
	    enviar(null,dato);
	    
		
	}
	public void enviarAR(MensajeABDCP mensaje) throws Exception{
		
		String idSolicitud=mensaje.getCabeceraMensaje().getIdentificadorProceso();
		String destinatario=mensaje.getCuerpoMensaje().getSolicitudRetorno().getCodigoReceptor();
		TipoSolicitudRetorno retorno = mensaje.getCuerpoMensaje().getSolicitudRetorno();
		Builder builder=new Builder();
		String fecha = fechaUtil.parseDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss");		
		builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo(servicioExteno.getAbdcp(), Proceso.SP.getValue()));	    
	    builder.setCabeceraIdentificadorProceso(idSolicitud);
	    builder.setCabeceraRemitente(servicioExteno.getAbdcp());
	    builder.setCabeceraDestinatario(destinatario);	    
	    builder.setCabeceraFechaCreacionMensaje(fecha);	
		
	    TipoAceptacionRetorno aceptacionRetorno= new TipoAceptacionRetorno();
	    aceptacionRetorno.setFechaEjecucionRetorno( retorno.getFechaEjecucionRetorno());
	    builder.setAceptacionRetorno(aceptacionRetorno);
	    builder.setCuerpoIdMensaje("AR");
	    MensajeABDCP data = builder.build();
	    enviar(null,data);
	}
	
	public void enviarDR(MensajeABDCP mensaje,String causaRechazo) throws Exception{
		
		
		String idSolicitud=mensaje.getCabeceraMensaje().getIdentificadorProceso();
		String destinatario=mensaje.getCuerpoMensaje().getSolicitudRetorno().getCodigoReceptor();
		
		Builder builder=new Builder();
		String fecha = fechaUtil.parseDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss");		
		builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo(servicioExteno.getAbdcp(), Proceso.SP.getValue()));	    
	    builder.setCabeceraIdentificadorProceso(idSolicitud);
	    builder.setCabeceraRemitente(servicioExteno.getAbdcp());
	    builder.setCabeceraDestinatario(destinatario);	    
	    builder.setCabeceraFechaCreacionMensaje(fecha);	
		
	    TipoDenegacionRetorno denegacionRetorno= new TipoDenegacionRetorno();
	    denegacionRetorno.setCausaRechazo(causaRechazo);
	    builder.setDenegacionRetorno(denegacionRetorno);
	    builder.setCuerpoIdMensaje("DR");
	    MensajeABDCP data = builder.build();
	    enviar(null,data);
		
	}
	
	public void enviarANS ( MensajeABDCP mensaje,String destinatario,String idSolicitud,String numero ) throws Exception{
		
		 String fecha = fechaUtil.parseDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss");
		 Builder builder=new Builder();	    
		 builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo(servicioExteno.getAbdcp(), Proceso.SP.getValue()));
		 builder.setCabeceraIdentificadorProceso(mensaje.getCabeceraMensaje().getIdentificadorProceso());
		 builder.setCabeceraRemitente(servicioExteno.getAbdcp());
		 builder.setCabeceraDestinatario(destinatario);
		 builder.setCabeceraFechaCreacionMensaje(fecha);
		 
		 
		 TipoAsignacionNumeroSolicitud asignacionNumeroSolicitud= new TipoAsignacionNumeroSolicitud();
		 asignacionNumeroSolicitud.setFechaRecepcionMensajeAnterior(mensaje.getCabeceraMensaje().getFechaCreacionMensaje());
		 asignacionNumeroSolicitud.setFechaReferenciaABDCP(fecha);
		 asignacionNumeroSolicitud.setNumeracion(numero);
		 asignacionNumeroSolicitud.setIdentificacionSolicitud(idSolicitud);
		 builder.setAsignacionNumeroConsultaPrevia(asignacionNumeroSolicitud);
		 builder.setCuerpoIdMensaje("ANS");	 
		 enviar(null,builder.build());
		
	}
	
	
	
	public void enviarRABDCP(String idSolicitud,
 			String destinatario,
 			String numeracion,String causaRechazo,
 			String monto,
 			String moneda,
 			String fechaActivacion,
 			String fechaTerminoContratoEquipo,
 			String fechaVencimiento) throws Exception{
		
		
		 String fecha = fechaUtil.parseDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss");
		
		 Builder builder=new Builder();	    
		 builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo(servicioExteno.getAbdcp(), Proceso.SP.getValue()));
		 builder.setCabeceraIdentificadorProceso(idSolicitud);
		 builder.setCabeceraRemitente(servicioExteno.getAbdcp());
		 builder.setCabeceraDestinatario(destinatario);
		 builder.setCabeceraFechaCreacionMensaje(fecha);
		
		 TipoRechazadaABDCP rechazadaABDCP= new TipoRechazadaABDCP();
		 rechazadaABDCP.setIdentificacionSolicitud(idSolicitud);

		 rechazadaABDCP.setNumeracion(numeracion);
		 rechazadaABDCP.setIdentificacionSolicitud(idSolicitud);
		 rechazadaABDCP.setCausaRechazo(causaRechazo);
		 rechazadaABDCP.setMonto(monto);
		 rechazadaABDCP.setMoneda(moneda);
		 rechazadaABDCP.setFechaActivacion(fechaActivacion);
		 rechazadaABDCP.setFechaVencimiento(fechaVencimiento);		 
		 builder.setRechazadaABDCP(rechazadaABDCP);
		builder.setCuerpoIdMensaje("RABDCP");	 
		enviar(null,builder.build());
	}
	
	
	public void enviarESC(MensajeABDCP mensajeCP,String idSolicitd,
 			String destinatario,
 			String numeracion,
 			String tipoPortabilidadCedente
 			) throws Exception{
		String fecha = fechaUtil.parseDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss");
		Builder builder=new Builder();
	    builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo(servicioExteno.getAbdcp(), Proceso.SP.getValue()));	    
	    builder.setCabeceraIdentificadorProceso(idSolicitd);
	    builder.setCabeceraRemitente(servicioExteno.getAbdcp());
	    builder.setCabeceraDestinatario(destinatario);	    
	    builder.setCabeceraFechaCreacionMensaje(fecha);	
	    
	    
	    TipoEnvioSolicitudCedente envioSolicitudCedente= new TipoEnvioSolicitudCedente();
	    
	    TipoSolicitudPortabilidad consulta = mensajeCP.getCuerpoMensaje().getSolicitudPortabilidad();
	    envioSolicitudCedente.setCliente(consulta.getCliente());
	    envioSolicitudCedente.setCodigoCedente(consulta.getCodigoCedente());
	    envioSolicitudCedente.setCodigoReceptor(consulta.getCodigoReceptor());
	    envioSolicitudCedente.setEmailContacto(consulta.getEmailContacto());
	    envioSolicitudCedente.setFaxContacto(consulta.getFaxContacto());
	    envioSolicitudCedente.setFechaReferenciaABDCP(fecha);
	    envioSolicitudCedente.setTipoPortabilidadCedente(tipoPortabilidadCedente);
	    envioSolicitudCedente.setNombreContacto(consulta.getNombreContacto());
	    envioSolicitudCedente.setNumeracion(numeracion);
	    envioSolicitudCedente.setNumeroDocumentoIdentidad(consulta.getNumeroDocumentoIdentidad());
	    envioSolicitudCedente.setTelefonoContacto(consulta.getTelefonoContacto());
	    envioSolicitudCedente.setTipoDocumentoIdentidad(consulta.getTipoDocumentoIdentidad());
	    envioSolicitudCedente.setTipoServicio(consulta.getTipoServicio());
	   
	    	    
	    builder.setEnvioSolicitudCedente(envioSolicitudCedente);
	    builder.setCuerpoIdMensaje("ESC");
	    enviar(null,builder.build());
	    
	}
	
	public void enviarSAC(String cedente
			,String idSolicitud
			,String fechaActivacion
			,String fechaTerminoContratoEquipo
			,String observaciones
			) throws Exception{
		
		
		Builder builder=new Builder();
		String fecha = fechaUtil.parseDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss");		
		builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo(cedente, Proceso.SP.getValue()));	    
	    builder.setCabeceraIdentificadorProceso(idSolicitud);
	    builder.setCabeceraRemitente(cedente);
	    builder.setCabeceraDestinatario("00");	    
	    builder.setCabeceraFechaCreacionMensaje(fecha);	
	    
	    TipoSolicitudAceptadaCedente  solicitudAceptadaCedente=new TipoSolicitudAceptadaCedente();
	    solicitudAceptadaCedente.setFechaActivacion(fechaActivacion);
	    solicitudAceptadaCedente.setFechaTerminoContratoEquipo(fechaTerminoContratoEquipo);
	    solicitudAceptadaCedente.setObservaciones(observaciones);
	    builder.setSolicitudAceptadaCedente(solicitudAceptadaCedente);
	    builder.setCuerpoIdMensaje("SAC");
	    enviar(null,builder.build());
	}
	
	
	
	public void enviarSPR(String idSolicitud
			,String destinatario
			,String fechaActivacion
			,String fechaTerminoContratoEquipo
			,String fechaLimiteProgamacion
			,String fechaLiminteEjecucion
			) throws Exception{

		Builder builder=new Builder();
		String fecha = fechaUtil.parseDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss");		
		builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo(servicioExteno.getAbdcp(), Proceso.SP.getValue()));	    
	    builder.setCabeceraIdentificadorProceso(idSolicitud);
	    builder.setCabeceraRemitente(servicioExteno.getAbdcp());
	    builder.setCabeceraDestinatario(destinatario);	    
	    builder.setCabeceraFechaCreacionMensaje(fecha);	
	    
	    TipoSolicitudProcedente solicitudProcedente = new TipoSolicitudProcedente();
	    solicitudProcedente.setFechaActivacion(fechaActivacion);
	    solicitudProcedente.setFechaTerminoContratoEquipo(fechaTerminoContratoEquipo);
	    solicitudProcedente.setFechaLimiteProgramacionPortabilidad(fechaLimiteProgamacion);
	    solicitudProcedente.setFechaLimiteEjecucionPortabilidad(fechaLiminteEjecucion);
	    solicitudProcedente.setFechaReferenciaABDCP(fecha);
	    
	    builder.setSolicitudProcedente(solicitudProcedente);
	    builder.setCuerpoIdMensaje("SPR");
	    MensajeABDCP data = builder.build();
	    enviar(null,data);
	}
	
	
	public void enviarCPSPR(String idSolicitud
			,String idSolicitudCP
			,String destinatario			
			,String fechaLimiteProgamacion
			,String fechaLiminteEjecucion
			) throws Exception{

		Builder builder=new Builder();
		String fecha = fechaUtil.parseDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss");		
		builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo(servicioExteno.getAbdcp(), Proceso.SP.getValue()));	    
	    builder.setCabeceraIdentificadorProceso(idSolicitud);
	    builder.setCabeceraRemitente(servicioExteno.getAbdcp());
	    builder.setCabeceraDestinatario(destinatario);	    
	    builder.setCabeceraFechaCreacionMensaje(fecha);	
	    
	    TipoSolicitudProcedente solicitudProcedente = new TipoSolicitudProcedente();
	    solicitudProcedente.setNumeroConsultaPrevia(idSolicitudCP);
	    solicitudProcedente.setFechaLimiteProgramacionPortabilidad(fechaLimiteProgamacion);
	    solicitudProcedente.setFechaLimiteEjecucionPortabilidad(fechaLiminteEjecucion);	    
	    solicitudProcedente.setFechaReferenciaABDCP(fecha);
	    builder.setSolicitudProcedenteConsultaPreviaProcedente(solicitudProcedente);
	    builder.setCuerpoIdMensaje("CPSPR");
	    MensajeABDCP data = builder.build();
	    enviar(null,data);
	}
	
	public void enviarOCC(String cedente			
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
	    builder.setCabeceraDestinatario(servicioExteno.getAbdcp());	    
	    builder.setCabeceraFechaCreacionMensaje(fecha);	
		
	    TipoObjecionConcesionarioCedente solicitudObjecionConcesionarioCedente= new TipoObjecionConcesionarioCedente();
	    
	    
	    solicitudObjecionConcesionarioCedente.setCausaObjecion(causaObjecion);
	    solicitudObjecionConcesionarioCedente.setFechaActivacion(fechaActivacion);
	    solicitudObjecionConcesionarioCedente.setFechaTerminoContratoEquipo(fechaTerminoContratoEquipo);
	    solicitudObjecionConcesionarioCedente.setFechaVencimiento(fechaVencimiento);
	    solicitudObjecionConcesionarioCedente.setMoneda(moneda);
	    solicitudObjecionConcesionarioCedente.setMonto(monto);	    
	    solicitudObjecionConcesionarioCedente.setNumeracion(numeracion);
	    
	    
	    
	    builder.setObjecionConcesionarioCedente(solicitudObjecionConcesionarioCedente);
	    builder.setCuerpoIdMensaje("OCC");
	    enviar(null,builder.build());
	}
	
	public void enviarAPD(String receptor			
			,String idSolicitd 			
			,String documentoAdjunto
			,String numeracion
			,byte[] archivo
			,String entidadPago
			,String fechaPago
			,String moneda
			,String monto
			,String numeroOperacionPago
			
			) throws Exception{
		
		Builder builder=new Builder();
		String fecha = fechaUtil.parseDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss");		
		builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo(receptor, Proceso.SP.getValue()));	    
	    builder.setCabeceraIdentificadorProceso(idSolicitd);
	    builder.setCabeceraRemitente(receptor);
	    builder.setCabeceraDestinatario(servicioExteno.getAbdcp());	    
	    builder.setCabeceraFechaCreacionMensaje(fecha);	
		
	    TipoAcreditacionPagoDeuda acreditacionPagoDeuda= new TipoAcreditacionPagoDeuda();
	    acreditacionPagoDeuda.setDocumentoAdjunto(documentoAdjunto);
	    acreditacionPagoDeuda.setEntidadPago(entidadPago);
	    acreditacionPagoDeuda.setFechaPago(fechaPago);
	    acreditacionPagoDeuda.setMoneda(moneda);
	    acreditacionPagoDeuda.setMonto(monto);
	    acreditacionPagoDeuda.setNumeracion(numeracion);
	    acreditacionPagoDeuda.setNumeroOperacionPago(numeroOperacionPago);

	    builder.setAcreditacionPagoDeuda(acreditacionPagoDeuda);
	    builder.setCuerpoIdMensaje("APD");
	    enviar(archivo,builder.build());
	}
	
	
	public void enviarAPDC(String cedente	,MensajeABDCP mensaje	,byte[] archivo	
			
			
			) throws Exception{
		
		
		TipoCabeceraMensaje cabecera = mensaje.getCabeceraMensaje();
		TipoCuerpoMensaje cuerpo = mensaje.getCuerpoMensaje();
		TipoAcreditacionPagoDeuda deuda=cuerpo.getAcreditacionPagoDeuda();
		Builder builder=new Builder();
		String fecha = fechaUtil.parseDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss");		
		builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo(servicioExteno.getAbdcp(), Proceso.SP.getValue()));	    
	    builder.setCabeceraIdentificadorProceso(cabecera.getIdentificadorProceso());
	    builder.setCabeceraRemitente(servicioExteno.getAbdcp());
	    builder.setCabeceraDestinatario(cedente);	    
	    builder.setCabeceraFechaCreacionMensaje(fecha);	
		
	    TipoAcreditacionPagoDeuda acreditacionPagoDeuda= new TipoAcreditacionPagoDeuda();
	    acreditacionPagoDeuda.setDocumentoAdjunto(deuda.getDocumentoAdjunto());
	    acreditacionPagoDeuda.setEntidadPago(deuda.getEntidadPago());
	    acreditacionPagoDeuda.setFechaPago(deuda.getFechaPago());
	    acreditacionPagoDeuda.setMoneda(deuda.getMoneda());
	    acreditacionPagoDeuda.setMonto(deuda.getMonto());
	    acreditacionPagoDeuda.setNumeracion(deuda.getNumeracion());
	    acreditacionPagoDeuda.setNumeroOperacionPago(deuda.getNumeroOperacionPago());

	    builder.setAcreditacionPagoDeuda(acreditacionPagoDeuda);
	    builder.setCuerpoIdMensaje("APDC");
	    enviar(archivo,builder.build());
	}
	
	
	public void enviarCNPF(String idSolicitud
			
			,String destinatario	
			
			) throws Exception{

		Builder builder=new Builder();
		String fecha = fechaUtil.parseDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss");
		String fechaLiminte = fechaUtil.parseDateTimeToString(LocalDateTime.now().plusHours(22), "yyyyMMddHHmmss");	
		builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo(servicioExteno.getAbdcp(), Proceso.SP.getValue()));	    
	    builder.setCabeceraIdentificadorProceso(idSolicitud);
	    builder.setCabeceraRemitente(servicioExteno.getAbdcp());
	    builder.setCabeceraDestinatario(destinatario);	    
	    builder.setCabeceraFechaCreacionMensaje(fecha);	
	    
	    TipoCancelacionNoProgramacionFecha cancelacion = new TipoCancelacionNoProgramacionFecha();
	    cancelacion.setFechaLimiteProgramacionPortabilidad(fechaLiminte);
	    builder.setCancelacionNoProgramacionFecha(cancelacion);
	    builder.setCuerpoIdMensaje("CNPF");
	    MensajeABDCP data = builder.build();
	    enviar(null,data);
	}
    
	public void enviarPP(
						  String idSolicitud			
						, String receptor			
						, String fechaLiminteEjecucion
					  ) throws Exception{

		Builder builder=new Builder();
		String fecha = fechaUtil.parseDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss");
		String fechaLiminte = fechaUtil.parseDateTimeToString(LocalDateTime.now().plusHours(22), "yyyyMMddHHmmss");	
		builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo(receptor, Proceso.SP.getValue()));	    
	    builder.setCabeceraIdentificadorProceso(idSolicitud);
	    builder.setCabeceraRemitente(receptor);
	    builder.setCabeceraDestinatario(servicioExteno.getAbdcp());	    
	    builder.setCabeceraFechaCreacionMensaje(fecha);	
	    
	    TipoProgramacionPortabilidad programacion = new TipoProgramacionPortabilidad();
	    programacion.setFechaEjecucionPortabilidad(fechaLiminteEjecucion);
	    builder.setProgramacionPortabilidad(programacion);	    
	    builder.setCuerpoIdMensaje("PP");
	    MensajeABDCP data = builder.build();
	    enviar(null,data);
	}
	
	
	public void enviarFLEP(
			  String idSolicitud			
			, String receptor
			, String fechaLiminteProgramacion
			, String fechaLiminteEjecucion
		  ) throws Exception{

			Builder builder=new Builder();
			String fecha = fechaUtil.parseDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss");
			String fechaLiminte = fechaUtil.parseDateTimeToString(LocalDateTime.now().plusHours(22), "yyyyMMddHHmmss");	
			builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo(servicioExteno.getAbdcp(), Proceso.SP.getValue()));	    
			builder.setCabeceraIdentificadorProceso(idSolicitud);
			builder.setCabeceraRemitente(servicioExteno.getAbdcp());
			builder.setCabeceraDestinatario(receptor);	    
			builder.setCabeceraFechaCreacionMensaje(fecha);	
			
			TipoFueraLimiteEjecutarPortabilidad programacion = new TipoFueraLimiteEjecutarPortabilidad();
			programacion.setFechaLimiteEjecucionPortabilidad(fechaLiminteEjecucion);
			programacion.setFechaLimiteProgramacionPortabilidad(fechaLiminteProgramacion);
			builder.setFueraLimiteEjecutarPortabilidad(programacion)  ;
			
			builder.setCuerpoIdMensaje("FLEP");
			MensajeABDCP data = builder.build();
			enviar(null,data);
    }
	
	public void enviarPEP(
			  String idSolicitud			
			, String destinatario
			, String fechaLiminteEjecucion
		  ) throws Exception{

			Builder builder=new Builder();
			String fecha = fechaUtil.parseDateTimeToString(LocalDateTime.now(), "yyyyMMddHHmmss");			
			builder.setCabeceraIdentificadorMensaje(mensajeDao.generarCodigo(servicioExteno.getAbdcp(), Proceso.SP.getValue()));	    
			builder.setCabeceraIdentificadorProceso(idSolicitud);
			builder.setCabeceraRemitente(servicioExteno.getAbdcp());
			builder.setCabeceraDestinatario(destinatario);	    
			builder.setCabeceraFechaCreacionMensaje(fecha);				
			TipoProgramadaEjecutarPortabilidad programacion = new TipoProgramadaEjecutarPortabilidad();
			programacion.setFechaEjecucionPortabilidad(fechaLiminteEjecucion);
			builder.setProgramadaEjecutarPortabilidad(programacion);			
			builder.setCuerpoIdMensaje("FLEP");
			MensajeABDCP data = builder.build();
			enviar(null,data);
  }
	
	
	private void enviar(byte[] archivo,MensajeABDCP mensaje) throws Exception{
		String msg=utilitario.converObjectToXmlString(mensaje);
		ClienteSoap soap= new ClienteSoap();
		mensajeDao.guardarMensaje(mensaje,msg, "OUT");
		
		soap.setConfig(servicioExteno.getWsdlAbdcp(), 
				servicioExteno.getTargetNameAbdcp(),
				servicioExteno.getServiceNameAbdcp(), 
				servicioExteno.getEndPointAbdcp());
		ReceiveMessageResponse respuesta = soap.enviarMensaje(archivo,servicioExteno.getClave(),servicioExteno.getUsuario(), msg);

		
		System.out.println("==================================================================");
		System.out.println("===> RESPONSE "+respuesta.getResponse());
		System.out.println("==================================================================");
	
	 
	}
	

}
