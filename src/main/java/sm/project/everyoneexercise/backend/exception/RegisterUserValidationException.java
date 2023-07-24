package sm.project.everyoneexercise.backend.exception;

import lombok.Getter;

@Getter
public class RegisterUserValidationException extends EveryOneExerciseException {

    public RegisterUserValidationException(String errorMessage) { super(errorMessage); }
}
