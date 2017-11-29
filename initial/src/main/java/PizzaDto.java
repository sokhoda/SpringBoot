import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class PizzaDto {
    private String name;
    private String type;
    private Double price;
}
