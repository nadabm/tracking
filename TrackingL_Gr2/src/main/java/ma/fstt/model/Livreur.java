package ma.fstt.model;


// java bean
public class Livreur {
        private Long id_livreur ;

        private String nom ;

        private String telephone ;
        private String prenom;
    public Livreur() {
    }

    public Livreur( long id_livreur,String nom, String telephone, String prenom) {
       this.id_livreur = id_livreur;
        this.nom = nom;
        this.telephone = telephone;
        this.prenom = prenom;
    }

    public Long getId_livreur() {
        return id_livreur;
    }

    public void setId_livreur(Long id_livreur) {
        this.id_livreur = id_livreur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
       // return "Livreur{" + "id_livreur=" + id_livreur + ", nom='" + nom + '\'' + ", telephone='" + telephone + '\'' + ", prenom='" + '}' + prenom;
        return "Livreur{" + "id_livreur=" + id_livreur + ", nom='" + nom + '\'' + ", telephone='" + telephone + '\'' + ", prenom='" + prenom + '\'' + '}';

    }
}
