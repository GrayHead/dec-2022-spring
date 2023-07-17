package ua.com.owu.dec2022spring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 1, message = "id must be gt than 0")
    private int id;
    @NotBlank(message = "model cannot be blank")
    @Size(min = 2, message = "there is no model ....")
    private String model;
    @NotBlank(message = "producer cannot be blank")
    @Size(min = 3, message = "there is no producer ....")
    private String producer;
    @Min(value = 56, message = "ha-ha")
    private int potujnist;
}
