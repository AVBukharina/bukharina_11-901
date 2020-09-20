import java.util.List;

public class Driver {
    private Long id;
    private String firstname;
    private String lastname;
    private Integer age;
    private List<Car> cars;

    public Driver(Long id, String firstname, String lastname, Integer age, List<Car> cars) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", cars=" + cars.toString() +
                '}';
    }
}
