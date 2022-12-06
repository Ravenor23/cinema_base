package com.kata.cinema.base.webapp.controllers.admin;

import com.kata.cinema.base.models.dto.request.AddressRequestDto;
import com.kata.cinema.base.models.dto.response.AddressResponseDto;
import com.kata.cinema.base.service.dto.AddressDtoService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/address")
public class AdminAddressRestController {

    private final AddressDtoService addressDtoService;

    public AdminAddressRestController(AddressDtoService addressDtoService) {
        this.addressDtoService = addressDtoService;
    }

    @GetMapping
    public ResponseEntity<List<AddressResponseDto>> getAddresses() {
        return new ResponseEntity<>(addressDtoService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AddressRequestDto> addAddress(@RequestBody AddressRequestDto addressRequestDto) {
        addressDtoService.save(addressRequestDto);
        return new ResponseEntity<>(addressRequestDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressRequestDto> updateAddress(@RequestBody AddressRequestDto addressRequestDto,
                                                           @PathVariable Long id) {
        addressDtoService.update(id, addressRequestDto);
        return new ResponseEntity<>(addressRequestDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id) {
        addressDtoService.delete(id);
        return ResponseEntity.ok("deleted");
    }
}
