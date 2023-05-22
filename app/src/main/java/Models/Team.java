package Models;

import java.util.List;

public class Team {
    public int t_id ;
    public String t_name;
    List<Employee> employees ;

    public Team(String t_name) {
        this.t_name = t_name;
    }

    public Team(int t_id, String t_name, List<Employee> employees) {
        this.t_id = t_id;
        this.t_name = t_name;
        this.employees = employees;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
