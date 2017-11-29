package infrastructure.db.converters;

import javax.persistence.AttributeConverter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class DateConverter implements AttributeConverter<LocalDateTime, Date> {
    @Override
    public Date convertToDatabaseColumn(LocalDateTime attribute) {
        return attribute == null ? null : Date.from(attribute.toInstant(ZoneOffset.UTC));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Date dbData) {
        return dbData == null ? null : LocalDateTime.ofInstant(dbData.toInstant(), ZoneOffset.UTC);
    }
}
