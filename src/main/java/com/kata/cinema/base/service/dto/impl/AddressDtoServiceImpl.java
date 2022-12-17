package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.mappers.AddressMapper;
import com.kata.cinema.base.models.dto.request.AddressRequestDto;
import com.kata.cinema.base.models.dto.response.AddressResponseDto;
import com.kata.cinema.base.models.entity.Address;
import com.kata.cinema.base.repositories.AddressRepository;
import com.kata.cinema.base.service.dto.AddressDtoService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class AddressDtoServiceImpl implements AddressDtoService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressDtoServiceImpl(AddressRepository addressRepository,
                                 AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public List<AddressResponseDto> getAll() {
        return addressMapper.modelsToDTO(addressRepository.findAll());
    }

    @Override
    public void save(AddressRequestDto addressRequestDto) {
        addressRepository.save(addressMapper.toEntity(addressRequestDto));
    }

    @Override
    public void update(Long id, AddressRequestDto addressRequestDto) {
        if (addressRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException();
        }
        Address address = addressMapper.toEntity(addressRequestDto);
        address.setId(id);
        addressRepository.save(address);
    }

    @Override
    public void delete(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(NoSuchElementException::new);
        addressRepository.delete(address);
    }
}
