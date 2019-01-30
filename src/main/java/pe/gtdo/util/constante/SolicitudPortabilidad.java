package pe.gtdo.util.constante;

public enum SolicitudPortabilidad {
   
	SP("SP"),
	ANS("ANS"),
	ESC("ESC"),
	OCC("OCC"),
	SAC("SAC"),
	APD("APD"),
	APDC("APDC"),
	RABDCP("RABDCP"),
	SPR("SPR"),
	CPSPR("CPSPR"),
	CNPF("CNPF"),
	PP("PP"),
	FLEP("FLEP"),
	PEP("PEP"),
	NONE("NONE");
	
    private final String value;
       
    
    
	public String getValue() {
		return value;
	}


	private SolicitudPortabilidad(String value) {
	       this.value = value;
	}
	

	  public static SolicitudPortabilidad fromType( String idTipo ){
		  SolicitudPortabilidad[] types = values();
		  SolicitudPortabilidad id = NONE;
	        for ( int i = 0; i < types.length; i++ ){
	            id = types[i];
	            if ( idTipo.equals(id.value))return id;
	        }
	        return id;
	
	  }
	
}
