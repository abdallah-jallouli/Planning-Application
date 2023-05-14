package Models;

public class ArticleModel {

    public String nameart , poids ;
    public int cadence ;

    public ArticleModel() {
    }

    public ArticleModel(String nameart, String poids, int cadence) {
        this.nameart = nameart;
        this.poids = poids;
        this.cadence = cadence;
    }

    public String getNameart() {
        return nameart;
    }

    public void setNameart(String nameart) {
        this.nameart = nameart;
    }

    public String getPoids() {
        return poids;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }

    public int getCadence() {
        return cadence;
    }

    public void setCadence(int cadence) {
        this.cadence = cadence;
    }
}
