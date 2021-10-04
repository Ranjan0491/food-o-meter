package com.springmicro.foodometer.web.controller;

import com.springmicro.foodometer.constants.UserRole;
import com.springmicro.foodometer.service.UserService;
import com.springmicro.foodometer.web.dto.AddressDto;
import com.springmicro.foodometer.web.dto.StaffDto;
import com.springmicro.foodometer.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food-o-meter-user-service/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody UserDto userDto) {
        UserDto savedUserDto = userService.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getUserById(id));
    }

    @GetMapping("/{id}/addresses")
    public ResponseEntity<List<AddressDto>> getUserAddress(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getUserAddresses(id));
    }

    @GetMapping("/{userId}/addresses/{addressId}")
    public ResponseEntity<AddressDto> getUserAddressById(@PathVariable("userId") String userId, @PathVariable("addressId") String addressId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getUserAddressById(userId, addressId));
    }

    @PutMapping("/{id}/addresses")
    public ResponseEntity<Void> saveUserAddress(@PathVariable("id") String id, @RequestBody AddressDto addressDto) {
        userService.saveNewAddressForUser(id, addressDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/staffs-by-role/{userRole}")
    public ResponseEntity<List<StaffDto>> getStaffByRole(@PathVariable("userRole")UserRole userRole) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findStaffsByRole(userRole));
    }

    @GetMapping("/staffs-by-id/{id}")
    public ResponseEntity<StaffDto> getStaffById(@PathVariable("id")String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findStaffsById(id));
    }
}
