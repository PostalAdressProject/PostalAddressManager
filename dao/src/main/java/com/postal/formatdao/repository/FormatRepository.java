package com.postal.formatdao.repository;
import com.postal.model.models.PostalFormat;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface FormatRepository {
    PostalFormat getFormat(final String countryName) throws IOException;
    List<PostalFormat> getFormats() throws IOException;
}
