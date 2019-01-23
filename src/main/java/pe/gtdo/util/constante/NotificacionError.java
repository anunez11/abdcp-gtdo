package pe.gtdo.util.constante;

public enum NotificacionError {

	NI("NI"),
	NE("NE"),
	NONE("NONE");
	
    private final String value;	
	private NotificacionError(String value) {
	       this.value = value;
	}
	

	  public static NotificacionError fromType( String idTipo ){
		  NotificacionError[] types = values();
		  NotificacionError id = NONE;
	        for ( int i = 0; i < types.length; i++ ){
	            id = types[i];
	            if ( idTipo.equals(id.value))return id;
	        }
	        return id;
	    }	 
}
