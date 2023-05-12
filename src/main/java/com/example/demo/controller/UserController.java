package com.example.demo.controller;
import com.example.demo.entity.User;
import com.example.demo.entity.UserImage;
import com.example.demo.repository.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
        String regex = ".*\\.(jpg|jpeg|png|webp|avif|gif|svg)$";

        if(file.getOriginalFilename().matches(regex)){
            UserImage userImage = new UserImage(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes());
        return userImage;
        }
        else throw new IOException();
    }

    @GetMapping("/{id}")
    String getUsername(@PathVariable Long id)  {
        Optional <User> user = userRepository.findById(id);
        return user.get().getUsername();
    }
    @GetMapping("/{id}/image")
    ResponseEntity<byte[]> getUserimage(@PathVariable Long id) {
        Optional <User> user = userRepository.findById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", user.get().getUserImage().getType());
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(user.get().getUserImage().getPicByte());
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    void updateUserById(@PathVariable long id, @RequestPart User user)  {
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
