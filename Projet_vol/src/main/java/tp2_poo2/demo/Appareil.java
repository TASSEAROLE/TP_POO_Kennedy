package tp2_poo2.demo;
public class Appareil {
    private String type;
    private String numeroSerie;
    private String proprietaire;
    private String contactProprietaire;

    // Constructeur par défaut
    public Appareil() {}

    // Constructeur avec paramètres
    public Appareil(String type, String numeroSerie, String proprietaire, String contactProprietaire) {
        this.type = type;
        this.numeroSerie = numeroSerie;
        this.proprietaire = proprietaire;
        this.contactProprietaire = contactProprietaire;
    }

    // Getters
    public String getType() { return type; }
    public String getNumeroSerie() { return numeroSerie; }
    public String getProprietaire() { return proprietaire; }
    public String getContactProprietaire() { return contactProprietaire; }

    // Setters
    public void setType(String type) { this.type = type; }
    public void setNumeroSerie(String numeroSerie) { this.numeroSerie = numeroSerie; }
    public void setProprietaire(String proprietaire) { this.proprietaire = proprietaire; }
    public void setContactProprietaire(String contactProprietaire) {
        this.contactProprietaire = contactProprietaire;
    }

    @Override
    public String toString() {
        return "Appareil [type=" + type
                + ", numeroSerie=" + numeroSerie
                + ", proprietaire=" + proprietaire
                + ", contactProprietaire=" + contactProprietaire + "]";
    }
}