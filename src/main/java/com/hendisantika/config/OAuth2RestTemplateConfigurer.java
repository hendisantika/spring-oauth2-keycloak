package com.hendisantika.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

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
}
