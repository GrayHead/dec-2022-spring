package ua.com.owu.dec2022spring.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    // /av/homer.jpg->path
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // folder qqq
        String path = "file:///" + System.getProperty("user.home") + File.separator + "qqq" + File.separator;
        registry
                // https://localhost:8080/av/img.jpg
                .addResourceHandler("/av/**")
                .addResourceLocations(path);
    }
}
