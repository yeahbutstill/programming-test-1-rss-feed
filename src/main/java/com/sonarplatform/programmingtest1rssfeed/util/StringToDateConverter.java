package com.sonarplatform.programmingtest1rssfeed.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
// converter dari tipe String ke Date
public class StringToDateConverter implements Converter<String, Date> {
    // ini untuk format request
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public Date convert(String source) {
        // kalau berhasil kita parse si sourcenya
        try {
            return dateFormat.parse(source);
        } catch (ParseException e) {
            // kalau gagal kita balikin null
            log.warn("Error convert date from String {}", source, e);
            return null;
        }
    }

    /**
     * Construct a composed {@link Converter} that first applies this {@link Converter}
     * to its input, and then applies the {@code after} {@link Converter} to the
     * result.
     *
     * @param after the {@link Converter} to apply after this {@link Converter}
     *              is applied
     * @return a composed {@link Converter} that first applies this {@link Converter}
     * and then applies the {@code after} {@link Converter}
     * @since 5.3
     */
    @Override
    public <U> Converter<String, U> andThen(Converter<? super Date, ? extends U> after) {
        return Converter.super.andThen(after);
    }
}
