package ci.atosdigitalacademy.cargo.services;

import ci.atosdigitalacademy.cargo.services.dto.RoleDTO;
import java.util.List;
import java.util.Optional;

public interface RoleService {

    RoleDTO save (RoleDTO roleDTO);

    RoleDTO update (RoleDTO roleDTO);

    void delete (Long id);

    List<RoleDTO> findByRole(String roleUser);

    List<RoleDTO> findAll();

    Optional<RoleDTO> findOne(Long id);

    Optional<RoleDTO> findBySlug(String slug);

    RoleDTO saveRole(RoleDTO roleDTO);

    RoleDTO updateTotal(RoleDTO roleDTO, Long id);

    void initRoles(List<RoleDTO> roleUsers);

}
