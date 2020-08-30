package com.tru.spring5.films.converters;

import com.tru.spring5.films.commands.DescriptionCommand;
import com.tru.spring5.films.POJO.Description;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class DescriptionToDescriptionCommand implements Converter<Description, DescriptionCommand> {


    @Nullable
    @Synchronized
    @Override
    public DescriptionCommand convert(Description description) {
        if(description==null)
        return null;


        final DescriptionCommand descriptionCommand = new DescriptionCommand();
        descriptionCommand.setDescription(description.getDescriptionText());
        descriptionCommand.setId(description.getId());
        return descriptionCommand;
    }
}
