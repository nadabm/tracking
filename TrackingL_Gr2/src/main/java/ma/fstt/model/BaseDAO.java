package ma.fstt.model;

import java.sql.*;
import java.util.List;

public abstract  class BaseDAO <T>{

        protected static Connection connection ;
        protected Statement statement ;

        protected PreparedStatement preparedStatement;

        protected ResultSet resultSet ;

        // connexion avec bdd

            protected static String url = "jdbc:mysql://127.0.0.1:3306/glovo";
            protected static String login = "root";
            protected static String password = "";



    BaseDAO() throws SQLException {
        this.connection = DriverManager.getConnection(url , login ,password );
    }

    public static Connection getConnection() throws SQLException{
     connection = DriverManager.getConnection(url , login ,password);

        return connection ;}

    public static void closeConnectionAndStatement() {
    }


    public abstract void save( T object ) throws SQLException;

    public abstract void update( T object ) throws SQLException ;

    public abstract void delete( T object ) throws SQLException ;


    public abstract List<T> getAll(  ) throws SQLException ;


    public abstract T getOne( Long id  ) throws SQLException  ;





}
