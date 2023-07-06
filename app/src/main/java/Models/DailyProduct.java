package Models;

public class ScheduledArticle {

   private int s_id ;
    private Team team ;
    private ProductModel article ;
    private int num_prep ;

    public ScheduledArticle() {
    }

    public ScheduledArticle(ProductModel article, Team team, int num_prep) {
        this.team = team;
        this.article = article;
        this.num_prep = num_prep;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public ProductModel getArticle() {
        return article;
    }

    public void setArticle(ProductModel article) {
        this.article = article;
    }

    public int getNum_prep() {
        return num_prep;
    }

    public void setNum_prep(int num_prep) {
        this.num_prep = num_prep;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }
}
