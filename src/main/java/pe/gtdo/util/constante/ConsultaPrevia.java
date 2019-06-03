package pe.gtdo.util.constante;

public enum ConsultaPrevia {
	
	CPOCC("CPOCC"),	
	ANCP("ANCP") ,
	CPAC("CPAC") ,
	CPRABD("CPRABD"),
	
	CP("CP") ,
	ECPC("ECPC"), 
	CPPR("CPPR"),
	NONE("NONE");
	
	private final String value;	
	
	
	
	
	
	public String getValue() {
		return value;
	}


	private ConsultaPrevia(String value) {
	       this.value = value;
	}
	

	  public static ConsultaPrevia fromType( String idTipo ){
		  ConsultaPrevia[] types = values();
		  ConsultaPrevia id = NONE;
	        for ( int i = 0; i < types.length; i++ ){
	            id = types[i];
	            if ( idTipo.equals(id.value))return id;
	        }
	        return id;
	    }	 

	
	
	
}
