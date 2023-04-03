package ma.fstt.model;

//import jdk.internal.net.http.HttpConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO extends BaseDAO<Admin>{
    public AdminDAO() throws SQLException {

        super();
    }

    public boolean authenticate(String username, String password) throws SQLException {
        String request = "SELECT * FROM admin WHERE username = ? AND password = ?";

        this.preparedStatement = this.connection.prepareStatement(request);
        this.preparedStatement.setString(1 , username);
        this.preparedStatement.setString(2 , password);

        this.resultSet =   this.preparedStatement.executeQuery();

        // If there is a row with the specified username and password, then authentication is successful
        return this.resultSet.next();
    }

    @Override
    public void save(Admin object) throws SQLException {
        // Saving new admin accounts is not implemented in this example
    }

    @Override
    public void update(Admin object) throws SQLException {
        // Updating admin accounts is not implemented in this example
    }

    @Override
    public void delete(Admin object) throws SQLException {
        // Deleting admin accounts is not implemented in this example
    }

    @Override
    public List<Admin> getAll() throws SQLException {
        // Getting all admin accounts is not implemented in this example
        return new ArrayList<Admin>();
    }

    @Override
    public Admin getOne(Long id) throws SQLException {
        // Getting a single admin account is not implemented in this example
        return null;
    }



}