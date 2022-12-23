import java.time.LocalDateTime;

public class ValidateUtils {
    public static String validateString(String value) {
        if (value == null || value.isEmpty() || value.isBlank()) {
            throw new IllegalArgumentException("Заполнено не корректно!");
        }
        return value;
    }

    public static RepeatabilityOfTask validateRepeatabilityOfTask(RepeatabilityOfTask value) {
        if (value == null) {
            throw new IllegalArgumentException("Заполнено не корректно!");
        }
        return value;
    }

    public static LocalDateTime validateLocalDateTime(LocalDateTime value) {
        return value == null ? LocalDateTime.now() : value;
    }

}
