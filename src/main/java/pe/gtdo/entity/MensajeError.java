package  pe.gtdo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="mensaje_error")
@NamedQuery(name="MensajeError.findAll", query="SELECT t FROM MensajeError t")
public class MensajeError {
     
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_mensaje_error")
	private Long idMensajeError;

	private String codigo;

	private String descripcion;
	
	private String tipo;

	@Column(name="es_activo")
	private Boolean esActivo;

	public Long getIdMensajeError() {
		return idMensajeError;
	}

	public void setIdMensajeError(Long idMensajeError) {
		this.idMensajeError = idMensajeError;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Boolean getEsActivo() {
		return esActivo;
	}

	public void setEsActivo(Boolean esActivo) {
		this.esActivo = esActivo;
	}
	
	
	
	
	
	
}
