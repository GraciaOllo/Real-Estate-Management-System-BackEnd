package com.example.demo.service;

import com.example.demo.model.Booking;
import com.example.demo.model.Payment;
import com.example.demo.model.Property;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;

    public BookingService(BookingRepository bookingRepository, PaymentRepository paymentRepository) {
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
    }

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }



    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById((long) Math.toIntExact(id)).orElseThrow(() -> new IllegalArgumentException("Tenant not found"));
        bookingRepository.delete(booking);
    }



    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Payment payBook(Long bookingId, BigDecimal amount) {
        Booking booking = bookingRepository.findById((long) Math.toIntExact(bookingId)).orElseThrow(() -> new IllegalArgumentException("Tenant not found"));
        Property property = booking.getProperty();

        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setBooking(null); // Not associated with a booking, just rent payment
        payment.setDescription("Book payment for property or an Appointment: " + property.getType());

        return paymentRepository.save(payment);
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }
}