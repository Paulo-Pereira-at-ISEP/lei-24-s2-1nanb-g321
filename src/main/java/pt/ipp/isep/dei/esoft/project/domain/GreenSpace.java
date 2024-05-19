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

    public abstract double getArea();
}

