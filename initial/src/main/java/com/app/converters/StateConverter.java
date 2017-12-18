package com.app.converters;

import com.businessdomain.states.*;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StateConverter implements AttributeConverter<State, String> {
    @Override
    public String convertToDatabaseColumn(State attribute) {
        return attribute == null ? null : attribute.getName().name();
    }

    @Override
    public State convertToEntityAttribute(String dbData) {
        State result = null;
        if (dbData != null) {
            switch (StateEn.valueOf(dbData)) {
                case NEW:
                    result = new NewState();
                    break;
                case CANCELED:
                    result = new CancelledState();
                    break;
                case DONE:
                    result = new DoneState();
                    break;
                case IN_PROGRESS:
                    result = new InProgressState();
                    break;
                default: result = new NewState();
            }
        }
        return result;
    }
}
