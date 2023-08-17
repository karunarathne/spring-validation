package com.scl.devnest.validation.controller;

import com.scl.devnest.validation.dto.UserDto;
import com.scl.devnest.validation.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> result = service.getAllUsers();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid @NotNull UserDto dto) {
        UserDto result = service.add(dto);
        URI resourceUri = URI.create("/users/" + result.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(resourceUri);

        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(headers)
                .build();
    }
}
