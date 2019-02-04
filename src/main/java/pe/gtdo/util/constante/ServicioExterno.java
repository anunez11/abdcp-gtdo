package pe.gtdo.util.constante;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import pe.gtdo.core.configuration.*;

@ApplicationScoped
public class ServicioExterno {

	@Inject @StageDependent
	private String operador;

	
	@Inject @StageDependent
	private String wsdlAbdcp;
	
	
	@Inject @StageDependent
	private String endPointAbdcp;
	
	
	@Inject @StageDependent
	private String serviceNameAbdcp;
	
	@Inject @StageDependent
	private String targetNameAbdcp;
	
	
	@Inject @StageDependent
	private String usuario;
	
	@Inject @StageDependent
	private String clave;
	
	
	@Inject @StageDependent
	private String validacionXSD;

	public String getOperador() {
		return operador;
	}

	public String getWsdlAbdcp() {
		return wsdlAbdcp;
	}

	public String getEndPointAbdcp() {
		return endPointAbdcp;
	}

	public String getServiceNameAbdcp() {
		return serviceNameAbdcp;
	}

	public String getTargetNameAbdcp() {
		return targetNameAbdcp;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getClave() {
		return clave;
	}

	public String getValidacionXSD() {
		return validacionXSD;
	}
	
	
	
	
	
}
