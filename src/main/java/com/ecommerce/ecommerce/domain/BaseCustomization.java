package domain;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BaseCustomization {
    private String sector; // dorso
    private String option; // texto
    public BaseCustomization(String sector, String option) {
        this.sector = sector;
        this.option = option;
    }


}
