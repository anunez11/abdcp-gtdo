package pe.gtdo.entity;

import java.io.Serializable;
import java.time.LocalDate;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_cliente")
	private Long idCliente;

	

	@Column(name="tipo_documento")
	private String tipoDocumento;
	
	@Column(name="doc_identidad")
	private String docIdentidad;
	
	@Column(name="codigo_error")
	private String codigoEerror;
	
	@Column(name="estado_factura")
	private String estadoFactura;
	
	
	private String numero;
	private String concesionario;

	private String cliente;

	@Column(name="es_activo")
	private Boolean esActivo;

	private String modalidad;
	
	@Column(name="monto_deuda")
	private Double montoDeuda;
	
	@Column(name="fecha_vencimiento_ultima_factura")
	private LocalDate fechaVencimientoUltimaFactura ;
	
	private String moneda ;
	
	@Column(name="fecha_activacion")
	private LocalDate fechaActivacion ;
	
	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public Double getMontoDeuda() {
		return montoDeuda;
	}

	public void setMontoDeuda(Double montoDeuda) {
		this.montoDeuda = montoDeuda;
	}

	public LocalDate getFechaVencimientoUltimaFactura() {
		return fechaVencimientoUltimaFactura;
	}

	public void setFechaVencimientoUltimaFactura(
			LocalDate fechaVencimientoUltimaFactura) {
		this.fechaVencimientoUltimaFactura = fechaVencimientoUltimaFactura;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}



	public LocalDate getFechaTerminoContratoEquipo() {
		return fechaTerminoContratoEquipo;
	}

	public void setFechaTerminoContratoEquipo(LocalDate fechaTerminoContratoEquipo) {
		this.fechaTerminoContratoEquipo = fechaTerminoContratoEquipo;
	}

	public String getEstadoServicio() {
		return estadoServicio;
	}

	public void setEstadoServicio(String estadoServicio) {
		this.estadoServicio = estadoServicio;
	}

	public String getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	@Column(name="fecha_termino_contrato_equipo")
	private LocalDate fechaTerminoContratoEquipo;
	
	@Column(name="estado_servicio")
	private String estadoServicio ;
	
	@Column(name="tipo_servicio")
	private String  tipoServicio ;
	
	
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

	public LocalDate getFechaActivacion() {
		return fechaActivacion;
	}

	public void setFechaActivacion(LocalDate fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}

	public String getEstadoFactura() {
		return estadoFactura;
	}

	public void setEstadoFactura(String estadoFactura) {
		this.estadoFactura = estadoFactura;
	}

	

}