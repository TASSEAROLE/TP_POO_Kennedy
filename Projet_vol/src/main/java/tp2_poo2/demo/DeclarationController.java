package tp2_poo2.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeclarationController {
    private AppareilDAO dao = new AppareilDAO();
    private Stage dialogStage;

    @FXML private ComboBox<String> typeComboBox;
    @FXML private TextField serialField;
    @FXML private TextField proprietaireField;
    @FXML private TextField contactField;
    @FXML private Label errorLabel;

    public void setDialogStage(Stage stage) {
        this.dialogStage = stage;
    }

    @FXML
    public void initialize() {
        typeComboBox.getItems().addAll(
                "Téléphone portable",
                "Ordinateur portable",
                "Tablette",
                "Appareil photo",
                "Autre"
        );
    }

    @FXML
    private void handleSubmit() {
        if (validateForm()) {
            String numeroSerie = serialField.getText().trim();

            // Vérifie si le numéro de série existe déjà
            if (dao.rechercherParNumeroSerie(numeroSerie) != null) {
                errorLabel.setText("Ce numéro de série a déjà été déclaré volé.");
                return;
            }

            Appareil appareil = new Appareil(
                    typeComboBox.getValue(),
                    numeroSerie,
                    proprietaireField.getText().trim(),
                    contactField.getText().trim()
            );

            boolean success = dao.declarerVol(appareil);
            if (success) {
                showSuccessAlert("Déclaration enregistrée avec succès !");
                dialogStage.close();
            } else {
                errorLabel.setText("Erreur lors de la déclaration.");
            }
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean validateForm() {
        errorLabel.setText("");

        if (typeComboBox.getValue() == null) {
            errorLabel.setText("Veuillez sélectionner un type d'appareil");
            return false;
        }

        if (serialField.getText().trim().isEmpty()) {
            errorLabel.setText("Le numéro de série est obligatoire");
            return false;
        }

        if (proprietaireField.getText().trim().isEmpty()) {
            errorLabel.setText("Le nom du propriétaire est obligatoire");
            return false;
        }

        if (contactField.getText().trim().isEmpty()) {
            errorLabel.setText("Le contact du propriétaire est obligatoire");
            return false;
        }

        return true;
    }

    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(dialogStage);
        alert.showAndWait();
    }
}
