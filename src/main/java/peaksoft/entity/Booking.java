package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "booking_gen")
    @SequenceGenerator(name = "booking_gen",sequenceName = "booking_seq",allocationSize = 1)
    private Long id;
    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @Value("house_id")
    private House houseId;
    @ManyToOne
    @Value("customer_id")
    private Customer customerId;
}
