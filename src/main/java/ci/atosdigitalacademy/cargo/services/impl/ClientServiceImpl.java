package ci.atosdigitalacademy.cargo.services.impl;

import ci.atosdigitalacademy.cargo.models.Client;
import ci.atosdigitalacademy.cargo.repositories.ClientRepository;
import ci.atosdigitalacademy.cargo.security.AuthorityConstants;
import ci.atosdigitalacademy.cargo.services.ClientService;
import ci.atosdigitalacademy.cargo.services.RoleService;
import ci.atosdigitalacademy.cargo.services.UserService;
import ci.atosdigitalacademy.cargo.services.dto.*;
import ci.atosdigitalacademy.cargo.services.mapper.ClientMapper;
import ci.atosdigitalacademy.cargo.services.mapping.ClientMapping;
import ci.atosdigitalacademy.cargo.utils.SlugifyUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;

    @Override
    public ClientDTO save(ClientDTO clientDTO) {
        log.debug("Request to save client {}", clientDTO);
        Client client = clientMapper.toEntity(clientDTO);
        client = clientRepository.save(client);
        return clientMapper.toDto(client);
    }

    @Override
    public ClientDTO saveClient(ClientDTO client) {
        log.debug("Request to save Client {} with slug", client);
        final String slug = SlugifyUtils.generate(String.valueOf(client.getName()));
        client.setSlug(slug);
        return save(client);
    }

    @Override
    public ClientDTO update(ClientDTO clientDTO) {
        log.debug("Request to update client {}", clientDTO);
        return findOne(clientDTO.getId()).map(client -> {
            client.setName(clientDTO.getName());
            client.setPhoneNumber(clientDTO.getPhoneNumber());
            client.setEmail(clientDTO.getEmail());
            client.setReservations(clientDTO.getReservations());
            return save(client);
        }).orElseThrow(() -> new IllegalArgumentException("Client not found"));
    }

    @Override
    public ClientDTO update(ClientDTO clientDTO, Long id) {
        log.debug("Request to update client {} by id", clientDTO);
        clientDTO.setId(id);
        return update(clientDTO);
    }

    @Override
    public ClientDTO partialUpdate(ClientDTO clientDTO, Long id) {
        log.debug("Request to update client {} by id {}", clientDTO, id);
        return clientRepository.findById(id).map(client -> {
            ClientMapping.partialUpdate(client, clientDTO);
            return client;
        }).map(clientRepository::save).map(clientMapper::toDto).orElse(null);
    }

    @Override
    public Optional<ClientDTO> findOne(Long id) {
        log.debug("Request to get Client by id {}", id);
        return clientRepository.findById(id).map(client -> {
            return clientMapper.toDto(client);
        });
    }

    @Override
    public Optional<ClientDTO> findBySlug(String slug) {
        log.debug("Request to get Client by slug {}", slug);
        return clientRepository.findBySlug(slug).map(client -> {
            return clientMapper.toDto(client);
        });
    }

    @Override
    public List<ClientDTO> findAll() {
        log.debug("Request to get all clients");
        return clientRepository.findAll().stream().map(client -> {
            return clientMapper.toDto(client);
        }).toList();
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete client {}", id);
        clientRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ResponseRegisterClientDTO registerClient(RegistrationPersonDTO registrationPersonDTO) {
        log.debug("Request to register client {}", registrationPersonDTO);
        List<RoleDTO> roles = roleService.findByRole(AuthorityConstants.ROLE_USER);
        UserDTO user = modelMapper.map(registrationPersonDTO, UserDTO.class);
        user.setPassword(registrationPersonDTO.getPassword());
        user.setRoles(roles);
        user = userService.saveUser(user);

        ClientDTO client = modelMapper.map(registrationPersonDTO, ClientDTO.class);
        client.setUser(user);
        client = saveClient(client);

        ResponseRegisterClientDTO responseRegisterClientDTO = new ResponseRegisterClientDTO();
        responseRegisterClientDTO.setClient(client);
        responseRegisterClientDTO.setUser(user);
        return responseRegisterClientDTO;
    }

    @Override
    public Optional<ClientDTO> findClientByUserId(Long userId) {
        return clientRepository.findClientByUserId(userId).map(client -> {
            return clientMapper.toDto(client);
        });
    }
}
