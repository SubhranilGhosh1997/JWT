package com.jwtExample.JWTexample.Controller;

import com.jwtExample.JWTexample.Model.Management;
import com.jwtExample.JWTexample.Service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/management")
@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
public class ManagementController {

    @Autowired
    private ManagementService managementService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin:post','manager:post')")
    public ResponseEntity<Management> createManagement(@RequestBody Management management) {
        Management createdManagement = managementService.createManagement(management);
        return new ResponseEntity<>(createdManagement, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('admin:get','manager:get')")
    public ResponseEntity<Management> getManagementById(@PathVariable Long id) {
        Management management = managementService.getManagementById(id);
        if (management != null) {
            return new ResponseEntity<>(management, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:get','manager:get')")
    public ResponseEntity<List<Management>> getAllManagements() {
        List<Management> managements = managementService.getAllManagements();
        return new ResponseEntity<>(managements, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('admin:put','manager:put')")
    public ResponseEntity<Management> updateManagement(@PathVariable Long id, @RequestBody Management management) {
        Management updatedManagement = managementService.updateManagement(id, management);
        if (updatedManagement != null) {
            return new ResponseEntity<>(updatedManagement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('admin:delete','manager:delete')")
    public ResponseEntity<Void> deleteManagement(@PathVariable Long id) {
        managementService.deleteManagement(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
