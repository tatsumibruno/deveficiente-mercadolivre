package deveficiente.mercadolivre.commons.infra.advice;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MessageError {
    private final String title;
    private List<String> details = new ArrayList<>();

    public void addDetail(String detail) {
        details.add(detail);
    }
}
