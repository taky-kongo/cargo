package ci.atosdigitalacademy.cargo.security;

import ci.atosdigitalacademy.cargo.models.Role;
import ci.atosdigitalacademy.cargo.models.User;
import ci.atosdigitalacademy.cargo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DomainUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        final List<GrantedAuthority> grantedAuthorities = user.get()
                .getRoles()
                .stream()
                .map(Role::getRole)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return user.map(userRecover -> org.springframework.security.core.userdetails.User.builder()
                .username(userRecover.getUsername())
                .password(userRecover.getPassword())
                .authorities(grantedAuthorities)
                .build()).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
