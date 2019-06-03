package pe.gtdo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * The persistent class for the acreditacion_pago database table.
 * 
 */
@Entity
@Table(name="acreditacion_pago")
@NamedQuery(name="AcreditacionPago.findAll", query="SELECT a FROM AcreditacionPago a")
public class AcreditacionPago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_acreditacion_pago")
	private Long idAcreditacionPago;

	private String adjunto;

	

	@Column(name="entidad_pago")
	private String entidadPago;

	@Column(name="es_activo")
	private Boolean esActivo=true;

	@Column(name="es_enviado")
	private Boolean esEnviado=false;

	@Column(name="fecha_creacion")
	private LocalDateTime fechaCreacion=LocalDateTime.now();
	
	@Column(name="fecha_modificacion")
	private LocalDateTime fechaModificacion=LocalDateTime.now();

	
	@Column(name="fecha_limite_envio")
	private LocalDateTime fechaLimiteEnvio;

	
	@Column(name="fecha_envio")
	private LocalDateTime fechaEnvio;



	@Column(name="fecha_pago")
	private LocalDateTime fechaPago;

	@Column(name="id_mensaje")
	private String idMensaje;

	@Column(name="id_proceso")
	private String idProceso;

	private String moneda;

	private Double monto;

	private String numero;

	@Column(name="numero_trasaccion")
	private String numeroTrasaccion;

	private String tipo;

	public AcreditacionPago() {
	}

	public Long getIdAcreditacionPago() {
		return this.idAcreditacionPago;
	}

	public void setIdAcreditacionPago(Long idAcreditacionPago) {
		this.idAcreditacionPago = idAcreditacionPago;
	}

	public String getAdjunto() {
		return this.adjunto;
	}

	public void setAdjunto(String adjunto) {
		this.adjunto = adjunto;
	}



	public String getEntidadPago() {
		return this.entidadPago;
	}

	public void setEntidadPago(String entidadPago) {
		this.entidadPago = entidadPago;
	}

	public Boolean getEsActivo() {
		return this.esActivo;
	}

	public void setEsActivo(Boolean esActivo) {
		this.esActivo = esActivo;
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

	public LocalDateTime getFechaEnvio() {
		return this.fechaEnvio;
	}

	public void setFechaEnvio(LocalDateTime fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public LocalDateTime getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public LocalDateTime getFechaPago() {
		return this.fechaPago;
	}

	public void setFechaPago(LocalDateTime fechaPago) {
		this.fechaPago = fechaPago;
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

	public String getMoneda() {
		return this.moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public Double getMonto() {
		return this.monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNumeroTrasaccion() {
		return this.numeroTrasaccion;
	}

	public void setNumeroTrasaccion(String numeroTrasaccion) {
		this.numeroTrasaccion = numeroTrasaccion;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public LocalDateTime getFechaLimiteEnvio() {
		return fechaLimiteEnvio;
	}

	public void setFechaLimiteEnvio(LocalDateTime fechaLimiteEnvio) {
		this.fechaLimiteEnvio = fechaLimiteEnvio;
	}

	
	
	
}