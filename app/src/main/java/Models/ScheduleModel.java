package Models;

public class ScheduleModel {

    public String nameArticle , poidsArticle , nbPreparation , equiResp ;

    public ScheduleModel(String nameArticle, String poidsArticle, String nbPreparation, String equiResp) {
        this.nameArticle = nameArticle;
        this.poidsArticle = poidsArticle;
        this.nbPreparation = nbPreparation;
        this.equiResp = equiResp;
    }

    public String getNameArticle() {
        return nameArticle;
    }

    public void setNameArticle(String nameArticle) {
        this.nameArticle = nameArticle;
    }

    public String getPoidsArticle() {
        return poidsArticle;
    }

    public void setPoidsArticle(String poidsArticle) {
        this.poidsArticle = poidsArticle;
    }

    public String getNbPreparation() {
        return nbPreparation;
    }

    public void setNbPreparation(String nbPreparation) {
        this.nbPreparation = nbPreparation;
    }

    public String getEquiResp() {
        return equiResp;
    }

    public void setEquiResp(String equiResp) {
        this.equiResp = equiResp;
    }
}
