package sm.project.everyoneexercise.backend.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends EveryOneExerciseException {

    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
