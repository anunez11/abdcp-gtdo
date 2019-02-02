package pe.gtdo.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.json.JSONObject;


import org.xml.sax.SAXException;
import org.w3c.dom.Document;


@ApplicationScoped
public class Utilitario {
    	


	private String XSD_FILE="C:\\Users\\Angel A\\Documents\\WS\\abdcp-gtdo\\src\\main\\webapp\\WEB-INF\\xsd\\elementoMsg.xsd";
//	private String XSD_FILE="D:\\ANGEL\\PROYECTOS\\DESARROLLO\\JAVA_CFE\\abdcp-gtdo\\src\\main\\webapp\\WEB-INF\\xsd\\elementoMsg.xsd";
	
	public  Document stringToXml(String xmlString) throws ParserConfigurationException, SAXException, IOException{
	
		
	     DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	     DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	     InputStream inputStream = new    ByteArrayInputStream(xmlString.getBytes("UTF-8"));	     
	     return docBuilder.parse(inputStream);
	}
	
	
	public <T> T converXmlToObject(Class clase,String xmlString) throws JAXBException, UnsupportedEncodingException{
		 InputStream inputStream = new  ByteArrayInputStream(xmlString.getBytes("UTF-8"));	  
		JAXBContext jaxbContext = JAXBContext.newInstance(clase);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		
		return (T) jaxbUnmarshaller.unmarshal(inputStream);
		
		
	}

	public <T>  String converObjectToXmlString(T t) throws Exception{
		
		 JAXBContext jaxbContext = JAXBContext.newInstance(t.getClass());
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(t, sw);
		return sw.toString();
		
	}
	
	
	
	
	public Boolean validarMsg(String xmslMsg){
		 System.out.println("mensaje: "+xmslMsg);
	     try {
	    	 
	    	
	            SchemaFactory factory = 
	                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	            Schema schema = factory.newSchema(new File(XSD_FILE));
	            Validator validator = schema.newValidator();
	            validator.validate(new StreamSource(new ByteArrayInputStream(xmslMsg.getBytes("UTF-8")) ));
	        } catch (IOException | SAXException e) {
                e.printStackTrace();
	        	
	            return false;
	        }
	        return true;
	}
	

	public  <T> T  setObject(T objetoASetear, Object  data ) throws Exception{		
		 Class<?> clase= data.getClass();
		 Method[] methods =clase.getMethods();
		 for (Method m : methods){			 
			 if(m.getName().startsWith("set"))  				 
				 setMetodoClase(objetoASetear,m.getName(),getMetodoClase(data,"get"+m.getName().substring(3))) ;
		 }
		 return objetoASetear;
	}	
		
	public <T> T  setMetodoClase(T t,String metodo, Object valor) throws Exception{
		if(valor==null) return t;
		Method m =null;
		try{
			m=t.getClass().getMethod(metodo,valor.getClass());	
		}catch(Exception e){}
	    
		if(m!=null){
			if(m.getParameterCount()>0){             
				if (m.getParameterTypes()[0].equals(valor.getClass())){
					m.invoke(t, valor);
					return t;
				} 
			}		
		}
		return t;
	}	
	public <T> Object  getMetodoClase(T t,String metodo) throws Exception{
		Class<?> clase = t.getClass();		
		Method m = clase.getMethod(metodo);
		if(m==null) return null;
		return m.invoke(t);	
	}	
	public <T> Method  getMetodoClaseParametroHistorico(T t,Object paranetro) throws Exception{
		Class<?> clase = t.getClass();		
		 Method[] methods =clase.getMethods();
		 for (Method m : methods){	
			 if(m.getName().startsWith("set")){ 
				 if ( m.getParameterTypes()[0].equals(paranetro.getClass())) return m; 
			 } 
		 }
		return null;
	}
	public  Map<String,Object> convertJsonToMap(JSONObject data){
		return data.toMap();
	}
	public JSONObject convertMapToJson( Map<String,Object> data){
		return new JSONObject(data);
	}
	

	public String converDocumentToString(Document doc){
		
		
		
		    String data=null;
		    TransformerFactory tf = TransformerFactory.newInstance();
		    try {
	            Transformer transformer = tf.newTransformer();
	            // below code to remove XML declaration
	          //   transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	            StringWriter writer = new StringWriter();
	            transformer.transform(new DOMSource(doc), new StreamResult(writer));
	            String output = writer.getBuffer().toString();
	            data= output;
	          
	            
	            
	        } catch (TransformerException e) {
	            e.printStackTrace();
	           
	        }
		    return data;
		
	}
	
	
	
}
