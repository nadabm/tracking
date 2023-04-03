package ma.fstt.model;

import java.sql.SQLException;

public class Admin {
    private String username ;
    private String password ;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


   public boolean authenticate() throws SQLException {
        AdminDAO adminDAO = new AdminDAO();
        return adminDAO.authenticate(username, password);
    }
}