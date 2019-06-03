package pe.gtdo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;




/**
 * The persistent class for the numero_solicitud database table.
 * 
 */
@Entity
@Table(name="numero_solicitud")
@NamedQuery(name="NumeroSolicitud.findAll", query="SELECT n FROM NumeroSolicitud n")
public class NumeroSolicitud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_numero_solicitud")
	
	private Long idNumeroSolicitud;

	@Column(name="es_activo")
	
	private Boolean esActivo=true;

	@Column(name="final_rango")
	
	private String finalRango;

	
	@ManyToOne
	@JoinColumn(name = "id_solicitud")
	
	private Solicitud idSolicitud;
	
	
	@Column(name="inicio_rango")
	
	private String inicioRango;

	@Column(name="tipo_portabilidad_cedente")
	
	private String tipoPortabilidadCedente;

	@Column(name="tipo_portabilidad_receptor")
	
	private String tipoPortabilidadReceptor;
	
	@Column(name="codigo_mensaje")
	private String codigoMensaje;
	
	@Column(name="id_mensaje")
	private String idMensaje;
	
	@Column(name="id_proceso")
	private String idProceso;
	
	
	@Column(name="id_abdcp_solicitud")
	private String idAbdcpSolicitud;
	
	private String response;
	
	@Column(name="fecha_creacion")
	
	private LocalDateTime fechaCreacion=LocalDateTime.now();
	
	@Column(name="fecha_modificacion")
	
	private LocalDateTime fechaModificacion=LocalDateTime.now();

	public NumeroSolicitud() {
	}

	public Long getIdNumeroSolicitud() {
		return this.idNumeroSolicitud;
	}

	public void setIdNumeroSolicitud(Long idNumeroSolicitud) {
		this.idNumeroSolicitud = idNumeroSolicitud;
	}

	public Boolean getEsActivo() {
		return this.esActivo;
	}

	public void setEsActivo(Boolean esActivo) {
		this.esActivo = esActivo;
	}

	public String getFinalRango() {
		return this.finalRango;
	}

	public void setFinalRango(String finalRango) {
		this.finalRango = finalRango;
	}


	
	

	public Solicitud getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(Solicitud idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public String getInicioRango() {
		return this.inicioRango;
	}

	public void setInicioRango(String inicioRango) {
		this.inicioRango = inicioRango;
	}

	public String getTipoPortabilidadCedente() {
		return this.tipoPortabilidadCedente;
	}

	public void setTipoPortabilidadCedente(String tipoPortabilidadCedente) {
		this.tipoPortabilidadCedente = tipoPortabilidadCedente;
	}

	public String getTipoPortabilidadReceptor() {
		return this.tipoPortabilidadReceptor;
	}

	public void setTipoPortabilidadReceptor(String tipoPortabilidadReceptor) {
		this.tipoPortabilidadReceptor = tipoPortabilidadReceptor;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getCodigoMensaje() {
		return codigoMensaje;
	}

	public void setCodigoMensaje(String codigoMensaje) {
		this.codigoMensaje = codigoMensaje;
	}

	public String getIdMensaje() {
		return idMensaje;
	}

	public void setIdMensaje(String idMensaje) {
		this.idMensaje = idMensaje;
	}

	public String getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(String idProceso) {
		this.idProceso = idProceso;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getIdAbdcpSolicitud() {
		return idAbdcpSolicitud;
	}

	public void setIdAbdcpSolicitud(String idAbdcpSolicitud) {
		this.idAbdcpSolicitud = idAbdcpSolicitud;
	}
    
	
	
}