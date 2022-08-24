package com.hendisantika;

import dasniko.testcontainers.keycloak.KeycloakContainer;
import io.restassured.RestAssured;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-oauth2-keycloak
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 8/24/22
 * Time: 21:01
 * To change this template use File | Settings | File Templates.
 */
@ContextConfiguration(initializers = {KeycloakTestContainers.Initializer.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class KeycloakTestContainers {

    static final KeycloakContainer keycloak = new KeycloakContainer().withRealmImportFile("keycloak/realm-export.json");
    private static final Logger LOGGER = LoggerFactory.getLogger(KeycloakTestContainers.class.getName());
    @LocalServerPort
    private int port;

    @PostConstruct
    public void init() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    protected String getJaneDoeBearer() {

        try {
            URI authorizationURI = new URIBuilder(keycloak.getAuthServerUrl() + "/realms/PowerRanger/protocol/openid-connect/token").build();
            WebClient webclient = WebClient.builder()
                    .build();
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.put("grant_type", Collections.singletonList("password"));
            formData.put("client_id", Collections.singletonList("login-app"));
            formData.put("username", Collections.singletonList("jane.doe@yopmail.com"));
            formData.put("password", Collections.singletonList("s3cr3t"));

            String result = webclient.post()
                    .uri(authorizationURI)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            JacksonJsonParser jsonParser = new JacksonJsonParser();

            return "Bearer " + jsonParser.parseMap(result)
                    .get("access_token")
                    .toString();
        } catch (URISyntaxException e) {
            LOGGER.error("Can't obtain an access token from Keycloak!", e);
        }

        return null;
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            keycloak.start();
            TestPropertyValues.of("keycloak.auth-server-url=" + keycloak.getAuthServerUrl())
                    .applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
