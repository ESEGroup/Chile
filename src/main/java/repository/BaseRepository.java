package repository;


import repository.config.RepositoryConfig;
import repository.util.NamedParameterStatement;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * simple base repository
 * Created by ericreis on 9/27/16.
 */
public class BaseRepository
{
    protected Connection conn;
    protected Statement stmt;
    protected NamedParameterStatement namedStmt;

    public BaseRepository()
    {
        try
        {
            Class.forName(RepositoryConfig.JDBC_DRIVER);

            this.conn = DriverManager.getConnection(RepositoryConfig.DB_URL,
                                                    RepositoryConfig.USER,
                                                    RepositoryConfig.PASSWORD);

            this.stmt = this.conn.createStatement();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    protected void createNamedParameterStatement(String query, HashMap<String, Object> params)
    {
        try
        {
            this.namedStmt = new NamedParameterStatement(this.conn, query);

            for (Map.Entry<String, Object> param : params.entrySet())
            {
                this.namedStmt.setObject(param.getKey(), param.getValue());
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable
    {
        this.conn.close();
        this.stmt.close();

        super.finalize();
    }
}