package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.model.Payment;
import com.example.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.math.BigDecimal;
import java.util.Optional;

    @RestController
    @RequestMapping("/api/booking")
    public class BookingController {
        @Autowired
        private BookingService bookingService;

        public BookingController(BookingService bookingService) {
            this.bookingService = bookingService;
        }

        @PostMapping
        public ResponseEntity<Booking> createTenant(@RequestBody Booking booking) {
            return ResponseEntity.ok(bookingService.createBooking(booking));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
            bookingService.deleteBooking(id);
            return ResponseEntity.noContent().build();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Optional<Booking>> getBookingById(@PathVariable Long id) {
            return ResponseEntity.ok(bookingService.getBookingById(id));
        }

        @GetMapping
        public ResponseEntity<List<Booking>> getAllTenants() {
            return ResponseEntity.ok(bookingService.getAllBookings());
        }

        @PostMapping("/{id}/pay-book")
        public ResponseEntity<Payment> payBook(@PathVariable Long id, @RequestParam BigDecimal amount) {
            return ResponseEntity.ok(bookingService.payBook(id, amount));
        }
    }

