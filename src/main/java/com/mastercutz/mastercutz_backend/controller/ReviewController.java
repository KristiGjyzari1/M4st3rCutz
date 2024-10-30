package com.mastercutz.mastercutz_backend.controller;

import com.mastercutz.mastercutz_backend.dto.ReviewDto;
import com.mastercutz.mastercutz_backend.model.Barber;
import com.mastercutz.mastercutz_backend.model.Client;
import com.mastercutz.mastercutz_backend.model.Review;
import com.mastercutz.mastercutz_backend.repository.BarberRepository;
import com.mastercutz.mastercutz_backend.repository.ClientRepository;
import com.mastercutz.mastercutz_backend.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BarberRepository barberRepository;

    @PostMapping("/add")
    public ResponseEntity<Review> addReview(@RequestBody ReviewDto reviewDto, @RequestParam Long clientId, @RequestParam Long barberId) {
        // Pse duhet të merrni klientin dhe berberin nga ID-të e dhëna
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
        Barber barber = barberRepository.findById(barberId)
                .orElseThrow(() -> new IllegalArgumentException("Barber not found"));

        Review review = reviewService.addReview(client, barber, reviewDto.getRating(), reviewDto.getComment());
        return ResponseEntity.ok(review);
    }

    @GetMapping("/barber/{barberId}")
    public List<Review> getReviewsForBarber(@PathVariable Long barberId) {
        Barber barber = barberRepository.findById(barberId)
                .orElseThrow(() -> new IllegalArgumentException("Barber not found"));
        return reviewService.getReviewsForBarber(barber);
    }
}
