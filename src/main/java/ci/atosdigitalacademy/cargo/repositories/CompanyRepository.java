package ci.atosdigitalacademy.cargo.repositories;

import ci.atosdigitalacademy.cargo.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findCompanyBySlug(String slug);
}
