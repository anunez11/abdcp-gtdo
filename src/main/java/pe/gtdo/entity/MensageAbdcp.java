package  pe.gtdo.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 * The persistent class for the mensage_abdcp database table.
 * 
 */
@Entity
@Table(name="mensage_abdcp")
@NamedQuery(name="MensageAbdcp.findAll", query="SELECT m FROM MensageAbdcp m")
public class MensageAbdcp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String destino;

	private String emisor;

	@Column(name="es_activo")
	private Boolean esActivo;

	@Column(name="fecha_creaacion")
	private LocalDateTime fechaCreaacion;

	@Column(name="fecha_envio")
	private LocalDateTime fechaEnvio;

	@Column(name="fecha_respuesta")
	private LocalDateTime fechaRespuesta;

	@Column(name="id_mensaje")
	private String idMensaje;

	private String request;

	private String response;

	private Long version;

	public MensageAbdcp() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDestino() {
		return this.destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getEmisor() {
		return this.emisor;
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

	public Boolean getEsActivo() {
		return this.esActivo;
	}

	public void setEsActivo(Boolean esActivo) {
		this.esActivo = esActivo;
	}

	public LocalDateTime getFechaCreaacion() {
		return this.fechaCreaacion;
	}

	public void setFechaCreaacion(LocalDateTime fechaCreaacion) {
		this.fechaCreaacion = fechaCreaacion;
	}

	public LocalDateTime getFechaEnvio() {
		return this.fechaEnvio;
	}

	public void setFechaEnvio(LocalDateTime fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public LocalDateTime getFechaRespuesta() {
		return this.fechaRespuesta;
	}

	public void setFechaRespuesta(LocalDateTime fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}

	public String getIdMensaje() {
		return this.idMensaje;
	}

	public void setIdMensaje(String idMensaje) {
		this.idMensaje = idMensaje;
	}

	public String getRequest() {
		return this.request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return this.response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}