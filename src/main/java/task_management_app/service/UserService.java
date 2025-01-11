package task_management_app.service;



import java.util.Optional;

import task_management_app.dto.UserDTO;
import task_management_app.dto.UserRegistrationDTO;



public interface UserService {
    UserDTO registerUser(UserRegistrationDTO userRegistrationDTO);
    Optional<UserDTO> login(String username, String password);

}
