package com.mastercutz.mastercutz_backend.services;

import com.mastercutz.mastercutz_backend.model.Barber;
import com.mastercutz.mastercutz_backend.model.Client;
import com.mastercutz.mastercutz_backend.model.Review;
import com.mastercutz.mastercutz_backend.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review addReview(Client client, Barber barber, int rating, String comment) {
        Review review = new Review();
        review.setClient(client);
        review.setBarber(barber);
        review.setRating(rating);
        review.setComment(comment);
        review.setDateTime(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsForBarber(Barber barber) {
        return reviewRepository.findByBarber(barber);
    }
}
