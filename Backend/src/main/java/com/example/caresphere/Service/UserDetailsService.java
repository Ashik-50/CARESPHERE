package com.example.caresphere.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.caresphere.Model.UserDetails;
import com.example.caresphere.Repository.UserDetailsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public UserDetails saveUserDetails(UserDetails userDetails) {
        return userDetailsRepository.save(userDetails);
    }

    public List<UserDetails> getAllUserDetails() {
        return userDetailsRepository.findAll();
    }

    public List<UserDetails> getDetailsByUsername(String username) {
        return userDetailsRepository.findByUsername(username);
    }

    public UserDetails updateUserDetails(UserDetails userDetails) {
        Optional<UserDetails> existingUserDetails = userDetailsRepository.findById(userDetails.getId());
        if (existingUserDetails.isPresent()) {
            UserDetails updatedUserDetails = existingUserDetails.get();
            updatedUserDetails.setFirstName(userDetails.getFirstName());
            updatedUserDetails.setLastName(userDetails.getLastName());
            updatedUserDetails.setEmail(userDetails.getEmail());
            updatedUserDetails.setPhone(userDetails.getPhone());
            updatedUserDetails.setEmergencyContact(userDetails.getEmergencyContact());
            updatedUserDetails.setAddress(userDetails.getAddress());
            updatedUserDetails.setDateOfBirth(userDetails.getDateOfBirth());
            updatedUserDetails.setAge(userDetails.getAge());
            updatedUserDetails.setGender(userDetails.getGender());
            updatedUserDetails.setIsPregnant(userDetails.getIsPregnant());
            updatedUserDetails.setMonthOfPregnancy(userDetails.getMonthOfPregnancy());
            updatedUserDetails.setExpectedDeliveryDate(userDetails.getExpectedDeliveryDate());
            updatedUserDetails.setConsultingDoctor(userDetails.getConsultingDoctor());
            updatedUserDetails.setHadDiseaseBefore(userDetails.getHadDiseaseBefore());
            updatedUserDetails.setSelectedDisease(userDetails.getSelectedDisease());
            updatedUserDetails.setDiseaseConsultingDoctor(userDetails.getDiseaseConsultingDoctor());
            updatedUserDetails.setMedicalHistory(userDetails.getMedicalHistory());
            updatedUserDetails.setUsername(userDetails.getUsername());
            return userDetailsRepository.save(updatedUserDetails);
        } else {
            throw new RuntimeException("User not found with id: " + userDetails.getId());
        }
    }
}
