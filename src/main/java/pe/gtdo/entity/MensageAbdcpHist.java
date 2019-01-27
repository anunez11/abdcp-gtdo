package  pe.gtdo.entity;

import java.io.Serializable;

import javax.persistence.*;



import java.time.LocalDateTime;


/**
 * The persistent class for the mensage_abdcp database table.
 * 
 */
@Entity
@Table(name="mensage_abdcp_hist")
@NamedQuery(name="MensageAbdcpHist.findAll", query="SELECT m FROM MensageAbdcpHist m")
public class MensageAbdcpHist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_hist")
	private Long idHist;

	@ManyToOne
	@JoinColumn(name = "id")	
	private MensajeAbdcp id;
	
	
	private String destino;

	private String emisor;

	@Column(name="es_activo")
	private Boolean esActivo;

	@Column(name="fecha_creacion")
	private LocalDateTime fechaCreacion;

	@Column(name="fecha_envio")
	private LocalDateTime fechaEnvio;

	@Column(name="fecha_respuesta")
	private LocalDateTime fechaRespuesta;

	@Column(name="id_mensaje")
	private String idMensaje;

	private String request;

	private String response;

	@Column(name="codigo_mensage")
	private String codigoMensage;
	
	@Column(name="id_proceso")
	private String idProceso;
	
	@Column(name="id_solicitud")
	private String idSolicitud;
	
	
	private String numero;
	
	@Column(name="estado_proceso")
	private String estadoProceso;
	
	@Column(name="direccion_mensaje")
	private String direccionMensaje;
	
	@Column(name="fecha_modificacion")
	private LocalDateTime  fechaModificacion;
	
	
	@Version
	private Long version;

	public MensageAbdcpHist() {
	}


	public Long getIdHist() {
		return idHist;
	}


	public void setIdHist(Long idHist) {
		this.idHist = idHist;
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

	public String getCodigoMensage() {
		return codigoMensage;
	}

	public void setCodigoMensage(String codigoMensage) {
		this.codigoMensage = codigoMensage;
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


	public MensajeAbdcp getId() {
		return id;
	}


	public void setId(MensajeAbdcp id) {
		this.id = id;
	}
    
	
	
	
	
}