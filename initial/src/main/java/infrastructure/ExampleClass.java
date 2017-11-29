package infrastructure;

import lombok.*;

@ToString(exclude = {"isReadable"})
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"i"})
@Data
public class ExampleClass {
    private int i;
    @NonNull
    private String text;
    private boolean isReadable;
//    private final int as;

    public ExampleClass(@NonNull String text, boolean isReadable) {
        this.text = text;
        this.isReadable = isReadable;
    }
}
