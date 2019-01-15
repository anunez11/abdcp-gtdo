package pe.gtdo.configuration.entitymanager;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.gtdo.entitymanager.producer.DbApplication;

//import pe.gob.mpfn.entitymanager.producer.DbApplication;


/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class ProducerDataBase.
 * 
 * @author MPFN
 * @version 1.0 , 20/01/2018
 */
public class ProducerDataBase {
	
	/** La em. */
	@Produces
	@DbApplication
	@PersistenceContext(unitName = "CHANGEME")
	private EntityManager em;

	/**
	 * Instancia un nuevo producer data base.
	 */
	public ProducerDataBase() {
		// TODO Auto-generated constructor stub
	}

}
