import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Data access object
public class DriversDao {
    private Connection connection;

    public DriversDao(Connection connection) {
        this.connection = connection;
    }

    public Optional <Driver> findById(Long id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from driver");
        ResultSet resultCar = statement.executeQuery("select * from car");
        String firstname;
        String lastname;
        Integer age;
        String model;
        String color;
        List<Car> cars = new ArrayList<>();
        Driver driver = null;
        while(result.next()){
            if(result.getInt("id") == id){
                firstname = result.getString("first_name");
                lastname = result.getString("last_name");
                age = result.getInt("age");
                while(resultCar.next()){
                    if(resultCar.getInt("drivet_id") == id){
                        model = resultCar.getString("model");
                        color = resultCar.getString("color");
                        Car car = new Car(id, model, color);
                        cars.add(car);
                    }
                }
                driver = new Driver(id, firstname, lastname, age, cars);
            }
        }
        Optional<Driver> optionalDriver = Optional.ofNullable(driver);
        for(int i = 0; i < cars.size(); i++){
            cars.get(i).setDriver(optionalDriver.get());
        }
        return optionalDriver;

    }
}
