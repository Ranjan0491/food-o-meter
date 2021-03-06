package com.springmicro.foodometer.service;

import com.springmicro.foodometer.constants.FoodOrderConstants;
import com.springmicro.foodometer.constants.UserRole;
import com.springmicro.foodometer.constants.UserStatus;
import com.springmicro.foodometer.web.dto.AddressDto;
import com.springmicro.foodometer.web.dto.StaffDto;
import com.springmicro.foodometer.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserLookUpService {
    private final RestTemplate restTemplate;

    public UserDto fetchUserDto(String id) {
        return restTemplate.getForObject("http://" + FoodOrderConstants.FOOD_USER_SERVICE_NAME + "/food-o-meter-user-service/v1/users/" + id, UserDto.class);
    }

    public AddressDto fetchAddressDtoForUser(String userId, String addressId) {
        return restTemplate.getForObject("http://" + FoodOrderConstants.FOOD_USER_SERVICE_NAME + "/food-o-meter-user-service/v1/users/" + userId + "addresses/" + addressId, AddressDto.class);
    }

    public List<StaffDto> fetchStaffsByRole(UserRole userRole) {
        return Arrays.asList(restTemplate.getForObject("http://" + FoodOrderConstants.FOOD_USER_SERVICE_NAME + "/food-o-meter-user-service/v1/users/staffs-by-role/" + userRole + "?userStatus=" + UserStatus.ACTIVE, StaffDto[].class));
    }

    public StaffDto fetchStaffById(String id) {
        return restTemplate.getForObject("http://" + FoodOrderConstants.FOOD_USER_SERVICE_NAME + "/food-o-meter-user-service/v1/users/staffs-by-id/" + id, StaffDto.class);
    }
}
