package com.example.demo.controller;
import com.example.demo.entity.User;
import com.example.demo.entity.UserImage;
import com.example.demo.repository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RequestMapping("api/users")
@RestController
public class UserController{
    private final UserRepository userRepository;

    public UserController (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    void addUser(@RequestPart("user") User user,
                 @RequestPart("imageFile")MultipartFile file){
        try {
            var userImage = uploadImage(file);
            user.setUserImage(userImage);
            userRepository.save(user);
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
    String getUsername(@PathVariable Long id)  {
        Optional <User> user = userRepository.findById(id);
        return user.get().getUsername();
    }

    @PutMapping("/{id}")
    void updateUserById(@PathVariable long id, @RequestBody User user)  {
        var updateUser = userRepository.findById(id).orElseThrow();
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
