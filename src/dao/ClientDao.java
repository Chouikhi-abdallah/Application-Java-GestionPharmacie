package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import modeles.Client;

public class ClientDao {

    private final Connection connection; //pour avoir une connection aux base de donnes 

    public ClientDao(Connection connection) {
        this.connection = connection;
    }
// pour ajouter un client
    public void addClient(Client client) throws SQLException {
        String query = "INSERT INTO Clients(nom_client, adresse_client, telephone_client, credit_client) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, client.getNom_client());
            statement.setString(2, client.getAdresse_client());
            statement.setString(3, client.getTelephone_client());
            statement.setFloat(4, client.getCredit_client());
            statement.executeUpdate();
        }
    }
// pour modifier un client
    public void updateClient(Client client) throws SQLException {
        String query = "UPDATE Clients SET nom_client=?, adresse_client=?, telephone_client=?, credit_client=? WHERE id_client=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, client.getNom_client());
            statement.setString(2, client.getAdresse_client());
            statement.setString(3, client.getTelephone_client());
            statement.setFloat(4, client.getCredit_client());
            statement.setInt(5, client.getId_client());
            statement.executeUpdate();
        }
    }
//pour supprimer uhn client
    public void deleteClient(int id) throws SQLException {
        String query = "DELETE FROM Clients WHERE id_client=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1,id);
           
            statement.executeUpdate();
            statement.close();
        }
    }
//pour chercher un client par id
    public Client getClientById(int id) throws SQLException {
        String query = "SELECT * FROM Clients WHERE id_client=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Client(resultSet.getInt("id_client"), resultSet.getString("nom_client"),
                            resultSet.getString("adresse_client"), resultSet.getString("telephone_client"),
                            resultSet.getFloat("credit_client"));
                } else {
                    return null;
                }
            }
        }
    }
//pour retourner tous les clients dans une liste 
    public List<Client> getAllClients() throws SQLException {
        String query = "SELECT * FROM Clients";
        List<Client> clients = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                clients.add(new Client(resultSet.getInt("id_client"), resultSet.getString("nom_client"),
                        resultSet.getString("adresse_client"), resultSet.getString("telephone_client"),
                        resultSet.getFloat("credit_client")));
            }
        }
        return clients;
    }
    public Client getClientByNom(String name) throws SQLException{
    	String query="select * from Clients where nom_client=?";
    	try (PreparedStatement statement=connection.prepareStatement(query)){
    		statement.setString(1, name);
    		try(ResultSet resultSet=statement.executeQuery()){
    			if(resultSet.next()) {
    				return new Client(resultSet.getInt("id_client"), resultSet.getString("nom_client"),
                            resultSet.getString("adresse_client"), resultSet.getString("telephone_client"),
                            resultSet.getFloat("credit_client"));
    			}
    			else return null;
    		}
    	}
    	
    }
    public boolean deleteClient(Client client) {
        boolean success = false;
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM clients WHERE id_client=?");
            ps.setInt(1, client.getId_client());
            int deletedRows = ps.executeUpdate();
            if (deletedRows > 0) {
                success = true;
                PreparedStatement ps2 = connection.prepareStatement("DELETE FROM ordonnances WHERE id_client=?");
                ps2.setInt(1, client.getId_client());
                ps2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }
   
    public void supprimerOrdonnancesClient(int idClient) throws SQLException {
        String sql = "DELETE FROM ordonnances WHERE id_client = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idClient);
            stmt.executeUpdate();
        }
    }
    public void supprimerClientEtOrdonnances(int idClient) {
        try {
            // Supprimer toutes les ordonnances du client
            supprimerOrdonnancesClient(idClient);
            // Supprimer le client
            String sql = "DELETE FROM clients WHERE id_client = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, idClient);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    
     
}
