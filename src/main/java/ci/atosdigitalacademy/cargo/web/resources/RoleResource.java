package ci.atosdigitalacademy.cargo.web.resources;

import ci.atosdigitalacademy.cargo.repository.RoleRepository;
import ci.atosdigitalacademy.cargo.services.RoleService;
import ci.atosdigitalacademy.cargo.services.dto.ReservationDTO;
import ci.atosdigitalacademy.cargo.services.dto.RoleDTO;
import ci.atosdigitalacademy.cargo.services.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/role")
public class RoleResource {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;
    private  final RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleDTO> save(@RequestBody RoleDTO roleDTO){
        log.debug("REST request to save role{}", roleDTO);
        RoleDTO role = roleService.saveRole(roleDTO);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    public void deleteRole(@PathVariable Long id) {
        log.info("REST Request to delete role : {}", id);
        roleService.delete(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        log.debug("REST request to delete {}", id);
    }

}
