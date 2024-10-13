package ci.atosdigitalacademy.cargo.repositories;

import ci.atosdigitalacademy.cargo.models.TypePayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypePaymentRepository extends JpaRepository<TypePayment, Long> {
   Optional<TypePayment> findTypePaymentBySlug (String slug);
}
