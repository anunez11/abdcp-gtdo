package pe.gtdo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import pe.gtdo.entity.BlacklistAbdcp;
import pe.gtdo.entity.MensajeRechazo;



@ApplicationScoped
public class MensajeRechazoDao extends TransactionDao {
   
	
	public BlacklistAbdcp getListanegra(String codigoMensajeEnvio,String codigoMensajeRecibido,String numero){
		Map<String,Object> parameters=new HashMap<String,Object>();
		String addSql="";
  		parameters.put("numero", numero);
  		if(codigoMensajeEnvio!=null){
  			parameters.put("mensajeEnviado", codigoMensajeEnvio);
  			addSql+=" and  u.mensajeEnviado=:mensajeEnviado";  			
  		} 
  		if(codigoMensajeRecibido!=null){
  			parameters.put("mensajeRecibido", codigoMensajeRecibido);
  			addSql+=" and  u.mensajeRecibido=:mensajeRecibido";
  		} 
		
  		List<BlacklistAbdcp> rechazos =crudService.findWithQuery("select u from BlacklistAbdcp u where u.numero=:numero "+addSql, parameters);
		if(rechazos.size()>0) return rechazos.get(0);
  		return null;
		
	}
	
	 
  	public  MensajeRechazo getMensajeRechazo(String codigo){
  		Map<String,Object> parameters=new HashMap<String,Object>();
  		parameters.put("codigo", codigo);
  		List<MensajeRechazo> rechazos =crudService.findWithQuery("select u from MensajeRechazo u where u.codigo=:codigo ", parameters);
  		if(rechazos.size()>0) return rechazos.get(0);
  		return null;  		
  	} 
	
  	
  	
  	public MensajeRechazo getMensajeRechazoAbdcp(String tipo,String proceso){
  		
  		String sql="select *  from mensaje_rechazo where '"+tipo+"'= any(mensaje_rechazado) and es_activo=true and proceso='"+proceso+"' and  participante_rechaza='ABDCP' order by random() limit 1" ;
  		
  		List<MensajeRechazo> lista = crudService.findByNativeQuery(sql, MensajeRechazo.class);
  		if(lista.size()>0) lista.get(0);
  		return null;
  		
  	}
	
}
