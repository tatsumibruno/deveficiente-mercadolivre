package deveficiente.mercadolivre.comum.exceptions;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.ToString;

@ToString
public class BusinessException extends RuntimeException {
    @Getter
    private final String[] arguments;

    private BusinessException(String message, String[] arguments) {
        super(message);
        this.arguments = arguments;
    }

    public static BusinessException of(String message, String... args) {
        String[] arguments = Lists.newArrayList(args).toArray(args);
        return new BusinessException(message, arguments);
    }
}
