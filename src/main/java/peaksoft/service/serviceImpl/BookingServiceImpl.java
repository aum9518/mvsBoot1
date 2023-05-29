package peaksoft.service.serviceImpl;

import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Booking;
import peaksoft.exceptions.MyException;
import peaksoft.repostiory.BookingRepository;
import peaksoft.service.BookingService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repository;
    @Override
    public List<Booking> getAllBookings() {
        return repository.findAll();
    }

    @Override
    public void save(Booking booking) {
        repository.save(booking);
    }

    @Override
    public void deleteById(Long id) {
        try{
            if (repository.existsById(id)){
                repository.deleteById(id);
            }else throw new MyException("Booking with id: + "+ id + "is not found");
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateBooking(Long id, Booking booking) {
            try{
                Booking booking1 = repository.findById(id).orElseThrow(() -> new MyException("Booking with id: + " + id + "is not found"));
                booking1.setCustomerId(booking.getCustomerId());
                booking1.setHouseId(booking.getHouseId());
                repository.save(booking1);
            }catch (MyException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Booking getBookingById(Long id) {
        try{
            repository.findById(id).orElseThrow(()->new MyException("Booking with id: + " + id + "is not found"));
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
