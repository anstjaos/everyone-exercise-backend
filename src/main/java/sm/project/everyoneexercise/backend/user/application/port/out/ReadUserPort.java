package sm.project.everyoneexercise.backend.user.application.port.out;

import sm.project.everyoneexercise.backend.user.domain.User;

public interface ReadUserPort {
    User readUserByUserId(String userId);
}
