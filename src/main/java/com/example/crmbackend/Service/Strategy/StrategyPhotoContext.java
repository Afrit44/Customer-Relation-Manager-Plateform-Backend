package com.example.crmbackend.Service.Strategy;

import com.flickr4java.flickr.FlickrException;
import jdk.jfr.SettingDefinition;
import lombok.Setter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class StrategyPhotoContext {

    private BeanFactory beanFactory;
    private Strategy strategy;
    @Setter
    private String context;

    @Autowired
    public StrategyPhotoContext(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object savePhoto(Context context, Integer id, InputStream photo, String title) throws Exception {
        determinContext(context);
        return strategy.savePhoto(id, photo, title);
    }
    private void determinContext(Context context) throws Exception {
        final String beanName=context+"Strategy";
        //In case we have multiple context (not our case) we use switch case instead of If and we implement Bean in each Service like we have done with SaveRequestPhoto sadiki.
        if (context.toString().equals("request")){
            strategy = beanFactory.getBean(beanName, SaveRequestPhoto.class);
        }else{
            throw new Exception("Context is wrong Sadiki");
        }
    }
}
