package pt.ipp.isep.dei.esoft.project.domain;

public class GreenSpace {
    private String name;
    private double area;
    private String classification;
    Employee manager;


    public GreenSpace(String name, double area, String classification, Employee manager) {
        this.name = name;
        this.area = area;
        this.classification = classification;
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public GreenSpace clone() {
        return new GreenSpace(this.name, this.area, this.classification, this.manager);
    }

    public String toString() {
        return this.name;
    }

}
