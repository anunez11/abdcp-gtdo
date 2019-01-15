package pe.gtdo.util.adapters.jpaJson;

import java.sql.Types;

import org.hibernate.dialect.ProgressDialect;



public class JsonPostgreSQLDialect extends ProgressDialect  {

    public JsonPostgreSQLDialect() {

        super();

        this.registerColumnType(Types.JAVA_OBJECT, "json");
    }
}