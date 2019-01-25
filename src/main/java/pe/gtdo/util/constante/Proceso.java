package pe.gtdo.util.constante;

public enum Proceso {
	SP("00"), //SIN PROCESO
	CP("05"), // CONSULTA PREVIA
	SPP("01"), // SOLICITUD DE PORTABILIDAD
	RP("02"), // RETORNO DE PORTABILIDAD
	DE("04"); // DETECCION DE ERROR

	
	private final String value;
	
	
	
	
	public String getValue() {
		return value;
	}


	private Proceso(String value) {
	       this.value = value;
	}
	

	  public static Proceso fromType( String idTipo ){
		  Proceso[] types = values();
		  Proceso id = null;
	        for ( int i = 0; i < types.length; i++ ){
	            id = types[i];
	            if ( idTipo.equals(id.value))return id;
	        }
	        return id;
	    }	 

	
	
}
