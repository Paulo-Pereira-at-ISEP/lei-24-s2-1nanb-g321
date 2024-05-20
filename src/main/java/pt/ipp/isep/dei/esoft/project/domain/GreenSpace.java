package pt.ipp.isep.dei.esoft.project.domain;

public abstract class GreenSpace {
    private String name;
    private double area;
    Employee manager;

    public Employee getManager() {
        return manager;
    }

    public GreenSpace(String name, double area) {
        this.name = name;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getArea(){
        return area;
    }

    public void setArea(double area){
        this.area = area;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager){
        this.manager = manager;
    }
    public GreenSpace clone() {
        return new GreenSpace(this.name, this.area, this.manager);
    }
}

