package tp2_poo2.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloController {
    private AppareilDAO appareilDAO = new AppareilDAO();

    @FXML private ComboBox<String> typeComboBox;
    @FXML private TextField serialField;
    @FXML private Label resultLabel;

    @FXML
    public void initialize() {
        // Remplir la ComboBox avec les types d'appareils
        typeComboBox.getItems().addAll(
                "Téléphone portable",
                "Ordinateur portable",
                "Tablette",
                "Appareil photo",
                "Autre"
        );
    }

    @FXML
    private void handleSearch() {
        String numeroSerie = serialField.getText().trim();

        if (numeroSerie.isEmpty()) {
            showResult("Veuillez entrer un numéro de série", "red");
            return;
        }

        Appareil appareil = appareilDAO.rechercherParNumeroSerie(numeroSerie);

        if (appareil != null) {
            String message = String.format("ATTENTION ! Cet appareil est déclaré volé.\n\n"
                            + "Type: %s\n"
                            + "Propriétaire: %s\n"
                            + "Contact: %s",
                    appareil.getType(),
                    appareil.getProprietaire(),
                    appareil.getContactProprietaire());

            showResult(message, "red");
        } else {
            showResult("Aucune déclaration de vol trouvée pour ce numéro de série.", "green");
        }
    }
    @FXML
    private void handleReport() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("declaration.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Déclarer un vol");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(serialField.getScene().getWindow());

            DeclarationController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            showResult("Erreur lors de l'ouverture du formulaire", "red");
        }
    }

    private void showResult(String message, String color) {
        resultLabel.setText(message);
        resultLabel.setStyle("-fx-text-fill: " + color + "; -fx-font-weight: bold;");
    }
}


