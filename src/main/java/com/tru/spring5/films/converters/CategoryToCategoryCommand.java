package com.tru.spring5.films.converters;

import com.tru.spring5.films.commands.CategoryCommand;
import com.tru.spring5.films.POJO.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand>
{
    @Nullable
    @Synchronized
    @Override
    public CategoryCommand convert(Category category) {
        if(category == null)
        return null;

        final CategoryCommand categoryCommand = new CategoryCommand();

        categoryCommand.setCategory(category.getDescription());
        categoryCommand.setId(category.getId());
        return categoryCommand;
    }
}
