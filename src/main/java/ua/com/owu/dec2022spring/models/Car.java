package ua.com.owu.dec2022spring.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.owu.dec2022spring.models.views.Views;

@Data
@Entity
@NoArgsConstructor
public class Car {


    //        Level2 - model producer power ( для endpoint /cars/power, /cars/producer)
    //        Level3 - model producer (для endpoint /cars)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 1, message = "id must be gt than 0")
    @JsonView(value = {Views.Level1.class})
    private int id;

    @NotBlank(message = "model cannot be blank")
    @Size(min = 2, message = "there is no model ....")
    @JsonView(value = {Views.Level1.class, Views.Level2.class, Views.Level3.class})
    private String model;

    @NotBlank(message = "producer cannot be blank")
    @Size(min = 3, message = "there is no producer ....")
    @JsonView(value = {Views.Level1.class, Views.Level2.class, Views.Level3.class})
    private String producer;

    @Min(value = 56, message = "ha-ha")
    @JsonView(value = {Views.Level1.class, Views.Level2.class})
    private int potujnist;
}
