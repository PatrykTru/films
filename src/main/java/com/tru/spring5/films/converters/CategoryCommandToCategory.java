package com.tru.spring5.films.converters;

import com.tru.spring5.films.commands.CategoryCommand;
import com.tru.spring5.films.POJO.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory  implements Converter<CategoryCommand, Category> {

    @Nullable
    @Synchronized
    @Override
    public Category convert(CategoryCommand categoryCommand) {
        if(categoryCommand==null)
        return null;

        final Category category = new Category();
        category.setDescription(categoryCommand.getCategory());
        category.setId(categoryCommand.getId());

        return category;
    }
}
