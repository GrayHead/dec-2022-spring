package ua.com.owu.dec2022spring.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.owu.dec2022spring.models.views.Views;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Level1.class)
    private int id;
    @NotBlank(message = "name is required")
    @Size(min = 3, message = "name must be at least 3 chars")
    @Size(max = 255, message = "name is too long, pal!")
    @JsonView({Views.Level1.class, Views.NoLevel.class})
    private String name;

    private String avatar;
    private String email;
    private boolean isActivated = false;

    public User(String name) {
        this.name = name;
    }


}
