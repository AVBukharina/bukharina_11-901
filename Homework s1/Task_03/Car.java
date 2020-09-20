public class Car {
    private Long id;
    private String model;
    private String color;
    private Driver driver;

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Car(Long id, String model, String color) {
        this.id = id;
        this.model = model;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
