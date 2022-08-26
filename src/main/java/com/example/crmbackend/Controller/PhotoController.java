package com.example.crmbackend.Controller;


import com.example.crmbackend.Service.Strategy.Context;
import com.example.crmbackend.Service.Strategy.StrategyPhotoContext;
import com.flickr4java.flickr.FlickrException;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/api/photos/")
public class PhotoController {

    private StrategyPhotoContext strategyPhotoContext;

    @Autowired
    public PhotoController(StrategyPhotoContext strategyPhotoContext) {
        this.strategyPhotoContext = strategyPhotoContext;
    }

    @PostMapping("/save/{requestId}/{title}/{context}")
    public Object savePhoto(Context context, Integer requestId, MultipartFile photo, String photoTitle) throws Exception {
        return strategyPhotoContext.savePhoto(context, requestId, photo.getInputStream(), photoTitle);
    }
}