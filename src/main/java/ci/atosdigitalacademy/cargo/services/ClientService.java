package ci.atosdigitalacademy.cargo.services;

import ci.atosdigitalacademy.cargo.services.dto.ClientDTO;
import ci.atosdigitalacademy.cargo.services.dto.RegistrationPersonDTO;
import ci.atosdigitalacademy.cargo.services.dto.ResponseRegisterClientDTO;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    ClientDTO save(ClientDTO clientDTO);

    ClientDTO saveClient(ClientDTO clientDTO);

    ClientDTO update(ClientDTO clientDTO);

    ClientDTO update(ClientDTO clientDTO, Long id);

    ClientDTO partialUpdate(ClientDTO clientDTO, Long id);

    Optional<ClientDTO> findOne(Long id);

    Optional<ClientDTO> findBySlug(String slug);

    List<ClientDTO> findAll();

    void delete(Long id);

    ResponseRegisterClientDTO registerClient(RegistrationPersonDTO registrationPersonDTO);

    Optional<ClientDTO> findClientByUserId(Long userId);
}
