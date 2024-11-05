package com.mastercutz.mastercutz_backend.services;

import com.mastercutz.mastercutz_backend.model.Offer;
import com.mastercutz.mastercutz_backend.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OfferService {
    @Autowired
    private OfferRepository offerRepository;

    public List<Offer> getActiveOffers() {
        LocalDate today = LocalDate.now();
        return offerRepository.findAllByStartDateBeforeAndEndDateAfter(today, today);
    }

    public Offer createOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    // Metoda për përditësimin e ofertave
    public Offer updateOffer(Long id, Offer updatedOffer) {
        Offer existingOffer = offerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Offer not found"));
        existingOffer.setTitle(updatedOffer.getTitle());
        existingOffer.setDescription(updatedOffer.getDescription());
        existingOffer.setDiscountPercentage(updatedOffer.getDiscountPercentage());
        existingOffer.setStartDate(updatedOffer.getStartDate());
        existingOffer.setEndDate(updatedOffer.getEndDate());
        return offerRepository.save(existingOffer);
    }

    // Metoda për fshirjen e ofertave
    public void deleteOffer(Long id) {
        if (!offerRepository.existsById(id)) {
            throw new IllegalArgumentException("Offer not found");
        }
        offerRepository.deleteById(id);
    }
}
