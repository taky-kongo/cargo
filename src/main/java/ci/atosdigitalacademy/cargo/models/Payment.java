package ci.atosdigitalacademy.cargo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private String slug;

    @Column(name = "date_payment")
    private LocalDate datePayment;

    @ManyToOne
    private TypePayment typePayment;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

}
