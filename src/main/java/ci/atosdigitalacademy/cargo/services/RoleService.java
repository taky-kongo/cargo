package ci.atosdigitalacademy.cargo.services;

import ci.atosdigitalacademy.cargo.models.Role;
import ci.atosdigitalacademy.cargo.services.dto.ReservationDTO;
import ci.atosdigitalacademy.cargo.services.dto.RoleDTO;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleService {

    RoleDTO save (RoleDTO roleDTO);

    RoleDTO update (RoleDTO roleDTO);

    void delete (Long id);

    Set<RoleDTO> findByRole(String roleUser);

    List<RoleDTO> findAll();

    Optional<RoleDTO> findOne(Long id);

    RoleDTO saveRole(RoleDTO roleDTO);

    RoleDTO updateTotal(RoleDTO roleDTO, Long id);

}
