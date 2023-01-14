package com.fikrat.hrms.controller;

import com.fikrat.hrms.dto.city.CityDto;
import com.fikrat.hrms.model.City;
import com.fikrat.hrms.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/city")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @PostMapping
    public ResponseEntity<City> add(@RequestBody CityDto cityDto) {
        return ResponseEntity.ok(cityService.add(cityDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cityService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> update(@PathVariable Long id, @RequestBody CityDto cityDto) {
        return ResponseEntity.ok(cityService.update(id, cityDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(cityService.get(id));
    }

    @GetMapping("list")
    public ResponseEntity<List<CityDto>> getList() {
        return ResponseEntity.ok(cityService.getList());
    }
}
