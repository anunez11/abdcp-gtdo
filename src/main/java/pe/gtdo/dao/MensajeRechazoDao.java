package pe.gtdo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import pe.gtdo.entity.MensajeRechazo;



@ApplicationScoped
public class MensajeRechazoDao extends TransactionDao {
   
	 
  	public  MensajeRechazo getMensajeRechazo(String codigo){
  		Map<String,Object> parameters=new HashMap<String,Object>();
  		parameters.put("codigo", codigo);
  		List<MensajeRechazo> rechazos =crudService.findWithQuery("select u from MensajeRechazo u where u.codgio=:codigo ", parameters);
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
