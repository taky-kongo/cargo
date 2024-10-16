package ci.atosdigitalacademy.cargo.models;

import ci.atosdigitalacademy.cargo.models.enumeration.ReservationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservations")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_reservation")
    private LocalDate dateReservation;

    @Enumerated(value = EnumType.STRING)
    private ReservationType status;

    private String slug;

    @ManyToOne
    private Client client;

    @ManyToOne
    @JoinColumn(name = "voyage_id")
    private Voyage voyage;

    @OneToOne
    private Payment payment;
}