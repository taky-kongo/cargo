package ci.atosdigitalacademy.cargo.services.Impl;

import ci.atosdigitalacademy.cargo.repository.RoleRepository;
import ci.atosdigitalacademy.cargo.models.Role;
import ci.atosdigitalacademy.cargo.services.RoleService;
import ci.atosdigitalacademy.cargo.services.dto.ReservationDTO;
import ci.atosdigitalacademy.cargo.services.dto.RoleDTO;
import ci.atosdigitalacademy.cargo.services.mapper.RoleMapper;
import ci.atosdigitalacademy.cargo.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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
     private final RoleService roleService;

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        log.debug("Request to update role user: {}", roleDTO);
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
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public void delete(Long id) {log.debug("Request to delete role user:{}",id); roleRepository.deleteById(id);}


    @Override
    public Set<RoleDTO> findByRole(String roleUser) {
        return roleRepository.findByRole(roleUser).stream().map(role->{
            return  roleMapper.toDto(role);
        }).collect(Collectors.toSet());
    }


    @Override
    public List<RoleDTO> findAll() {
        log.debug("Request to find");
        return roleRepository.findAll().stream().map(role -> {
            return roleMapper.toDto(role);
        }).toList();

    }

    @GetMapping
    public List<RoleDTO> getAll(){
        log.debug("REST request to get All");
        return roleService.findAll();
    }

    @Override
    public Optional<RoleDTO> findOne(Long id) {
        log.debug("Request to get role user: {}", id);
        return roleRepository.findById(id).map(role -> {
            return roleMapper.toDto(role);
        });

    }

    @Override
    public RoleDTO saveRole(RoleDTO roleDTO) {
        final String slug = SlugifyUtils.generate(roleDTO.getRole().toString());
        roleDTO.setSlug(slug);
        return save(roleDTO);
    }

    @Override
    public RoleDTO updateTotal(RoleDTO roleDTO, Long id) {
        roleDTO.setId(id);
        return update(roleDTO);
    }


}
