package com.example.demo;

import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.Transformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class CloundinaryConfig {
    private Cloudinary cloundinary;
    @Autowired
    public CloundinaryConfig(@Value("${cloud.key}") String key,
            @Value("${cloud.secret}") String secret,
            @Value("${cloud.name}") String cloud){
                cloundinary = Singleton.getCloudinary();
                cloundinary.config.cloudName=cloud;
                cloundinary.config.apiSecret=secret;
                cloundinary.config.apiKey=key;


            }
            public Map upload(Object file, Map options){
            try{
                    return cloundinary.uploader().upload(file, options);
                    } catch (IOException e){
            e.printStackTrace();
            return null;
            }
}
public String createUrl(String name, int width, int height, String action) {
    return cloundinary.url()
            .transformation(new Transformation()
                    .width(width).height(height)
                    .border("2px_solid_black").crop(action))
            .imageTag(name);
}
}
