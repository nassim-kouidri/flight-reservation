package com.esiea.flightreservation.service;

import com.esiea.flightreservation.model.Plane;
import com.esiea.flightreservation.repository.PlaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlaneService {

    private final PlaneRepository planeRepository;

    public List<Plane> getAllPlanes() {
        return planeRepository.findAll();
    }

    public Plane getPlaneById(UUID id) {
        return planeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("Plane with id '%s' not found", id)));
    }

    public Plane savePlane(Plane plane) {
        return planeRepository.save(plane);
    }

    public Plane updatePlane(Plane planeRequest, UUID id) {
        Plane plane = getPlaneById(id);
        plane.setBrand(planeRequest.getBrand());
        plane.setModel(planeRequest.getModel());
        plane.setCreationYear(planeRequest.getCreationYear());

        return plane;
    }
}
