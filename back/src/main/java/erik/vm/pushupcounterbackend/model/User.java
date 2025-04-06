package erik.vm.pushupcounterbackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "users")
public class User {
    @Id
    private Integer id;
    private String name;
    private String email;
    private Integer timesClicked = 0;
    private LocalDate lastClickDate;
}