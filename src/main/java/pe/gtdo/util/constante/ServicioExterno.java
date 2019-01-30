package pe.gtdo.util.constante;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import pe.gtdo.core.configuration.*;




@ApplicationScoped
public class ServicioExterno {

	
	@Inject @StageDependent
	private String servicioCatalogo;

	
	@Inject @StageDependent
	private String servicioCarpeta;
	
	
	
	public String getServicioCatalogo() {
		return servicioCatalogo;
	}


	public String getServicioCarpeta() {
		return servicioCarpeta;
	}
	
	
	
}
