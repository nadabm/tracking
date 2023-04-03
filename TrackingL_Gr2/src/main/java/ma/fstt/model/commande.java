package ma.fstt.model;

import java.util.List;

public class commande {
    private Long id_commande;
    private Livreur livreur;
    private List<produit> produits;
    private String date;
    private double km;
    public commande() {
    }

    public commande(long id_commande, Livreur livreur, List<produit> produits, String date, double km) {
        this.id_commande = id_commande;
        this.livreur = livreur;
        this.produits = produits;
        this.date = date;
        this.km =  km;
    }



    public void setId_commande(Long id_commande) {
        this.id_commande = id_commande;
    }

    public List<produit> getProduits() {
        return produits;
    }

    public void setProduits(List<produit> produits) {
        this.produits = produits;
    }

    public Livreur getLivreur() {
        return livreur;
    }

    public void setLivreur(Livreur livreur) {
        this.livreur = livreur;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    @Override
    public String toString() {
        return "commande{" +
                "id_commande=" + id_commande +
                ", livreur=" + livreur +
                ", produits=" + produits +
                ", date='" + date + '\'' +
                ", km=" + km +
                '}';
    }

}
