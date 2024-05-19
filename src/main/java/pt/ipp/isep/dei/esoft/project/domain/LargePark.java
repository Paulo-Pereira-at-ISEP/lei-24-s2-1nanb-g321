package pt.ipp.isep.dei.esoft.project.domain;
public class LargePark extends GreenSpace {

    public LargePark(String name, double area) {
        super(name, area);
    }

    @Override
    public double getArea() {
        return 0;
    }

}
