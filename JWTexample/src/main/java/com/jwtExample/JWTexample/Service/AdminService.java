package com.jwtExample.JWTexample.Service;

import com.jwtExample.JWTexample.Model.Admin;
import com.jwtExample.JWTexample.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id) {
        Optional<Admin> adminOptional = adminRepository.findById(id);
        return adminOptional.orElse(null);
    }

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Long id, Admin admin) {
        if (adminRepository.existsById(id)) {
            admin.setId(id);
            return adminRepository.save(admin);
        }
        return null;
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}
