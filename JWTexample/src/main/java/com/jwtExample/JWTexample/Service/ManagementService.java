package com.jwtExample.JWTexample.Service;

import com.jwtExample.JWTexample.Model.Management;
import com.jwtExample.JWTexample.Repository.ManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagementService {

    @Autowired
    ManagementRepository managementRepository;

    public List<Management> getAllManagements() {
        return managementRepository.findAll();
    }

    public Management getManagementById(Long id) {
        Optional<Management> adminOptional = managementRepository.findById(id);
        return adminOptional.orElse(null);
    }

    public Management createManagement(Management management) {
        return managementRepository.save(management);
    }

    public Management updateManagement(Long id, Management management) {
        if (managementRepository.existsById(id)) {
            management.setId(id);
            return managementRepository.save(management);
        }
        return null;
    }

    public void deleteManagement(Long id) {
        managementRepository.deleteById(id);
    }
}
