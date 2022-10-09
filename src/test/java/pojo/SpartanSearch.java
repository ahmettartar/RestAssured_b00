package pojo;

import lombok.Data;
import pojo.Spartan;

import java.util.List;

//@Getter
//@Setter
@Data
public class SpartanSearch {
    private List<Spartan> content;
    private int totalElement;
}
