package task_management_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;



import task_management_app.dto.UserDTO;
import task_management_app.dto.UserRegistrationDTO;
import task_management_app.entity.UserEntity;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(task_management_app.entity.UserEntity userEntity);

    UserEntity toEntity(UserRegistrationDTO userRegistrationDTO);
}
