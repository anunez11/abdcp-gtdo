package pe.gtdo.dao;




import javax.ejb.EJB;
import javax.inject.Inject;





//import pe.gob.mpfn.entitymanager.producer.controller.CrudServiceController;
import pe.gtdo.entitymanager.producer.controller.CrudServiceController;
//import pe.gtdo.logging.AbdcpLogger;
import pe.gtdo.util.FechaUtil;
import pe.gtdo.util.Utilitario;






public class TransactionDao {

	@EJB
	CrudServiceController crudService;
	
	@Inject
	Utilitario utilitario;
	
	@Inject
	FechaUtil fechaUtil;
	
	/*@Inject
	AbdcpLogger LOG;*/
	
		
}
