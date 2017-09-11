import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.dbcp.managed.BasicManagedDataSource;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class jdbcDemo1 {

    private Map<Runnable, Object> map = new HashMap<Runnable, Object>();

    //
    ThreadLocal t1 = new ThreadLocal();


    public void setObject(Object object){
        InputStream in = jdbcDemo1.class.getClassLoader().getResourceAsStream("");
        Properties properties = new Properties();
        try {
            properties.load(in);
            DataSource source = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}


