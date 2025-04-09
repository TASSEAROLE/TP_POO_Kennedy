package tp2_poo2.demo;

import java.sql.*;

public class AppareilDAO {

    // Création de la table (MySQL syntax)
    public void creerTable() {
        String sql = "CREATE TABLE IF NOT EXISTS appareils_voles ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "type VARCHAR(255) NOT NULL,"
                + "numero_serie VARCHAR(255) UNIQUE NOT NULL,"
                + "proprietaire VARCHAR(255) NOT NULL,"
                + "contact VARCHAR(255) NOT NULL)";

        try (Connection conn = ConnectionManager.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Table créée ou déjà existante !");

        } catch (SQLException e) {
            System.err.println("Erreur création table: " + e.getMessage());
        }
    }

    // Recherche par numéro de série
    public Appareil rechercherParNumeroSerie(String numeroSerie) {
        String sql = "SELECT * FROM appareils_voles WHERE numero_serie = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, numeroSerie);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Appareil(
                        rs.getString("type"),
                        rs.getString("numero_serie"),
                        rs.getString("proprietaire"),
                        rs.getString("contact")
                );
            }

        } catch (SQLException e) {
            System.err.println("Erreur recherche: " + e.getMessage());
        }
        return null;
    }

    // Déclarer un vol
    public boolean declarerVol(Appareil appareil) {
        String sql = "INSERT INTO appareils_voles (type, numero_serie, proprietaire, contact) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, appareil.getType());
            stmt.setString(2, appareil.getNumeroSerie());
            stmt.setString(3, appareil.getProprietaire());
            stmt.setString(4, appareil.getContactProprietaire());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Erreur déclaration: " + e.getMessage());
            return false;
        }
    }
}