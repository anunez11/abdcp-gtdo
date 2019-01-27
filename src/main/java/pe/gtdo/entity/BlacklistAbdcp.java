package  pe.gtdo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="blacklist_abdcp")
@NamedQuery(name="BlacklistAbdcp.findAll", query="SELECT t FROM BlacklistAbdcp t")
public class BlacklistAbdcp {
     
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_blacklist_abdcp")
	private Long idBlacklistAbdcp;

	@Column(name="mensaje_recibido")
	private String mensajeRecibido;

	@Column(name="mensaje_enviado")
	private String mensajeEnviado;
	
	@Column(name="codigo_rechazo")
	private String codigoRechazo;
	
	private String numero;

	@Column(name="es_activo")
	private Boolean esActivo;


	public Long getIdBlacklistAbdcp() {
		return idBlacklistAbdcp;
	}

	public void setIdBlacklistAbdcp(Long idBlacklistAbdcp) {
		this.idBlacklistAbdcp = idBlacklistAbdcp;
	}

	public String getMensajeRecibido() {
		return mensajeRecibido;
	}

	public void setMensajeRecibido(String mensajeRecibido) {
		this.mensajeRecibido = mensajeRecibido;
	}

	public String getMensajeEnviado() {
		return mensajeEnviado;
	}

	public void setMensajeEnviado(String mensajeEnviado) {
		this.mensajeEnviado = mensajeEnviado;
	}

	public String getCodigoRechazo() {
		return codigoRechazo;
	}

	public void setCodigoRechazo(String codigoRechazo) {
		this.codigoRechazo = codigoRechazo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getEsActivo() {
		return esActivo;
	}

	public void setEsActivo(Boolean esActivo) {
		this.esActivo = esActivo;
	}
	
	
	
	
	
	
}
