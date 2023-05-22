package Models;

public class ArticleModel {

    public String name_art ;
    public int id_art , cadence_art, weight_art ;
    public byte[] image_art ;

    public ArticleModel() {
    }

    public ArticleModel(int id_art,String name_art, int weight_art ,int cadence_art,byte[] image_art) {
        this.name_art = name_art;
        this.image_art = image_art;
        this.id_art = id_art;
        this.cadence_art = cadence_art;
        this.weight_art = weight_art;
    }

    public ArticleModel(String name_art, int weight_art ,int cadence_art,byte[] image_art) {
        this.name_art = name_art;
        this.image_art = image_art;
        this.cadence_art = cadence_art;
        this.weight_art = weight_art;
    }

    public String getName_art() {
        return name_art;
    }

    public void setName_art(String name_art) {
        this.name_art = name_art;
    }

    public byte[] getImage_art() {
        return image_art;
    }

    public void setImage_art(byte[] image_art) {
        this.image_art = image_art;
    }

    public int getId_art() {
        return id_art;
    }

    public void setId_art(int id_art) {
        this.id_art = id_art;
    }

    public int getCadence_art() {
        return cadence_art;
    }

    public void setCadence_art(int cadence_art) {
        this.cadence_art = cadence_art;
    }

    public int getWeight_art() {
        return weight_art;
    }

    public void setWeight_art(int weight_art) {
        this.weight_art = weight_art;
    }
}
