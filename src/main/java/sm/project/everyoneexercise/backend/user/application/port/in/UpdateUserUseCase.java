package sm.project.everyoneexercise.backend.user.application.port.in;

import sm.project.everyoneexercise.backend.user.domain.User;

public interface UpdateUserUseCase {
    User updateUser(String userId, UpdateUserCommand updateUserCommand);
}
