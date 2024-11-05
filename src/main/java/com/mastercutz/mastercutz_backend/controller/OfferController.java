package com.mastercutz.mastercutz_backend.controller;

import com.mastercutz.mastercutz_backend.model.Offer;
import com.mastercutz.mastercutz_backend.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offers")
public class OfferController {
    @Autowired
    private OfferService offerService;

    @GetMapping("getOffers")
    public List<Offer> getOffers() {
        return offerService.getActiveOffers();
    }

    @PostMapping
    public Offer createOffer(@RequestBody Offer offer) {
        return offerService.createOffer(offer);
    }

    // Rruga për përditësimin e ofertave
    @PutMapping("/{id}")
    public Offer updateOffer(@PathVariable Long id, @RequestBody Offer updatedOffer) {
        return offerService.updateOffer(id, updatedOffer);
    }

    // Rruga për fshirjen e ofertave
    @DeleteMapping("/{id}")
    public void deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);
    }
}