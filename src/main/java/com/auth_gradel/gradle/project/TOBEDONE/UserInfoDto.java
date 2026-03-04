//package com.auth_gradel.gradle.project.TOBEDONE;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Pattern;
//import jakarta.validation.constraints.Size;
//
//public class UserInfoDto {
//
//    @NotBlank(message = "Username required")
//    private String username;
//
//    @Email(message = "Invalid email format")
//    @NotBlank(message = "Email required")
//    private String email;
//
//    @NotBlank(message = "Password required")
//    @Size(min = 8, message = "Password must be at least 8 characters")
//    @Pattern(
//            regexp =
//                    "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
//            message =
//                    "Password must contain uppercase, lowercase, digit and special character"
//    )
//    private String password;
//}
//
////controller
//@PostMapping("/signup")
//public ResponseEntity<?> signup(
//        @Valid @RequestBody UserInfoDto userInfoDto)
//{
//    Boolean result = userService.signupUser(userInfoDto);
//    return ResponseEntity.ok(result);
//}
//
////service
////sign up
//public Boolean signupUser(UserInfoDto userInfoDto)
//{
//    if(userRepository
//            .findByEmail(userInfoDto.getEmail())
//            .isPresent())
//    {
//        return false;
//    }
//
//    userInfoDto.setPassword(
//            passwordEncoder.encode(
//                    userInfoDto.getPassword()));
//
//    String userId = UUID.randomUUID().toString();
//
//    userRepository.save(
//            new UserInfo(
//                    userId,
//                    userInfoDto.getUsername(),
//                    userInfoDto.getPassword(),
//                    new HashSet<>()
//            )
//    );
//
//    return true;
//}