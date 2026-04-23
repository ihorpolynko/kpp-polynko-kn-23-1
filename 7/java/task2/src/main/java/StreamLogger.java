import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class StreamLogger<T> {
    private final Path logPath;

    public StreamLogger(Path logPath) {
        this.logPath = logPath;
    }

    // Додає один запис у файл
    public void append(T log) {
        try {
            Files.writeString(
                    logPath,
                    log.toString() + "\n",
                    StandardOpenOption.APPEND,
                    StandardOpenOption.CREATE
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
