package akademia.cars.model;

import lombok.*;

import javax.persistence.*;



@Data
@AllArgsConstructor
//@NoArgsConstructor
@Builder
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "brand",nullable = false)
    private String brand;

    @Column(name = "model",nullable = false)
    private String model;

    @Column(name = "power")
    private String power;

    @Column(name = "plate", unique = true, nullable = false)
    private String plate;


    public Car(){
        System.out.println("This is new car");
    }

    public Car(String brand, String model, String power, String plate) {
        this.brand = brand;
        this.model = model;
        this.power = power;
        this.plate = plate;
    }
}
