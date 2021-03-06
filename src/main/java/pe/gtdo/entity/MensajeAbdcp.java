package  pe.gtdo.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.w3c.dom.Document;

import pe.gtdo.entitymanager.adapter.ListStringUserType;
import pe.gtdo.entitymanager.adapter.XmlStringUsersType;

import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 * The persistent class for the mensage_abdcp database table.
 * 
 */
@Entity
@Table(name="mensaje_abdcp")
@NamedQuery(name="MensajeAbdcp.findAll", query="SELECT m FROM MensajeAbdcp m")
@TypeDefs({ @TypeDef(name = "XmlStringUsersType", typeClass = XmlStringUsersType.class) })
public class MensajeAbdcp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String destino;

	private String emisor;

	@Column(name="es_activo")
	private Boolean esActivo=true;

	@Column(name="fecha_creacion")
	private LocalDateTime fechaCreacion=LocalDateTime.now();

	@Column(name="fecha_envio")
	private LocalDateTime fechaEnvio=LocalDateTime.now();

	@Column(name="fecha_respuesta")
	private LocalDateTime fechaRespuesta;

	@Column(name="id_mensaje")
	private String idMensaje;
	
	
	
	@Type(type = "XmlStringUsersType")
	private Document request;
	@Type(type = "XmlStringUsersType")
	private Document response;

	@Column(name="codigo_mensaje")
	private String codigoMensaje;
	
	@Column(name="id_proceso")
	private String idProceso;
	
	@Column(name="id_solicitud")
	private String idSolicitud;
	
	
	private String numero;
	private String proceso;
	
	@Column(name="estado_proceso")
	private String estadoProceso;
	
	@Column(name="direccion_mensaje")
	private String direccionMensaje;
	
	@Column(name="fecha_modificacion")
	private LocalDateTime  fechaModificacion=LocalDateTime.now();
	
	
	@Version
	private Long version;

	public MensajeAbdcp() {
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

	
	

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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



	public Document getRequest() {
		return request;
	}

	public void setRequest(Document request) {
		this.request = request;
	}

	public Document getResponse() {
		return response;
	}

	public void setResponse(Document response) {
		this.response = response;
	}

	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getCodigoMensaje() {
		return codigoMensaje;
	}

	public void setCodigoMensaje(String codigoMensaje) {
		this.codigoMensaje = codigoMensaje;
	}

	public String getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(String idProceso) {
		this.idProceso = idProceso;
	}

	public String getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(String idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getEstadoProceso() {
		return estadoProceso;
	}

	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}

	public String getDireccionMensaje() {
		return direccionMensaje;
	}

	public void setDireccionMensaje(String direccionMensaje) {
		this.direccionMensaje = direccionMensaje;
	}

	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}
    
	
	
	
	
}