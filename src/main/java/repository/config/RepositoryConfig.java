package repository.config;


/**
 * Simple repository config class
 * Created by ericreis on 9/27/16.
 */
public final class RepositoryConfig
{
    // JDBC driver name and database URL
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/manutencao-ufrj";

    //  Database credentials
    //public static final String USER = "sqluser";
    //public static final String PASSWORD = "sqluser";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
}
