package com.hendisantika.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-oauth2-keycloak
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 8/24/22
 * Time: 18:36
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@Conditional(value = {ServiceAccountEnabled.class})
public class OAuth2RestTemplateConfigurer {

    private static final Logger LOG = LoggerFactory.getLogger(OAuth2RestTemplateConfigurer.class);

    @Bean
    public OAuth2RestTemplate oauth2RestTemplate(OAuth2ProtectedResourceDetails details) {
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(details);

        LOG.debug("Begin OAuth2RestTemplate: getAccessToken");
        /* To validate if required configurations are in place during startup */
        oAuth2RestTemplate.getAccessToken();
        LOG.debug("End OAuth2RestTemplate: getAccessToken");
        return oAuth2RestTemplate;
    }
}
