package com.postal.formatdao.accessor;

import com.postal.formatdao.exception.FormatDataAccessException;
import com.postal.formatdao.repository.FormatRepository;
import com.postal.model.models.PostalFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FormatAccessor {

    @Autowired
    private FormatRepository formatRepository;

    public PostalFormat getFormat(final String countryName) throws FormatDataAccessException {
        try {
            return formatRepository.getFormat(countryName);
        } catch (final IllegalArgumentException e) {
            System.out.println("Illegal argument exception occurred:" + e.getMessage());
            throw new IllegalArgumentException(e);
        } catch (final Exception e) {
            System.out.println("Exception occurred:" + e.getMessage());
            throw new FormatDataAccessException(e.getMessage(), e);
        }
    }

    public List<PostalFormat> getFormats() throws FormatDataAccessException {
        try {
            return formatRepository.getFormats();
        } catch (final IllegalArgumentException e) {
            System.out.println("Illegal argument exception occurred:" + e.getMessage());
            throw new IllegalArgumentException(e);
        } catch (final Exception e) {
            System.out.println("Exception occurred:" + e.getMessage());
            throw new FormatDataAccessException(e.getMessage(), e);
        }
    }
}