package com.hendisantika.controller;

import com.hendisantika.config.SecurityContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-oauth2-keycloak
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 8/24/22
 * Time: 18:43
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeRestController {
    @GetMapping(path = "/username")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public ResponseEntity<String> getAuthorizedUserName() {
        return ResponseEntity.ok(SecurityContextUtils.getUserName());
    }

    @GetMapping(path = "/roles")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public ResponseEntity<Set<String>> getAuthorizedUserRoles() {
        return ResponseEntity.ok(SecurityContextUtils.getUserRoles());
    }
}
