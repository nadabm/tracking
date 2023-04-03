package ma.fstt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class produitDAO  extends BaseDAO<produit>{
    public produitDAO() throws SQLException {

        super();
    }

    @Override
    public void save(produit object) throws SQLException {

        String request = "insert into produit (nom , prix, descreption) values (? , ? , ?)";

        // mapping objet table

        this.preparedStatement = connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1 , object.getNom());

        this.preparedStatement.setString(2 , object.getPrix());
        this.preparedStatement.setString(3, object.getDescreption());


        this.preparedStatement.execute();
    }

    @Override
    public void update(produit object) throws SQLException {
        String request = "UPDATE produit SET nom=?, prix=?, descreption=? WHERE id_produit=?";

        this.preparedStatement = connection.prepareStatement(request);
        this.preparedStatement.setString(1, object.getNom());
        this.preparedStatement.setString(2, object.getPrix());
        this.preparedStatement.setString(3, object.getDescreption());
        this.preparedStatement.setLong(4, object.getId_produit());

        this.preparedStatement.executeUpdate();
    }


    @Override
    public void delete(produit object) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("DELETE FROM produit WHERE id_produit = ?");
            statement.setLong(1, object.getId_produit());
            statement.executeUpdate();
        } finally {
            closeConnectionAndStatement(connection, statement);
        }
    }
    private void closeConnectionAndStatement(Connection connection, PreparedStatement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            // Log the error or throw a custom exception
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // Log the error or throw a custom exception
        }
    }
    @Override
    public List<produit> getAll()  throws SQLException {

        List<produit> mylist = new ArrayList<produit>();

        String request = "select * from produit ";

        this.statement = this.connection.createStatement();

        this.resultSet =   this.statement.executeQuery(request);

// parcours de la table
        while ( this.resultSet.next()){

// mapping table objet
            mylist.add(new produit(this.resultSet.getLong(1) , this.resultSet.getString(2) , this.resultSet.getString(3) , this.resultSet.getString(4)));


        }


        return mylist;
    }

    @Override
    public produit getOne(Long id) throws SQLException {
        return null;
    }
}

