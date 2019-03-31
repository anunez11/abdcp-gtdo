package pe.gtdo.handler;

import java.io.ByteArrayOutputStream;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import pe.gtdo.core.logging.AbdcpLogger;

import javax.xml.ws.handler.soap.SOAPMessageContext;


@Stateless
public class LoggingHandler implements SOAPHandler<SOAPMessageContext> {
	 
	
	 @Inject
	 AbdcpLogger LOG;
	
	 @Override
	 public Set<QName> getHeaders() {
	 return null;
	 }
	 
	 @Override
	 public void close(MessageContext context) {
	 }
	 
	 @Override
	 public boolean handleFault(SOAPMessageContext context) {
	  logToSystemOut(context);
	  return true;
	 }
	 
	 @Override
	 public boolean handleMessage(SOAPMessageContext context) {
	 logToSystemOut(context);
	  return true;
	 }
	 
	 private void logToSystemOut(SOAPMessageContext context) {
		 
		 Boolean esSalida = (Boolean) context
					.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				SOAPMessage soapMessage = context.getMessage();
				soapMessage.writeTo(baos);
				String a= esSalida ? "OUT" : "IN";
				LOG.info(a+" ==> "+baos.toString());
				//LOG.info("{}=[{}]", esSalida ? "OUT" : "IN",baos.toString());
				
			} catch (Exception ex) {
				LOG.error(" Exception Handler  "+ex);
			}

			
		 
	
	  }

	
	
}
