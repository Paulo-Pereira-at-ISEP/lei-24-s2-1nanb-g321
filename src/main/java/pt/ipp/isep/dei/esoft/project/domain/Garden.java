package pt.ipp.isep.dei.esoft.project.domain;

public class Garden extends GreenSpace{

    public Garden(String name, double area) {
        super(name, area);
    }

    @Override
    public double getArea() {
        return 0;
    }
}
