package pe.gtdo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

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
	
}
