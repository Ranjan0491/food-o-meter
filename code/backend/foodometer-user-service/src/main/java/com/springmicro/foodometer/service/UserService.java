package com.springmicro.foodometer.service;

import com.springmicro.foodometer.constants.UserRole;
import com.springmicro.foodometer.document.User;
import com.springmicro.foodometer.exception.UserException;
import com.springmicro.foodometer.repository.UserRepository;
import com.springmicro.foodometer.web.dto.AddressDto;
import com.springmicro.foodometer.web.dto.StaffDto;
import com.springmicro.foodometer.web.dto.UserDto;
import com.springmicro.foodometer.web.mapper.AddressMapper;
import com.springmicro.foodometer.web.mapper.StaffMapper;
import com.springmicro.foodometer.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;
    private final StaffMapper staffMapper;

    @Transactional
    public UserDto saveUser(UserDto userDtoDto) {
        if(userDtoDto!=null) {
            if(userDtoDto.getAddresses()!=null && userDtoDto.getAddresses().size()>0) {
                userDtoDto.getAddresses().forEach(addressDto -> addressDto.setId(UUID.randomUUID().toString()));
                UserDto savedUserDto = userMapper.userToUserDto(userRepository.save(userMapper.userDtoToUser(userDtoDto)));
                savedUserDto.setPassword(null);
                log.info("User saved : "+savedUserDto);
                return  savedUserDto;
            } else {
                throw new UserException("No address is present for user.");
            }
        } else {
            throw new UserException("User object is null.");
        }
    }

    public UserDto getUserById(String id) {
        if(StringUtils.isNotBlank(id)) {
            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isPresent()) {
                UserDto userDto = userMapper.userToUserDto(optionalUser.get());
                userDto.setPassword(null);
                return userDto;
            } else {
                throw new UserException("No user found with id - " + id);
            }
        } else {
            throw new UserException("User Id is missing");
        }
    }

    public List<AddressDto> getUserAddresses(String id) {
        UserDto userDto = getUserById(id);
        return userDto.getAddresses();
    }

    public AddressDto getUserAddressById(String userId, String addressId) {
        List<AddressDto> addressDtos = getUserAddresses(userId);
        Optional<AddressDto> optionalAddress = addressDtos.stream().filter(addressDto -> addressDto.getId().equals(addressId)).findFirst();
        if(optionalAddress.isPresent()) {
            return optionalAddress.get();
        } else {
            throw new UserException("No Address exists with id - "+addressId);
        }
    }

    public void saveNewAddressForUser(String userId, AddressDto addressDto) {
        UserDto userDto = getUserById(userId);
        if(userDto.getAddresses().stream().anyMatch(address -> {
            if(StringUtils.equalsIgnoreCase(address.getLine1(), addressDto.getLine1()) &&
                    StringUtils.equalsIgnoreCase(address.getLine2(), addressDto.getLine2()) &&
                    StringUtils.equalsIgnoreCase(address.getLine3(), addressDto.getLine3()) &&
                    StringUtils.equalsIgnoreCase(address.getCity(), addressDto.getCity()) &&
                    StringUtils.equalsIgnoreCase(address.getState(), addressDto.getState()) &&
                    StringUtils.equalsIgnoreCase(address.getPinCode(), addressDto.getPinCode())) {
                return true;
            } else {
                return false;
            }
        })) {
            log.info("Address found for User with id : "+userDto);
            throw new UserException("Address already exists - "+addressDto);
        } else {
            log.info("Address not found for User with id : "+userId+" (Saving new address)");
            User user = userRepository.findById(userId).get();
            addressDto.setId(ObjectId.get().toString());
            user.getAddresses().add(addressMapper.addressDtoToAddress(addressDto));
            userRepository.save(user);
        }
    }

    public List<StaffDto> findStaffsByRole(UserRole userRole) {
        if (userRole == UserRole.CHEF || userRole == UserRole.DELIVERY_AGENT) {
            return userRepository.findAllByUserRole(userRole).stream().map(user -> staffMapper.userToStaffDto(user)).collect(Collectors.toList());
        } else {
            throw new UserException("All user details cannot be fetched. Only staff details can be fetched.");
        }
    }

    public StaffDto findStaffsById(String id) {
        UserDto userDto = getUserById(id);
        if(userDto != null) {
            if (userDto.getUserRole() == UserRole.CHEF || userDto.getUserRole() == UserRole.DELIVERY_AGENT) {
                return staffMapper.userToStaffDto(userMapper.userDtoToUser(userDto));
            } else {
                throw new UserException("All user details cannot be fetched. Only staff details can be fetched.");
            }
        } else {
            return null;
        }
    }

    public void updateUserDetails(String id, UserDto userDto) {
        log.info(userDto.toString());
        UserDto userDtoFromDB = getUserById(id);
        if(userDtoFromDB != null) {
            userDtoFromDB.setDob(userDto.getDob());
            userDtoFromDB.setEmail(userDto.getEmail());
            userDtoFromDB.setFirstName(userDto.getFirstName());
            userDtoFromDB.setLastName(userDto.getLastName());
            userDtoFromDB.setPhone(userDto.getPhone());
            userRepository.save(userMapper.userDtoToUser(userDtoFromDB));
        } else {
            throw new UserException("User could not be found");
        }
    }
}
