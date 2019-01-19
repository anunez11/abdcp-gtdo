package pe.gtdo.ws;

import java.io.IOException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

import org.xml.sax.SAXException;

import pe.gtdo.controller.ABDCPController;



@WebService(
		endpointInterface = "pe.gtdo.ws.ABDCPWebServicePortType",
		targetNamespace = "http://ws.inpac.telcordia.com",
		serviceName = "ABDCPWebService",
		portName="ABDCPWebServiceHttpSoap12Endpoint"
	   ,wsdlLocation = "WEB-INF/wsdl/wsdl_abdcp.wsdl"
)
@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING )
@javax.jws.soap.SOAPBinding
(
	    style = javax.jws.soap.SOAPBinding.Style.DOCUMENT,
	    use   = javax.jws.soap.SOAPBinding.Use.LITERAL)
@HandlerChain(file="handler-chain.xml")
public class ABDCPWebService implements  ABDCPWebServicePortType{

	@Inject
	ABDCPController adbcp;
	
	
	@Override
	@WebMethod(operationName="receiveMessage", action="urn:receiveMessage")
	public String receiveMessage(String userID, String password, String xmlMsg,	byte[] attachedDoc) {
		
		// TODO Auto-generated method stub		
		// aca vien la capa de negocio ....
		
			return adbcp.getResultado(userID, password, xmlMsg, attachedDoc);
		
		
		
	}

	
	
    
	

	

}
