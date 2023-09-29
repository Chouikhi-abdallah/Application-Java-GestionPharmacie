package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modeles.Utilisateur;

public class UtilisateursDao {
    private Connection connection;

    public UtilisateursDao(Connection connection) {
        this.connection = connection;
    }

    public Utilisateur getUtilisateurByUsername(String username) throws SQLException {
        Utilisateur utilisateur = null;
        String query = "SELECT * FROM utilisateurs WHERE nom_utilisateur = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id_utilisateur");
                    String password = resultSet.getString("mot_de_passe");
                    String type = resultSet.getString("type_utilisateur");
                    
                    utilisateur = new Utilisateur(id, username, password, type);
                }
            }
        }
        return utilisateur;
    }

    public void addUtilisateur(Utilisateur utilisateur) throws SQLException {
        String query = "INSERT INTO utilisateurs (nom_utilisateur, mot_de_passe, type_utilisateur) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, utilisateur.getNom_utilisateur());
            statement.setString(2, utilisateur.getMot_de_passe());
            statement.setString(3, utilisateur.getType_utilisateur());
            statement.executeUpdate();
        }
    }

    public void updateUtilisateur(Utilisateur utilisateur) throws SQLException {
        String query = "UPDATE utilisateurs SET mot_de_passe = ?, type_utilisateur = ? WHERE id_utilisateur = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, utilisateur.getMot_de_passe());
            statement.setString(2, utilisateur.getType_utilisateur());
            statement.setInt(3, utilisateur.getId_utilisateur());
            statement.executeUpdate();
        }
    }

    public void deleteUtilisateur(int idUtilisateur) throws SQLException {
        String query = "DELETE FROM utilisateurs WHERE id_utilisateur = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idUtilisateur);
            statement.executeUpdate();
        }
    }
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
    /*public static void main(String [] args) throws SQLException {
    	Connection conn =SingletonConnection.getInstance();
    	UtilisateursDao u=new UtilisateursDao(conn);
    	Utilisateur ut=u.getUtilisateurByUsername("admin");
    	System.out.println(ut.getMot_de_passe());
    }*/


