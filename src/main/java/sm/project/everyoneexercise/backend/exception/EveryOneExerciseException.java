package sm.project.everyoneexercise.backend.exception;

import lombok.Getter;

@Getter
public class EveryOneExerciseException extends RuntimeException {
    private final String errorMessage;

    public EveryOneExerciseException(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }
}
