package pe.gtdo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;

import pe.gtdo.entity.MensajeError;

@ApplicationScoped
public class MensajeErrorDao extends TransactionDao {

	public String getError(String error){
		Map<String,Object> parameters=new HashMap<String,Object>();
		parameters.put("codigo", error);
		List<MensajeError> lista=crudService.findWithQuery("select u from MensajeError u where u.codigo=:codigo", parameters);
		if(lista.size()>0) return lista.get(0).getCodigo();
		return "ERRSOAP001:Error Interno: no se pudo establecer conexión con la base de datos, por favor contacte al Administrador del Sistema.";	
	}
	
	
	
}
