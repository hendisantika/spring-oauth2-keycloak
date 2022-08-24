package com.hendisantika.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-oauth2-keycloak
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 8/24/22
 * Time: 18:40
 * To change this template use File | Settings | File Templates.
 */
@Component
public class SecurityContextUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityContextUtils.class);

    private static final String ANONYMOUS = "anonymous";

    private SecurityContextUtils() {
    }
}
