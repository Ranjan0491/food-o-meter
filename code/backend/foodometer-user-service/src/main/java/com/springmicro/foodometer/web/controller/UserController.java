package com.springmicro.foodometer.web.controller;

import com.springmicro.foodometer.constants.UserRole;
import com.springmicro.foodometer.constants.UserStatus;
import com.springmicro.foodometer.service.UserService;
import com.springmicro.foodometer.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import java.util.List;

@RestController
@RequestMapping("/food-o-meter-user-service/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveUser(@RequestBody UserDto userDto) {
        UserDto savedUserDto = userService.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<UserDto> getUserByEmailOrPhone(@RequestParam String emailOrPhone) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getUserByEmailOrPhone(emailOrPhone));
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

    @PutMapping(value = "/{id}/addresses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveUserAddress(@PathVariable("id") String id, @RequestBody AddressDto addressDto) {
        userService.saveNewAddressForUser(id, addressDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUserDetails(@PathVariable("id") String id, @RequestBody UserDto userDto) {
        userService.updateUserDetails(id, userDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/staffs-by-role/{userRole}")
    public ResponseEntity<List<StaffDto>> getStaffByRole(@PathVariable("userRole")UserRole userRole, @RequestParam UserStatus userStatus) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findStaffsByRole(userRole, userStatus));
    }

    @GetMapping("/staffs-by-role")
    public ResponseEntity<List<StaffDto>> getStaffByRoles(@RequestParam("userRoles")List<UserRole> userRoles) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findStaffsByRoles(userRoles));
    }

    @GetMapping("/staffs-by-id/{id}")
    public ResponseEntity<StaffDto> getStaffById(@PathVariable("id")String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findStaffsById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaffById(@PathVariable String id, @RequestParam String requesterId) {
        userService.deleteStaffById(id, requesterId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginDto loginDto) throws LoginException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.userLogin(loginDto));
    }

    @PutMapping(value = "/{id}/changePassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUserPassword(@PathVariable("id") String id, @RequestBody PasswordUpdateDto passwordUpdateDto) {
        userService.updateUserPassword(id, passwordUpdateDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
