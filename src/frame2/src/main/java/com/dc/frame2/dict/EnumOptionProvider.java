package com.dc.frame2.dict;

import com.dc.frame2.view.view.freemarker.form.Option;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/11/15.
 */
@Service("EnumOptionProvider")
public class EnumOptionProvider implements OptionProvider {
    @Override
    public List<Option> listOptions(String optionKey, Locale locale) {
        Class<?> keyClass = null;
        try {
            keyClass = Class.forName(optionKey);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Illegal defined optionKey in EnumOptionProvider, the optionKey must be a full enum class name and implements TranslatableEnum.class", e);
        }
        if (TranslatableEnum.class.isAssignableFrom(keyClass) && Enum.class.isAssignableFrom(keyClass)) {
            try {
                TranslatableEnum[] enums = (TranslatableEnum[]) keyClass.getMethod("values").invoke(null);
                return Arrays.stream(enums).map(e -> new Option().setValue(e.getValue()).setText(e.getCode())).collect(Collectors.toList());
            } catch (Exception e) {
                throw new IllegalStateException("not reachable", e);
            }
        } else {
            throw new IllegalStateException("Illegal defined optionKey in EnumOptionProvider, the optionKey must be a full enum class name and implements TranslatableEnum.class");
        }
    }
}
