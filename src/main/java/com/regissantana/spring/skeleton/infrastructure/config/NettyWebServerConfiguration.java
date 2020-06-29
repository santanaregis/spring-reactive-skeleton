package com.regissantana.spring.skeleton.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ContextPathCompositeHandler;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;

@Configuration
public class NettyWebServerConfiguration extends NettyReactiveWebServerFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyWebServerConfiguration.class);

    private final String contextPath;

    public NettyWebServerConfiguration(@Value("${SERVER_CONTEXT_PATH:/}") final String contextPath) {
        this.contextPath = contextPath;
    }

    @Override
    public WebServer getWebServer(HttpHandler httpHandler) {
        LOGGER.info("Configuring context path to {}", contextPath);

        Map<String, HttpHandler> handlerMap = new HashMap<>();
        handlerMap.put(this.contextPath, httpHandler);

        return super.getWebServer(new ContextPathCompositeHandler(handlerMap));
    }

    @Bean
    public ContextPathBeanPostProcessor contextPathBeanPostProcessor(@Value("${SERVER_CONTEXT_PATH:/}") final String contextPath) {
        return new ContextPathBeanPostProcessor(contextPath);
    }

    /**
     * Intercept bean lifecycle and nest it with context path.
     */
    public static class ContextPathBeanPostProcessor implements BeanPostProcessor {

        private final String contextPath;

        public ContextPathBeanPostProcessor(final String contextPath) {
            this.contextPath = contextPath;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (bean instanceof RouterFunction) return RouterFunctions.nest(path(this.contextPath), (RouterFunction) bean);
            else return bean;
        }
    }

}
