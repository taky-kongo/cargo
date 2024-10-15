package ci.atosdigitalacademy.cargo.services;

import ci.atosdigitalacademy.cargo.services.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO save(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    Optional<UserDTO> findOne(Long id);

    List<UserDTO> findAll();

    void delete(Long id);

    UserDTO getCurrentUser();

    Optional<UserDTO> findByUsername(String username);
}
