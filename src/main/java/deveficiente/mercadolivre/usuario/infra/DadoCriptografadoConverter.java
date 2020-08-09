package deveficiente.mercadolivre.usuario.infra;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DadoCriptografadoConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return new BCryptPasswordEncoder(31).encode(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData;
    }
}
