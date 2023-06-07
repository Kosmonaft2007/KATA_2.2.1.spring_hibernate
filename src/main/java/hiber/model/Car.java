package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long carId;
    @Column(name = "model")
    private String model;
    @Column(name = "series")
    private int series;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name ="user_id")
private User user;

    public Car(Long carId, String model, int series) {
        this.carId = carId;
        this.model = model;
        this.series = series;
    }

    public Car() {

    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", model='" + model + '\'' +
                ", series=" + series +
                '}';
    }
}
