package pe.gtdo.util.constante;

public enum Retorno {
     
	
	SR("SR"),	
	AR("AR") ,
	DR("DR") ,	
	NONE("NONE");
	
	private final String value;	
	
	
	
	
	
	public String getValue() {
		return value;
	}


	private Retorno(String value) {
	       this.value = value;
	}
	

	  public static Retorno fromType( String idTipo ){
		  Retorno[] types = values();
		  Retorno id = NONE;
	        for ( int i = 0; i < types.length; i++ ){
	            id = types[i];
	            if ( idTipo.equals(id.value))return id;
	        }
	        return id;
	    }	 

	
	
}
