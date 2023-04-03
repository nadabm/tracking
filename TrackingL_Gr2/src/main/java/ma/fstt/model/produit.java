package ma.fstt.model;


// java bean
public class produit {
    private long id_produit ;

    private String nom ;

    private String prix ;
    private String descreption;
    public produit() {

    }
    public produit( long id_produit,String nom, String prix, String descreption) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.prix = prix;
        this.descreption = descreption;
    }



    public long getId_produit() {
        return id_produit;
    }

    public void setId_produit(long id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }
    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    @Override
    public String toString() {

        return "prduit{" + "id_produit=" + id_produit + ", nom='" + nom + '\'' + ", prix='" + prix + '\'' + ", descreption='" + descreption + '\'' + '}';

    }
}