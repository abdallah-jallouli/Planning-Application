package Models;

public class Employee {
    public int e_id;
    public String e_name;
    public String e_first_name;
    public String e_phone_number;
    public String e_position;
    public Team team;


    public Employee(int e_id, String e_name, String e_first_name, String e_phone_number, String e_position, Team team) {
        this.e_id = e_id;
        this.e_name = e_name;
        this.e_first_name = e_first_name;
        this.e_phone_number = e_phone_number;
        this.e_position = e_position;
        this.team = team;
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getE_first_name() {
        return e_first_name;
    }

    public void setE_first_name(String e_first_name) {
        this.e_first_name = e_first_name;
    }

    public String getE_phone_number() {
        return e_phone_number;
    }

    public void setE_phone_number(String e_phone_number) {
        this.e_phone_number = e_phone_number;
    }

    public String getE_position() {
        return e_position;
    }

    public void setE_position(String e_position) {
        this.e_position = e_position;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
