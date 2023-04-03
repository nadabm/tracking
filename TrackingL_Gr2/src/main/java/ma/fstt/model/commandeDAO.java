package ma.fstt.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class commandeDAO extends BaseDAO<commande>{

    public commandeDAO() throws SQLException {
        super();
    }

    @Override
    public void save(commande object) throws SQLException {

        String request = "INSERT INTO commande (id_produit, id_livreur, date, km) VALUES (?, ?, ?, ?)";

        this.preparedStatement = this.connection.prepareStatement(request);
        this.preparedStatement.setLong(1, object.getProduits().get(0).getId_produit()); // assuming you only have one product in the list
        this.preparedStatement.setLong(2, object.getLivreur().getId_livreur());
        this.preparedStatement.setString(3, object.getDate());
        this.preparedStatement.setDouble(4, object.getKm());

        this.preparedStatement.executeUpdate();
    }


    @Override
    public void update(commande object) throws SQLException {

    }

    @Override
    public void delete(commande object) throws SQLException {

    }


    @Override
    public List<commande> getAll() throws SQLException {
        List<commande> commandes = new ArrayList<>();

        // Establish database connection
        Connection conn = BaseDAO.getConnection();

        // Create and execute the SQL statement
        String sql = "SELECT c.id_commande, c.date, c.km, p.id_produit, l.id_livreur " +
                "FROM commande c " +
                "JOIN produit p ON c.id_produit = p.id_produit " +
                "JOIN livreur l ON c.id_livreur = l.id_livreur";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        // Loop through the result set and create commande objects
        while (rs.next()) {
            commande commande = new commande();
            commande.setId_commande(rs.getLong("id_commande"));
            Livreur livreur = new Livreur();
            livreur.setId_livreur(rs.getLong("id_livreur"));
            commande.setLivreur(livreur);
            List<produit> produits = new ArrayList<>();
            produit Produit = new produit();
            Produit.setId_produit(rs.getLong("id_produit"));
            produits.add(Produit);
            commande.setProduits(produits);
            commande.setDate(rs.getString("date"));
            commande.setKm(rs.getDouble("km"));
            commandes.add(commande);
        }

        // Close all connections
        rs.close();
        stmt.close();
        conn.close();

        return commandes;
    }






    @Override
    public commande getOne(Long id) throws SQLException {
        return null;
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
}
