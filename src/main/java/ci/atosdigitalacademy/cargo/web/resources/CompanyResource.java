package ci.atosdigitalacademy.cargo.web.resources;

import ci.atosdigitalacademy.cargo.services.CompanyService;
import ci.atosdigitalacademy.cargo.services.dto.CompanyDTO;
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
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/companies")
public class CompanyResource {
    private final CompanyService companyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Request to save company")
    @Operation(summary = "Save a new company", description = "This endpoint allow to save company")
    public CompanyDTO saveCompany(@RequestBody CompanyDTO company) {
        log.debug("Rest request to save Company : {}", company);
        return companyService.saveCompany(company);
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get company by id"),
            @ApiResponse(responseCode = "404", description = "company not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> getCompanyById(
            @Parameter(required = true, description = "ID of company to be retrieved")
            @PathVariable Long id) {
        log.debug("Rest request to get Company by id : {}", id);
        Optional<CompanyDTO> company = companyService.findOne(id);
        if (company.isPresent()) {
            return new ResponseEntity<>(company.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Id of company not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/slug/{slug}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get company by slug"),
            @ApiResponse(responseCode = "404", description = "Company not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> getCompanyBySlug(
            @Parameter(required = true, description = "Slug of to be retrieved")
            @PathVariable String slug) {
        log.debug("Rest request to get company by slug : {}", slug);
        Optional<CompanyDTO> company = companyService.findBySlug(slug);
        if (company.isPresent()) {
            return new ResponseEntity<>(company.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Slug of company not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Request to get all company")
    @Operation(summary = "Get all company", description = "This endpoint allow to get all company")
    public List<CompanyDTO> getAllCompany() {
        log.debug("Rest request to get all company");
        return companyService.findAllCompany();
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Request to update company")
    @Operation(summary = "Update company", description = "This endpoint allow to update company")
    @ResponseStatus(HttpStatus.OK)
    public CompanyDTO updateCompany(@RequestBody CompanyDTO company, @PathVariable Long id) {
        log.debug("Rest request to update Company : {}", company);
        return companyService.updateCompany( company, id);
    }

    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Request to partial update company")
    @Operation(summary = "Update company", description = "This endpoint allow to partial update company")
    public CompanyDTO partialUpdate(@PathVariable Long id, @RequestBody CompanyDTO company) {
        log.debug("Rest request to partial update company : {}", company);
        return companyService.partialUpdate(company, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Request to delete company")
    @Operation(summary = "Delete company", description = "This endpoint allow delete company")
    public void deleteCompany(@PathVariable Long id) {
        log.debug("Rest request to delete comapny : {}", id);
        companyService.deleteCompany(id);
    }
}
