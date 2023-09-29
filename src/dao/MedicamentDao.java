package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modeles.Medicament;

public class MedicamentDao {

    private Connection conn;

    public MedicamentDao(Connection conn) {
        this.conn = conn;
    }

    public void ajouterMedicament(Medicament medicament) throws SQLException {
        String sql = "INSERT INTO Medicaments (nom_medicament, description_medicament, stock_medicament,prix_medicament) VALUES (?, ?, ?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, medicament.getNom_medicament());
        pstmt.setString(2, medicament.getDescription_medicament());
        pstmt.setString(3, medicament.getStock_medicament());
        pstmt.setDouble(4, medicament.getPrix_medicament());
        pstmt.executeUpdate();
    }

    public void modifierMedicament(Medicament medicament) throws SQLException {
        String sql = "UPDATE Medicaments SET nom_medicament = ?, description_medicament = ?, stock_medicament = ? WHERE id_medicament = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, medicament.getNom_medicament());
        pstmt.setString(2, medicament.getDescription_medicament());
        pstmt.setString(3, medicament.getStock_medicament());
        pstmt.setInt(4, medicament.getId_medicament());
        pstmt.executeUpdate();
    }

    public void supprimerMedicament(int id) throws SQLException {
        String sql = "DELETE FROM Medicaments WHERE id_medicament = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }

    public List<Medicament> listerMedicaments() throws SQLException {
        List<Medicament> medicaments = new ArrayList<>();
        String sql = "SELECT * FROM Medicaments";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Medicament medicament = new Medicament(rs.getInt("id_medicament"), rs.getString("nom_medicament"), rs.getString("description_medicament"), rs.getString("stock_medicament"),rs.getDouble("prix_medicament"));
            medicaments.add(medicament);
        }
        return medicaments;
    }

    public Medicament trouverMedicamentParId(int id) throws SQLException {
        String sql = "SELECT * FROM Medicaments WHERE id_medicament = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            Medicament medicament = new Medicament(rs.getInt("id_medicament"), rs.getString("nom_medicament"), rs.getString("description_medicament"), rs.getString("stock_medicament"),rs.getDouble("prix_medicament"));
            return medicament;
        } else {
            return null;
        }
    }
}

