package com.hendisantika.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-oauth2-keycloak
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 8/24/22
 * Time: 18:33
 * To change this template use File | Settings | File Templates.
 */
public class JwtAccessTokenCustomizer extends DefaultAccessTokenConverter implements JwtAccessTokenConverterConfigurer {

    private static final Logger LOG = LoggerFactory.getLogger(JwtAccessTokenCustomizer.class);

    private static final String CLIENT_NAME_ELEMENT_IN_JWT = "resource_access";

    private static final String ROLE_ELEMENT_IN_JWT = "roles";

    private final ObjectMapper mapper;

    /* Using spring constructor injection, @Autowired is implicit */
    public JwtAccessTokenCustomizer(ObjectMapper mapper) {
        this.mapper = mapper;
        LOG.info("Initialized {}", JwtAccessTokenCustomizer.class.getSimpleName());
    }
}
