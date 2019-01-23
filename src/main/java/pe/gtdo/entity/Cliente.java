package pe.gtdo.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the departamento database table.
 * 
 */
@Entity
@NamedQuery(name="Cliente.findAll", query="SELECT d FROM Cliente d")
public class Cliente implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_cliente")
	private Long idCliente;

	

	@Column(name="tipo_documento")
	private String tipoDocumento;
	
	@Column(name="doc_identidad")
	private String docIdentidad;
	
	@Column(name="codigo_error")
	private String codigoEerror;
	
	private String numero;
	private String concesionario;

	private String cliente;

	@Column(name="es_activo")
	private Boolean esActivo;

	public Cliente() {
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDocIdentidad() {
		return docIdentidad;
	}

	public void setDocIdentidad(String docIdentidad) {
		this.docIdentidad = docIdentidad;
	}

	public String getCodigoEerror() {
		return codigoEerror;
	}

	public void setCodigoEerror(String codigoEerror) {
		this.codigoEerror = codigoEerror;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getConcesionario() {
		return concesionario;
	}

	public void setConcesionario(String concesionario) {
		this.concesionario = concesionario;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Boolean getEsActivo() {
		return esActivo;
	}

	public void setEsActivo(Boolean esActivo) {
		this.esActivo = esActivo;
	}

	

}