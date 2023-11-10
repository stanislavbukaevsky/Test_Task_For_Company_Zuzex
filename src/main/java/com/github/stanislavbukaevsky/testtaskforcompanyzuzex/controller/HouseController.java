package com.github.stanislavbukaevsky.testtaskforcompanyzuzex.controller;

import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.entity.House;
import com.github.stanislavbukaevsky.testtaskforcompanyzuzex.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/homes")
@RequiredArgsConstructor
public class HouseController {
    private final HouseService houseService;

    @PostMapping("/add")
    public ResponseEntity<House> addHouse(@RequestBody House house) {
        House result = houseService.addHouse(house);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add/user-house")
    public ResponseEntity<String> addUserToHouse(@RequestParam String email, @RequestParam String address) {
        String result = houseService.addUserToHouse(email, address);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/find")
    public ResponseEntity<House> findHouseByAddress(@RequestParam String address) {
        House house = houseService.findHouseByAddress(address);
        return ResponseEntity.ok(house);
    }
}
