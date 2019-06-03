package pe.gtdo.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;




import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//import com.fasterxml.jackson.annotation.*;


/**
 * The persistent class for the solicitud database table.
 * 
 */
@Entity
@XmlRootElement
@Table(name = "solicitud")
@NamedQuery(name="Solicitud.findAll", query="SELECT s FROM Solicitud s")
public class Solicitud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_solicitud")
	private Long idSolicitud;

	@Column(name="cantidad_numero")
   
	private Long cantidadNumero;

	private String cedente;
	
	private String codigo;
     
	@Column(name="correo_contacto")

	private String correoContacto;
	
	private String departamento;
	

	private String documento;

	@Column(name="es_activo")
	private Boolean esActivo=true;

	@Column(name="es_enviado")
	private Boolean esEnviado=false;

	@Column(name="fax_contacto")
   
	private String faxContacto;

	@Column(name="fecha_creacion")	
	

	
	private LocalDateTime fechaCreacion=LocalDateTime.now();

	
	@Column(name="fecha_ejecucion_retorno")
	
	private LocalDateTime fechaEjecucionRetorno;

	@Column(name="fecha_envio")	
	
	private LocalDateTime fechaEnvio;

	@Column(name="fecha_modificacion")	
	
	private LocalDateTime fechaModificacion=LocalDateTime.now();
	
	@Column(name="fecha_ejecucion_portabilidad")
	
	private LocalDateTime fechaEjecucionPortabilidad;
	

	@Column(name="id_mensaje")
	
	private String idMensaje;

	@Column(name="id_proceso")
	
	private String idProceso;

	@Column(name="motivo_retorno")
	
	private String motivoRetorno;

	@Column(name="nombre_contacto")
	
	private String nombreContacto;

	
	private String numero;

	@Column(name="numero_consulta_previa")
	
	private String numeroConsultaPrevia;

	
	private String observaciones;

	
	private String receptor;

	@Column(name="telefono_contacto")
	
	private String telefonoContacto;

	
	private String tipo;

	@Column(name="tipo_cliente")
	
	private String tipoCliente;

	@Column(name="tipo_documento")
	
	private String tipoDocumento;

	@Column(name="tipo_servicio")
	
	private String tipoServicio;

	@Column(name="codigo_mensaje")
	private String codigoMensaje;
	
	private String response;
	
	@Transient
	List<NumeroSolicitud> numeracion=new ArrayList<NumeroSolicitud>();
	
	public Solicitud() {
	}

	public Long getIdSolicitud() {
		return this.idSolicitud;
	}

	public void setIdSolicitud(Long idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public Long getCantidadNumero() {
		return this.cantidadNumero;
	}

	public void setCantidadNumero(Long cantidadNumero) {
		this.cantidadNumero = cantidadNumero;
	}

	public String getCedente() {
		return this.cedente;
	}

	public void setCedente(String cedente) {
		this.cedente = cedente;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCorreoContacto() {
		return this.correoContacto;
	}

	public void setCorreoContacto(String correoContacto) {
		this.correoContacto = correoContacto;
	}

	public String getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}



	public String getDocumento() {
		return this.documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
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

	public String getFaxContacto() {
		return this.faxContacto;
	}

	public void setFaxContacto(String faxContacto) {
		this.faxContacto = faxContacto;
	}

	public LocalDateTime getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDateTime getFechaEjecucionRetorno() {
		return this.fechaEjecucionRetorno;
	}

	public void setFechaEjecucionRetorno(LocalDateTime fechaEjecucionRetorno) {
		this.fechaEjecucionRetorno = fechaEjecucionRetorno;
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

	public String getMotivoRetorno() {
		return this.motivoRetorno;
	}

	public void setMotivoRetorno(String motivoRetorno) {
		this.motivoRetorno = motivoRetorno;
	}

	public String getNombreContacto() {
		return this.nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNumeroConsultaPrevia() {
		return this.numeroConsultaPrevia;
	}

	public void setNumeroConsultaPrevia(String numeroConsultaPrevia) {
		this.numeroConsultaPrevia = numeroConsultaPrevia;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getReceptor() {
		return this.receptor;
	}

	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}

	public String getTelefonoContacto() {
		return this.telefonoContacto;
	}

	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipoCliente() {
		return this.tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getTipoServicio() {
		return this.tipoServicio;
	}

	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public LocalDateTime getFechaEjecucionPortabilidad() {
		return fechaEjecucionPortabilidad;
	}

	public void setFechaEjecucionPortabilidad(
			LocalDateTime fechaEjecucionPortabilidad) {
		this.fechaEjecucionPortabilidad = fechaEjecucionPortabilidad;
	}

	public List<NumeroSolicitud> getNumeracion() {
		return numeracion;
	}

	public void setNumeracion(List<NumeroSolicitud> numeracion) {
		this.numeracion = numeracion;
	}

	public String getCodigoMensaje() {
		return codigoMensaje;
	}

	public void setCodigoMensaje(String codigoMensaje) {
		this.codigoMensaje = codigoMensaje;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	
	

}