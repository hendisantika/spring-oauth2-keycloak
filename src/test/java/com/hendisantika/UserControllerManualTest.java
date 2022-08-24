package com.hendisantika;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-oauth2-keycloak
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 8/24/22
 * Time: 21:02
 * To change this template use File | Settings | File Templates.
 */
public class UserControllerManualTest extends KeycloakTestContainers {

    @Test
    void givenAuthenticatedUser_whenGetMe_shouldReturnMyInfo() {

        given().header("Authorization", getJaneDoeBearer())
                .when()
                .get("/users/me")
                .then()
                .body("username", equalTo("janedoe"))
                .body("lastname", equalTo("Doe"))
                .body("firstname", equalTo("Jane"))
                .body("email", equalTo("jane.doe@yopmail.com"));

    }
}
