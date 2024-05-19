package pt.ipp.isep.dei.esoft.project.domain;

public class MediumPark extends GreenSpace{

    public MediumPark(String name, double area) {
        super(name, area);
    }

    @Override
    public double getArea() {
        return 0;
    }
}
