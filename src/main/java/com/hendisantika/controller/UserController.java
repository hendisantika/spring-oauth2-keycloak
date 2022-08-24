package com.hendisantika.controller;

import com.hendisantika.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-oauth2-keycloak
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 8/24/22
 * Time: 20:42
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("me")
    public UserDto getMe() {
        return new UserDto(1L, "janedoe", "Doe", "Jane", "jane.doe@yopmail.com");
    }
}
