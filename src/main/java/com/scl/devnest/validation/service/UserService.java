package com.scl.devnest.validation.service;

import com.scl.devnest.validation.dto.UserDto;
import com.scl.devnest.validation.entity.User;
import com.scl.devnest.validation.exception.DtoNotFoundException;
import com.scl.devnest.validation.exception.DuplicateEmailException;
import com.scl.devnest.validation.exception.EntityNotFoundException;
import com.scl.devnest.validation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserDto> getAllUsers() {
        return repository.findAll().stream()
                .map(e -> convertToDto(e))
                .collect(Collectors.toList());
    }

    public UserDto findById(String id) {
        return repository.findById(id)
                .map(e -> convertToDto(e))
                .orElseThrow(() -> new EntityNotFoundException("Could not find a User entity with the ID = " + id));
    }

    public UserDto add(UserDto dto) {
        if(dto == null) {
            throw new DtoNotFoundException("User data not found");
        }

        if(repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new DuplicateEmailException("Email " + dto.getEmail() + " already in use");
        }

        User entity = convertToEntity(dto);
        entity = repository.save(entity);

        return convertToDto(entity);
    }

    private UserDto convertToDto(User e) {
        return UserDto.build(e.getId(), e.getFirstName(), e.getLastName(),
                e.getPhoneNumber(), e.getEmail(), null, e.getAge(),
                e.getEnrolledDate(), e.getRole());
    }

    private User convertToEntity(UserDto d) {
        return User.build(d.getId(), d.getFirstName(), d.getLastName(),
                d.getPhoneNumber(), d.getEmail(), d.getPassword(), d.getAge(),
                d.getEnrolledDate(), d.getRole());
    }
}
