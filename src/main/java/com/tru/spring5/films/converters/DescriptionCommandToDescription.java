package com.tru.spring5.films.converters;

import com.tru.spring5.films.commands.DescriptionCommand;
import com.tru.spring5.films.POJO.Description;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class DescriptionCommandToDescription implements Converter<DescriptionCommand, Description> {

    @Nullable
    @Synchronized
    @Override
    public Description convert(DescriptionCommand descriptionCommand) {
        if(descriptionCommand == null)
            return null;

        final Description description = new Description();
        description.setDescriptionText(descriptionCommand.getDescription());
        description.setId(descriptionCommand.getId());
        return description;
    }
}
