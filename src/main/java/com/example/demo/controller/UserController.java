package com.example.demo.controller;
import com.example.demo.entity.User;
import com.example.demo.entity.UserImage;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@RequestMapping("api/users")
@RestController
public class UserController{
    @Autowired
    private UserService userService;
    private final UserRepository userRepository;

    public UserController (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping(value = {"/addNewUser"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    void addUser(@RequestPart("user") User user,
                 @RequestPart("imageFile")MultipartFile file){
        try {
            var userImage = uploadImage(file);
            user.setUserImage(userImage);
            //userRepository.addUser(user);
            userService.addUser(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public UserImage uploadImage(MultipartFile file) throws IOException {

            UserImage userImage = new UserImage(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
        return userImage;
    }

    @GetMapping("/{id}")
    User getUser(@PathVariable Long id) {
        User user = userRepository.findById(id);
        return user;
    }

    @PutMapping("/{id}")
    void updateUserById(@PathVariable long id, @RequestBody User user)  {
        var updateUser = userRepository.findById(id);
        updateUser.setUsername(user.getUsername());
        updateUser.setAge(user.getAge());
        updateUser.setBiography(user.getBiography());
        updateUser.setUserImage(user.getUserImage());
        userRepository.save(updateUser);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable long id) {
        userRepository.deleteById(id);
    }

}
