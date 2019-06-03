package pe.gtdo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import pe.gtdo.entity.MensajeAbdcp;
import pe.gtdo.entity.NumeroSolicitud;
import pe.gtdo.entity.Solicitud;
import pe.gtdo.util.constante.ConsultaPrevia;
import pe.gtdo.util.constante.SolicitudPortabilidad;

@ApplicationScoped
public class SolicitudDao extends TransactionDao{
    
	
	public void setIdProcesoNumeroSolicitud(MensajeAbdcp mensaje,String response) throws Exception{
		
		String codigo=mensaje.getCodigoMensaje();
		if(!SolicitudPortabilidad.fromType(codigo).equals(SolicitudPortabilidad.ANS)
				&& !ConsultaPrevia.fromType(codigo).equals(ConsultaPrevia.ANCP)){
			
			 // actualizamos por la idsolicitud ...
			
			Map<String,Object> parameters=new HashMap<String,Object>();
			parameters.put("idAbdcpSolicitud", mensaje.getIdSolicitud());
			List<NumeroSolicitud> numeroSolicitudes = crudService.findWithQuery("select u from NumeroSolicitud u where u.idAbdcpSolicitud=:idAbdcpSolicitud and u.esActivo=true", parameters);
			if(numeroSolicitudes.size()>0){
				NumeroSolicitud numeroSolicitud=crudService.find(NumeroSolicitud.class, numeroSolicitudes.get(0).getIdNumeroSolicitud());
				numeroSolicitud.setIdMensaje(mensaje.getIdMensaje());
				numeroSolicitud.setCodigoMensaje(mensaje.getCodigoMensaje());
				numeroSolicitud.setResponse(response);				
				update(numeroSolicitud);
			}
			
		}else{
			// agregamos el idSolickitud ... 
			Map<String,Object> parameters=new HashMap<String,Object>();
			parameters.put("idProceso", mensaje.getIdProceso());
			List<Solicitud> solicitudes = crudService.findWithQuery("select u from Solicitud u where u.idProceso=:idProceso and u.esActivo=true", parameters);
            if(solicitudes.size()>0){
            	Map<String,Object> parameters1=new HashMap<String,Object>();
            	parameters1.put("idSolicitud", solicitudes.get(0).getIdSolicitud());
            	parameters1.put("inicioRango", mensaje.getNumero());
    			List<NumeroSolicitud> numeroSolicitudes = crudService.findWithQuery("select u from NumeroSolicitud u where u.idSolicitud.idSolicitud=:idSolicitud and u.inicioRango=:inicioRango and u.esActivo=true", parameters1);
    			if(numeroSolicitudes.size()>0){
    				NumeroSolicitud numeroSolicitud=crudService.find(NumeroSolicitud.class, numeroSolicitudes.get(0).getIdNumeroSolicitud());
    				numeroSolicitud.setIdMensaje(mensaje.getIdMensaje());
    				numeroSolicitud.setCodigoMensaje(mensaje.getCodigoMensaje());
    				numeroSolicitud.setResponse(response);	
    				numeroSolicitud.setIdProceso(mensaje.getIdProceso());
    				numeroSolicitud.setIdAbdcpSolicitud(mensaje.getIdSolicitud());
    				numeroSolicitud.setIdMensaje(mensaje.getIdMensaje());
    				update(numeroSolicitud);
    			}
    	
            }
			
		}
		
		
		
		actualizarSolicitud(mensaje.getIdProceso(),mensaje,response);
		
	}
	
	

	
	public void actualizarSolicitud(String idProceso,MensajeAbdcp mensaje,String response) throws Exception{
		String codigo=mensaje.getCodigoMensaje();
		
		if(!SolicitudPortabilidad.fromType(codigo).equals(SolicitudPortabilidad.ANS)
				&& !ConsultaPrevia.fromType(codigo).equals(ConsultaPrevia.ANCP)){
			Map<String,Object> parameters=new HashMap<String,Object>();
			parameters.put("idProceso", mensaje.getIdProceso());
			List<Solicitud> solicitudes = crudService.findWithQuery("select u from Solicitud u where u.idProceso=:idProceso and u.esActivo=true", parameters);
			if(solicitudes.size()>0){
				Solicitud solicitud=crudService.find(Solicitud.class, solicitudes.get(0).getIdSolicitud());
				solicitud.setIdMensaje(mensaje.getIdMensaje());
				solicitud.setCodigoMensaje(mensaje.getCodigoMensaje());
				solicitud.setResponse(response);
				update(solicitud);
			}
			
			
		}
				
		
	}
	
	
}
