package pe.gtdo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import pe.gtdo.entity.Cliente;
import pe.gtdo.entitymanager.producer.controller.CrudServiceController;

@ApplicationScoped
public class ClienteDao extends TransactionDao{

	
	public Cliente getClienteByNumero(String numero ,String consecionario){
		
		 Map<String,Object> parameters=new HashMap<String,Object>();		 
		 parameters.put("numero", numero);		 
		 parameters.put("consecionario", consecionario);
	     List<Cliente> lista=crudService.findWithQuery("select  u Cliente u where u.numero=:numero and u.esActivo=true and u.consecionario=:consecionario", parameters);	     
		 if(lista.size()>0) return lista.get(0);		 
		 return null;
		
	}
	
	
	
}
