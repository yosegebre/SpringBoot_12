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
public class CloudinaryConfig {
    private Cloudinary cloudinary;
    @Autowired
    public CloudinaryConfig(
            @Value("343817447964282") String key,
            @Value("5j1CflYKnYZhK4UvkICyoIhKT24")String secret,
            @Value("dhvr70c9r")String cloud){
        cloudinary = Singleton.getCloudinary();
        cloudinary.config.cloudName=cloud;
        cloudinary.config.apiSecret=secret;
        cloudinary.config.apiKey=key;
    }
    public Map upload(Object file, Map options){
        try{
            return cloudinary.uploader().upload(file, options);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public String createUrl(String name, int width, int heigth, String action){
        return cloudinary.url()
                .transformation(new Transformation()
                        .width(width).height(heigth)
                        .border("2px_solid_black").crop(action))
                .imageTag(name);

    }
}