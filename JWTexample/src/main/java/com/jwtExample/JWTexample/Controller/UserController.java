package com.jwtExample.JWTexample.Controller;

import com.jwtExample.JWTexample.Model.UserModel;
import com.jwtExample.JWTexample.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController{

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserModel getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/security-util")
    public String getSecurity() {
//        Test test=new Test();
//        String user = test.getUser();
//        int age = test.getAge();
//        int number = test.getNumber();
//        System.out.println("hi");
        return "hi";
    }

    @PostMapping("/create-user")
    public UserModel createUser(@RequestBody UserModel user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public UserModel updateUser(@PathVariable Long id, @RequestBody UserModel user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/current-user")
    public String getCurrentUser(Principal principal){
        UserDetails userDetails = userService.loadUserByUsername(principal.getName());
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        System.out.println(authorities);
        List<? extends GrantedAuthority> collect = authorities.stream().toList();
        System.out.println(collect);
        return principal.getName();
    }
}
