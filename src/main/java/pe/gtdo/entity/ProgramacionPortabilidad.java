package pe.gtdo.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.time.LocalDateTime;


/**
 * The persistent class for the programacion_portabilidad database table.
 * 
 */
@Entity
@Table(name="programacion_portabilidad")
@NamedQuery(name="ProgramacionPortabilidad.findAll", query="SELECT p FROM ProgramacionPortabilidad p")
public class ProgramacionPortabilidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_programacion_portabilidad")
	private Long idProgramacionPortabilidad;

	@Column(name="es_activo")
	private Boolean esActivo;

	@Column(name="es_ejecutado")
	private Boolean esEjecutado;

	@Column(name="es_enviado")
	private Boolean esEnviado;

	@Column(name="fecha_limite_envio")
	private LocalDateTime fechaLimiteEnvio;

	@Column(name="fecha_ejecucion")
	private LocalDateTime fechaEjecucion;

	@Column(name="fecha_envio")
	private LocalDateTime fechaEnvio;

	@Column(name="fecha_limite_ejecucion")
	private LocalDateTime fechaLimiteEjecucion;

	@Column(name="fecha_modificacion")
	private LocalDateTime fechaModificacion;
	
	@Column(name="fecha_creacion")
	private LocalDateTime fechaCreacion;

	@Column(name="fecha_progamada_ejecucion")
	private LocalDateTime fechaProgamadaEjecucion;

	@Column(name="id_mensaje")
	private String idMensaje;

	@Column(name="id_proceso")
	private String idProceso;

	private String numero;

	public ProgramacionPortabilidad() {
	}

	public Long getIdProgramacionPortabilidad() {
		return this.idProgramacionPortabilidad;
	}

	public void setIdProgramacionPortabilidad(Long idProgramacionPortabilidad) {
		this.idProgramacionPortabilidad = idProgramacionPortabilidad;
	}

	public Boolean getEsActivo() {
		return this.esActivo;
	}

	public void setEsActivo(Boolean esActivo) {
		this.esActivo = esActivo;
	}

	public Boolean getEsEjecutado() {
		return this.esEjecutado;
	}

	public void setEsEjecutado(Boolean esEjecutado) {
		this.esEjecutado = esEjecutado;
	}

	public Boolean getEsEnviado() {
		return this.esEnviado;
	}

	public void setEsEnviado(Boolean esEnviado) {
		this.esEnviado = esEnviado;
	}

	public LocalDateTime getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDateTime getFechaEjecucion() {
		return this.fechaEjecucion;
	}

	public void setFechaEjecucion(LocalDateTime fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

	public LocalDateTime getFechaEnvio() {
		return this.fechaEnvio;
	}

	public void setFechaEnvio(LocalDateTime fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public LocalDateTime getFechaLimiteEjecucion() {
		return this.fechaLimiteEjecucion;
	}

	public void setFechaLimiteEjecucion(LocalDateTime fechaLimiteEjecucion) {
		this.fechaLimiteEjecucion = fechaLimiteEjecucion;
	}

	public LocalDateTime getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public LocalDateTime getFechaProgamadaEjecucion() {
		return this.fechaProgamadaEjecucion;
	}

	public void setFechaProgamadaEjecucion(LocalDateTime fechaProgamadaEjecucion) {
		this.fechaProgamadaEjecucion = fechaProgamadaEjecucion;
	}

	public String getIdMensaje() {
		return this.idMensaje;
	}

	public void setIdMensaje(String idMensaje) {
		this.idMensaje = idMensaje;
	}

	public String getIdProceso() {
		return this.idProceso;
	}

	public void setIdProceso(String idProceso) {
		this.idProceso = idProceso;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public LocalDateTime getFechaLimiteEnvio() {
		return fechaLimiteEnvio;
	}

	public void setFechaLimiteEnvio(LocalDateTime fechaLimiteEnvio) {
		this.fechaLimiteEnvio = fechaLimiteEnvio;
	}
	
	
	
	
}