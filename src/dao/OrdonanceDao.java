package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import modeles.Ordonance;

public class OrdonanceDao {
    
    private final Connection connection;
    
    public OrdonanceDao(Connection connection) {
        this.connection = connection;
    }

    public List<Ordonance> getAll() throws SQLException {
        List<Ordonance> ordonnances = new ArrayList<>();
        String query = "SELECT * FROM Ordonnances";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id_ordonnance");
                    int idClient = resultSet.getInt("id_client");
                    int idMedicament = resultSet.getInt("id_medicament");
                    Date date = resultSet.getDate("date_ordonnance");
                    int quantite = resultSet.getInt("quantite");
                    Ordonance ordonnance = new Ordonance(id, idClient, idMedicament, date, quantite);
                    ordonnances.add(ordonnance);
                }
            }
        }
        return ordonnances;
    }

    public Ordonance getOrdById(int id) throws SQLException {
        String query = "SELECT * FROM Ordonnances WHERE id_ordonnance=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idClient = resultSet.getInt("id_client");
                    int idMedicament = resultSet.getInt("id_medicament");
                    Date date = resultSet.getDate("date_ordonnance");
                    int quantite = resultSet.getInt("quantite");
                    return new Ordonance(id, idClient, idMedicament, date, quantite);
                } else {
                    return null;
                }
            }
        }
    }

    public void add_ord(Ordonance ordonnance) throws SQLException {
        String query = "INSERT INTO Ordonnances (id_client, id_medicament, date_ordonnance, quantite) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, ordonnance.getId_client());
            statement.setInt(2, ordonnance.getId_medicament());
            statement.setDate(3, new java.sql.Date(ordonnance.getDate_ordonnance().getTime()));
            statement.setInt(4, ordonnance.getQuantite());
            statement.executeUpdate();
            statement.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void update_ord(Ordonance ordonnance) throws SQLException {
        String query = "UPDATE Ordonnances SET id_client=?, id_medicament=?, date_ordonnance=?, quantite=? WHERE id_ordonnance=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ordonnance.getId_client());
            statement.setInt(2, ordonnance.getId_medicament());
            statement.setDate(3, new java.sql.Date(ordonnance.getDate_ordonnance().getTime()));
            statement.setInt(4, ordonnance.getQuantite());
            statement.setInt(5, ordonnance.getId_ordonnance());
            statement.executeUpdate();
        }
    }

    public void delete_ord(int id) throws SQLException {
        String query = "DELETE FROM Ordonnances WHERE id_ordonnance=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean Ord_exit_non(int id) throws SQLException{
    	String sql = "SELECT COUNT(*) FROM Ordonnances WHERE id_ordonnance = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        }
    }
    return false;
    	
    	
    }
}

