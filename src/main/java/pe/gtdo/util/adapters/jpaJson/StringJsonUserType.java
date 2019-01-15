package pe.gtdo.util.adapters.jpaJson;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;
import org.json.JSONObject;


public class StringJsonUserType implements UserType {

	@Override
	public Object assemble(Serializable arg0, Object arg1)
			throws HibernateException {
		// TODO Auto-generated method stub
		return this.deepCopy( arg0);
	}

	@Override
	public Object deepCopy(Object arg0) throws HibernateException {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public Serializable disassemble(Object arg0) throws HibernateException {
		// TODO Auto-generated method stub
		return (String)this.deepCopy( arg0);
	}

	@Override
	public boolean equals(Object arg0, Object arg1) throws HibernateException {
		// TODO Auto-generated method stub
		if(arg0== null){

            return arg0== null;
        }

        return arg0.equals( arg1);
	}

	@Override
	public int hashCode(Object arg0) throws HibernateException {
		// TODO Auto-generated method stub
		return arg0.hashCode();
	}

	@Override
	public boolean isMutable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object nullSafeGet(ResultSet arg0, String[] arg1,
			SessionImplementor arg2, Object arg3) throws HibernateException,
			SQLException {
		
		// TODO Auto-generated method stub
		
		//String o= arg0.getObject(arg1[0]).toString();
		
		 if (arg0.getObject(arg1[0]) != null) {
	            return  new JSONObject(arg0.getObject(arg1[0]).toString());
	      }
		/*
		if(arg0.getObject(arg1[0]) == null){
            return null;
        }*/
        return new JSONObject(); 
		
		
	}

	@Override
	public void nullSafeSet(PreparedStatement arg0, Object arg1, int arg2,
			SessionImplementor arg3) throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		
	    if (arg1 == null) {
	    	arg0.setNull(arg2, Types.OTHER);
            return;
        }

	    arg0.setObject(arg2, arg1, Types.OTHER);
		
		
		
	}

	@Override
	public Object replace(Object arg0, Object arg1, Object arg2)
			throws HibernateException {
		// TODO Auto-generated method stub
		 return arg0;
	}

	@Override
	public Class returnedClass() {
		// TODO Auto-generated method stub
		 return String.class;
	}

	@Override
	public int[] sqlTypes() {
		// TODO Auto-generated method stub
		 return new int[] { Types.JAVA_OBJECT};
	}

   
}