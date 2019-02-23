package akademia.cars.model;

import lombok.*;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
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



}
