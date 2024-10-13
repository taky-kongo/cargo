package ci.atosdigitalacademy.cargo.web.resource;

import ci.atosdigitalacademy.cargo.services.ClientService;
import ci.atosdigitalacademy.cargo.services.dto.ClientDTO;
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

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/clients")
public class ClientResource {

    private final ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Request to save client")
    @Operation(summary = "Save a new client", description = "This endpoint allow to save client")
    public ClientDTO save(@RequestBody ClientDTO client) {
        log.debug("Rest request to save client : {}", client);
        return clientService.saveClient(client);
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get client by id"),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> getClientById(
            @Parameter(required = true, description = "ID of client to be retrieved")
            @PathVariable Long id) {
        log.debug("Rest request to get client by id : {}", id);
        Optional<ClientDTO> client = clientService.findOne(id);
        if (client.isPresent()) {
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Id of client not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/slug/{slug}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get client by slug"),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> getClientBySlug(
            @Parameter(required = true, description = "Slug of to be retrieved")
            @PathVariable String slug) {
        log.debug("Rest request to get client by slug : {}", slug);
        Optional<ClientDTO> client = clientService.findBySlug(slug);
        if (client.isPresent()) {
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Slug of client not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Request to get all clients")
    @Operation(summary = "Get all clients", description = "This endpoint allow to get all clients")
    public List<ClientDTO> getAllClients() {
        log.debug("Rest request to get all clients");
        return clientService.findAll();
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Request to update client")
    @Operation(summary = "Update client", description = "This endpoint allow to update client")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO update(@RequestBody ClientDTO client, @PathVariable Long id) {
        log.debug("Rest request to update client : {}", client);
        return clientService.update(client, id);
    }

    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Request to partial update client")
    @Operation(summary = "Update client", description = "This endpoint allow to partial update client")
    public ClientDTO partialUpdate(@PathVariable Long id, @RequestBody ClientDTO client) {
        log.debug("Rest request to partial update client : {}", client);
        return clientService.partialUpdate(client, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Request to delete client")
    @Operation(summary = "Delete client", description = "This endpoint allow delete client")
    public void delete(@PathVariable Long id) {
        log.debug("Rest request to delete client : {}", id);
        clientService.delete(id);
    }
}
