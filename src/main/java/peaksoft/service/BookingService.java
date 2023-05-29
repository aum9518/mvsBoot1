package peaksoft.service;

import peaksoft.entity.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> getAllBookings();
    void save(Booking booking);
    void deleteById(Long id);
    void updateBooking(Long id,Booking booking);
    Booking getBookingById(Long id);
}
