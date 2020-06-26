package com.regissantana.spring.skeleton;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringSkeletonApplication {

    @Value("${web.client.log-request:false}")
    private boolean logRequest;

    public static void main(String[] args) {
        SpringApplication.run(SpringSkeletonApplication.class, args);
    }

    @Bean(name = "auditingDateTimeProvider")
    public DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(new Date().toInstant().atZone(ZoneId.systemDefault()));
    }

    @ReadingConverter
    public static class ZonedDateTimeReadConverter
            implements Converter<Date, ZonedDateTime> {
        @Override
        public ZonedDateTime convert(Date date) {
            return date.toInstant().atZone(ZoneId.systemDefault());
        }
    }

    @WritingConverter
    public static class ZonedDateTimeWriteConverter
            implements Converter<ZonedDateTime, Date> {
        @Override
        public Date convert(ZonedDateTime date) {
            return Date.from(date.toInstant());
        }
    }

    @Bean
    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new ZonedDateTimeReadConverter());
        converters.add(new ZonedDateTimeWriteConverter());
        return new MongoCustomConversions(converters);
    }

    @Bean
    public WebClient webClient() {
        WebClient.Builder builder = WebClient.builder();
        if(logRequest)
            builder.clientConnector(new ReactorClientHttpConnector(HttpClient.create().wiretap(true)));

        return builder.build();
    }

}
