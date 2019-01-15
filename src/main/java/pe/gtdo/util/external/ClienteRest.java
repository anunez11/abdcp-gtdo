package pe.gtdo.util.external;



import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

//import pe.gob.mpfn.cfe.exception.CfeException;

import pe.gtdo.exception.AbdcpException;





public class ClienteRest {

	private String token="";
	private String url="";
	private MediaType mediaTypeRespuesta=MediaType.APPLICATION_JSON_TYPE;
	public ClienteRest(String url ,String token ) {
		super();
		this.token = token;
		this.url = url;
	}
	
	public ClienteRest(String url) {
		super();
		this.url = url;
	}
	private static final int POST = 1;
	private static final int GET = 2;
	private static final int PUT = 3;
	private static final int DELETE = 4;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public MediaType getMediaTypeRespuesta() {
		return mediaTypeRespuesta;
	}
	
	public void setMediaTypeRespuesta(MediaType mediaTypeRespuesta) {
		this.mediaTypeRespuesta = mediaTypeRespuesta;
	}
	
	public <T> T senRequestGet(GenericType<T> respuesta) throws Exception{		
		return senRequest(GET, null,respuesta);		
	}
   
	public <T> T senRequestPost(Entity dataEnviar,GenericType<T> respuesta) throws Exception{		
    	return senRequest(POST, dataEnviar,respuesta);
	}
    
    public <T> T senRequestPut(Entity dataEnviar,GenericType<T> respuesta) throws Exception{
    	return senRequest(PUT, dataEnviar,respuesta);
    	
	}    
    
    public <T> T senRequestDelete(Entity dataEnviar,GenericType<T> respuesta) throws Exception{
    	return senRequest(DELETE, dataEnviar,respuesta);
    	
 	}
   
    private <T> T senRequest(int metodo,Entity dataEnviar ,GenericType<T> respuesta ) throws Exception{
		
    	try{
    		Client client = ClientBuilder.newClient();
	  		WebTarget rs = client.target(this.url);
	  		Builder request = rs.request().header("Authorization",token).accept(mediaTypeRespuesta);
	  		T resultado=null;
	    	switch(metodo){
	    	
		    	case POST: resultado=request.post(dataEnviar).readEntity(respuesta);
		    	break;
		    	
		    	case GET: resultado= request.get().readEntity(respuesta);
		        break;
		        
		    	case PUT: resultado= request.put(dataEnviar).readEntity(respuesta);
		        break;
		        
		    	case DELETE:  if(dataEnviar==null) resultado=request.delete().readEntity(respuesta);
		    	              else resultado=request
		    	                   .build("DELETE", dataEnviar)
  		  					       .invoke()
  		  					       .readEntity(respuesta);
		        break;
	    	
	    	}
	    	
	    	return resultado;
	    	
		}catch(Exception e){
    	  e.printStackTrace();
			 throw new AbdcpException(Status.INTERNAL_SERVER_ERROR,"Hubo un problema con la peticion ");
		}
    	
   	}
	
}
