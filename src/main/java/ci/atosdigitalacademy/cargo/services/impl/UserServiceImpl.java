package ci.atosdigitalacademy.cargo.services.impl;

import ci.atosdigitalacademy.cargo.models.User;
import ci.atosdigitalacademy.cargo.repositories.UserRepository;
import ci.atosdigitalacademy.cargo.services.UserService;
import ci.atosdigitalacademy.cargo.services.dto.UserDTO;
import ci.atosdigitalacademy.cargo.services.mapper.UserMapper;
import ci.atosdigitalacademy.cargo.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDTO save(UserDTO userDTO) {
        log.debug("Request to save user {}", userDTO);
        String password = userDTO.getPassword();
        userDTO.setPassword(bCryptPasswordEncoder.encode(password));
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        log.debug("Request to save User {} with slug", userDTO);
        final String slug = SlugifyUtils.generate(String.valueOf(userDTO.getUsername()));
        userDTO.setSlug(slug);
        return save(userDTO);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        log.debug("Request to update user {}", userDTO);
        return findOne(userDTO.getId()).map(user -> {
            user.setUsername(userDTO.getUsername());
            return save(user);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public Optional<UserDTO> findOne(Long id) {
        log.debug("Request to get User : {}", id);
        return userRepository.findById(id).map(user -> {
            return userMapper.toDto(user);
        });
    }

    @Override
    public List<UserDTO> findAll() {
        return List.of();
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete User: {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO getCurrentUser() {

        SecurityContext securityContext = SecurityContextHolder.getContext();

        Authentication authentication = securityContext.getAuthentication();

        String username = "";

        if (authentication == null) {
            username = null;
        } else if (authentication.getPrincipal() instanceof UserDetails springSecurityUser) {
            username = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof Jwt jwt) {
            username = jwt.getSubject();
        } else if (authentication.getPrincipal() instanceof String s) {
            username = s;
        }

        Optional<UserDTO> userDTO = findByUsername(username);
        UserDTO user = new UserDTO();
        if (userDTO.isPresent()) {
            user = userDTO.get();
        }
        return user;
    }

    @Override
    public Optional<UserDTO> findByUsername(String username) {
        return userRepository.findByUsername(username).map(user -> {
            return userMapper.toDto(user);
        });
    }
}
