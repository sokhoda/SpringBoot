package utils.parsers;

import java.io.InputStream;
import java.util.List;

public interface CustomParser<T> {
    public static final String FAIL_TO_UPLOAD_FILE = "Fail to upload file: '%s'";
    public static final String FILE_UPLOADED_SUCCESSFULLY = "File uploaded successfully: '%s'";

    List<T> parse(InputStream file);
}
