package ci.atosdigitalacademy.cargo.web.resources;

import ci.atosdigitalacademy.cargo.services.RoleService;
import ci.atosdigitalacademy.cargo.services.dto.RoleDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roles")
public class RoleResource {

    private  final RoleService roleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Request to save role")
    @Operation(summary = "Save a new role", description = "This endpoint allow to save role")
    public RoleDTO save(@RequestBody RoleDTO roleDTO){
        log.debug("REST request to save role {}", roleDTO);
        return roleService.saveRole(roleDTO);
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Request to update role")
    @Operation(summary = "Update role", description = "This endpoint allow to update role")
    @ResponseStatus(HttpStatus.OK)
    public RoleDTO update(@RequestBody RoleDTO roleDTO, @PathVariable Long id){
        log.debug("REST request to update role {}", roleDTO);
        return roleService.updateTotal(roleDTO, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Request to delete role")
    @Operation(summary = "Delete role", description = "This endpoint allow delete role")
    public void delete(@PathVariable Long id) {
        log.info("REST Request to delete role : {}", id);
        roleService.delete(id);
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get role by id"),
            @ApiResponse(responseCode = "404", description = "role not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> getRoleById(
            @Parameter(required = true, description = "ID of role to be retrieved")
            @PathVariable Long id) {
        log.debug("REST request to get one role by id {}", id);
        Optional<RoleDTO> roleDTO = roleService.findOne(id);
        if(roleDTO.isPresent()){
            return new ResponseEntity<>(roleDTO.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("role not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/slug/{slug}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get role by slug"),
            @ApiResponse(responseCode = "404", description = "role not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> getRoleBySlug(
            @Parameter(required = true, description = "Slug of role to be retrieved")
            @PathVariable String slug) {
        log.debug("REST request to get one role by slug {}", slug);
        Optional<RoleDTO> roleDTO = roleService.findBySlug(slug);
        if(roleDTO.isPresent()){
            return new ResponseEntity<>(roleDTO.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("role not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Request to get all roles")
    @Operation(summary = "Get all roles", description = "This endpoint allow to get all roles")
    public List<RoleDTO> getAll(){
        log.debug("REST request to get all roles");
        return roleService.findAll();
    }
}
