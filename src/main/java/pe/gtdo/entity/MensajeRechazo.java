package  pe.gtdo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import pe.gtdo.entitymanager.adapter.ListStringUserType;
import pe.gtdo.entitymanager.adapter.StringMapUserType;

@Entity
@Table(name="mensaje_rechazo")
@NamedQuery(name="MensajeRechazo.findAll", query="SELECT t FROM MensajeRechazo t")
@TypeDefs({ @TypeDef(name = "ListStringUserType", typeClass = ListStringUserType.class) })
public class MensajeRechazo {
     
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_mensaje_rechazo")
	private Long idMensajeRechazo;

	private String codigo;

	private String descripcion;
	
	private String proceso;
	
	
	@Column(name="tipo_mensaje")
	private String tipoMensaje;
	
	@Column(name="mensaje_rechazado")
	@Type(type = "ListStringUserType")
	private List<String> mensajeRechazado;
	
	
	@Column(name="participante_rechaza")	
	private String participanteRechaza;

	@Column(name="es_activo")
	private Boolean esActivo;

	public Long getIdMensajeRechazo() {
		return idMensajeRechazo;
	}

	public void setIdMensajeRechazo(Long idMensajeRechazo) {
		this.idMensajeRechazo = idMensajeRechazo;
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

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}





	public List<String> getMensajeRechazado() {
		return mensajeRechazado;
	}

	public void setMensajeRechazado(List<String> mensajeRechazado) {
		this.mensajeRechazado = mensajeRechazado;
	}

	public String getParticipanteRechaza() {
		return participanteRechaza;
	}

	public void setParticipanteRechaza(String participanteRechaza) {
		this.participanteRechaza = participanteRechaza;
	}

	public Boolean getEsActivo() {
		return esActivo;
	}

	public void setEsActivo(Boolean esActivo) {
		this.esActivo = esActivo;
	}

	public String getTipoMensaje() {
		return tipoMensaje;
	}

	public void setTipoMensaje(String tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}

	
	
	
	
	
	
	
	
	
}
