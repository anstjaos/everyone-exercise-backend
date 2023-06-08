package sm.project.everyoneexercise.backend.user.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import sm.project.everyoneexercise.backend.user.UserUtil;
import sm.project.everyoneexercise.backend.user.adapter.in.RegisterUserRequest;
import sm.project.everyoneexercise.backend.user.application.port.in.RegisterUserCommand;
import sm.project.everyoneexercise.backend.user.application.port.in.RegisterUserUseCase;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class UserControllerTest {

    @MockBean
    private RegisterUserUseCase registerUserUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void registerUser_failure_validateFailed() throws Exception {
        var registerUserRequest = RegisterUserRequest.builder()
                .phoneNumber("")
                .autoLogin(false)
                .build();

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerUserRequest))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.statusCode").value(400))
                .andExpect(jsonPath("$.body.errorMessage", containsString("User id is mandatory.")))
                .andExpect(jsonPath("$.body.errorMessage", containsString("Nickname is mandatory.")))
                .andExpect(jsonPath("$.body.errorMessage", containsString("Password is mandatory.")));
    }

    @Test
    void registerUser_success() throws Exception {
        var registerUserRequest = RegisterUserRequest.builder()
                .userId("userId")
                .nickname("nickname")
                .password("password")
                .phoneNumber("phoneNumber")
                .autoLogin(false)
                .build();

        var registerUserCommand = RegisterUserCommand.mapRequestToCommand(registerUserRequest);

        when(registerUserUseCase.registerUser(registerUserCommand)).thenReturn(UserUtil.createUser(registerUserCommand));

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerUserRequest))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.header.isSuccessful").value(true))
                .andExpect(jsonPath("$.header.statusCode").value(200));
    }
}