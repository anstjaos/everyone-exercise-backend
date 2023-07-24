package sm.project.everyoneexercise.backend.user.application.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sm.project.everyoneexercise.backend.user.application.port.out.UpdateUserPort;

@ExtendWith(MockitoExtension.class)
class UserUpdateServiceTest {
    @Mock
    private UpdateUserPort updateUserPort;

    @InjectMocks
    private UserUpdateService userUpdateService;
}