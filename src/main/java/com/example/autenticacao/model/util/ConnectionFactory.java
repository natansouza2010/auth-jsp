package com.example.autenticacao.model.util;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory  implements AutoCloseable{

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static Statement statement;


    public static Connection createConnection() {
        try {

            SQLiteDataSource db = new SQLiteDataSource();
            db.setUrl("jdbc:sqlite:C:/Users/natan/OneDrive/Área de Trabalho/NATAN/5º SEMESTRE/PW3/autenticacao/db_users.db");
            if(connection == null){
                connection = db.getConnection();
            }
        }catch (SQLException e) { e.printStackTrace();}

        return connection;
    }


    public static PreparedStatement createPreparedStatement(String sql) {
        try {preparedStatement = createConnection().prepareStatement(sql);}
        catch (SQLException e) { e.printStackTrace();}
        return preparedStatement;
    }


    public static Statement createStatement(){
        try{
            statement = createConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;

    }


    public void close() throws Exception {

        if(preparedStatement !=null){
            preparedStatement.close();
            if(statement !=null){
                statement.close();
            }
            if(connection !=null){
                connection.close();
            }
        }
    }


}
