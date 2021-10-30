package model;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceManager {
  private static DataSourceManager instance = new DataSourceManager();
  private final Properties prop;
  private ComboPooledDataSource comboPooledDataSource;

  public static DataSourceManager getInstance() {

    if (instance == null) {
      instance = new DataSourceManager();
    }

    return instance;
  }

  private DataSourceManager() {
    prop = new Properties();

    try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
      prop.load(input);
    } catch (IOException e) {
      e.printStackTrace();
    }

    comboPooledDataSource = new ComboPooledDataSource();
    comboPooledDataSource.setJdbcUrl(prop.getProperty("db.url"));
    comboPooledDataSource.setUser(prop.getProperty("db.user"));
    comboPooledDataSource.setPassword(prop.getProperty("db.password"));

    comboPooledDataSource.setMinPoolSize(5);
    comboPooledDataSource.setMaxPoolSize(10);
  }

  public Connection getConnection() {
    Connection connection = null;

    try {
      connection = comboPooledDataSource.getConnection();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return connection;
  }
}
