package sm.project.everyoneexercise.backend.user.application.port.out;

import sm.project.everyoneexercise.backend.user.application.port.in.UpdateUserCommand;
import sm.project.everyoneexercise.backend.user.domain.User;

public interface UpdateUserPort {
    User updateUser(String userId, UpdateUserCommand updateUserCommand);
}
