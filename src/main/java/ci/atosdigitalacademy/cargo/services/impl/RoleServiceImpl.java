package ci.atosdigitalacademy.cargo.services.impl;

import ci.atosdigitalacademy.cargo.repositories.RoleRepository;
import ci.atosdigitalacademy.cargo.models.Role;
import ci.atosdigitalacademy.cargo.services.RoleService;
import ci.atosdigitalacademy.cargo.services.dto.RoleDTO;
import ci.atosdigitalacademy.cargo.services.mapper.RoleMapper;
import ci.atosdigitalacademy.cargo.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
 @RequiredArgsConstructor
 @Service
public class RoleServiceImpl implements RoleService {

     private  final RoleRepository roleRepository;
     private  final RoleMapper roleMapper;

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        log.debug("Request to save role: {}", roleDTO);
        Role role = roleMapper.toEntity(roleDTO);
        role = roleRepository.save(role);
        return roleMapper.toDto(role);
    }

    @Override
    public RoleDTO update(RoleDTO roleDTO) {
        log.debug("Request to update role user: {}", roleDTO);
        return findOne(roleDTO.getId()).map(existingRole->{
            existingRole.setRole(roleDTO.getRole());
            return save(existingRole);
        }).orElseThrow(() -> new IllegalArgumentException("Role not found"));
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete role user:{}",id);
        roleRepository.deleteById(id);
    }


    @Override
    public Optional<RoleDTO> findByRole(String roleUser) {
        log.debug("Request to get role: {} by role",roleUser);
        return roleRepository.findByRole(roleUser).map(role->{
            return  roleMapper.toDto(role);
        });
    }


    @Override
    public List<RoleDTO> findAll() {
        log.debug("Request to find all roles");
        return roleRepository.findAll().stream().map(role -> {
            return roleMapper.toDto(role);
        }).toList();
    }

    @Override
    public Optional<RoleDTO> findOne(Long id) {
        log.debug("Request to get role user: {}", id);
        return roleRepository.findById(id).map(role -> {
            return roleMapper.toDto(role);
        });

    }

    @Override
    public Optional<RoleDTO> findBySlug(String slug) {
        log.debug("Request to get role by slug: {}", slug);
        return roleRepository.findBySlug(slug).map(role -> {
            return roleMapper.toDto(role);
        });
    }

    @Override
    public RoleDTO saveRole(RoleDTO roleDTO) {
        log.debug("Request to save role: {} with slug", roleDTO);
        final String slug = SlugifyUtils.generate(String.valueOf(roleDTO.getRole()));
        roleDTO.setSlug(slug);
        return save(roleDTO);
    }

    @Override
    public RoleDTO updateTotal(RoleDTO roleDTO, Long id) {
        log.debug("Request to update total role: {} with id: {}", roleDTO, id);
        roleDTO.setId(id);
        return update(roleDTO);
    }

    @Override
    public void initRoles(List<RoleDTO> roleUsers) {
        log.debug("Request to init roles {}", roleUsers);
        List<RoleDTO> roles = findAll();
        if (roles.isEmpty() || roles.size() < roleUsers.size()){
            roleUsers.forEach(role->{
                saveRole(role);
            });
        }
    }
}
