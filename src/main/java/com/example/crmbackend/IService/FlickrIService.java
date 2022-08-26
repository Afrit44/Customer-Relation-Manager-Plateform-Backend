package com.example.crmbackend.IService;

import com.flickr4java.flickr.FlickrException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

public interface FlickrIService {

    String savePhoto(InputStream photo, String title);

}
