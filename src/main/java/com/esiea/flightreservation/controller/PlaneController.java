package com.esiea.flightreservation.controller;

import com.esiea.flightreservation.model.Plane;
import com.esiea.flightreservation.service.PlaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plane")
@Validated
public class PlaneController {

    private final PlaneService planeService;

    @GetMapping("/all")
    public List<Plane> getAllPlanes() {
        return planeService.getAllPlanes();
    }

    @GetMapping("/{id}")
    public Plane getPlaneById(@PathVariable UUID id) {
        return planeService.getPlaneById(id);
    }

    @PostMapping("/save")
    public Plane savePlane(@RequestBody Plane plane) {
        return planeService.savePlane(plane);
    }

    @PutMapping("/update/{id}")
    public Plane updatePlane(@RequestBody Plane plane, @PathVariable UUID id) {
        return planeService.updatePlane(plane, id);
    }
}
