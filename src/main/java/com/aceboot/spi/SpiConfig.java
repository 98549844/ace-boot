package com.aceboot.spi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpiConfig {

    /**
     * @return
     */
    @Bean
    public SayHello spiSayHello() {
        return new SayHello();
    }
}
