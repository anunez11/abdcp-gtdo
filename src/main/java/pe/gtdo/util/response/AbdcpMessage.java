package pe.gtdo.util.response;

public enum AbdcpMessage {
	TIPO_NOTIFICACION_ENCONTRADO("Tipo de notificación encontrado",200),

	SUJETO_CREADA("Sujeto creado", 200),
	SUJETO_ACTUALIZADA("Sujeto actualizado", 200),
	SUJETO_ELIMINADO("Sujeto Eliminado", 200),
	SUJETO_YA_REGISTRADO("Ya esta registrado en el caso",500);
	
	String message;
	int code;
	private AbdcpMessage(String message, int code) {
		this.message = message;
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public int getCode() {
		return code;
	}
}
