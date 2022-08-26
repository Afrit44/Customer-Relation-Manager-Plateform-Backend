package com.example.crmbackend.Service.Strategy;

import com.example.crmbackend.DTO.RequestDTO;
import com.example.crmbackend.IService.FlickrIService;
import com.example.crmbackend.Model.Request;
import com.example.crmbackend.Repository.RequestRepository;
import com.example.crmbackend.Service.FlickrService;
import com.example.crmbackend.Service.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("requestStrategy")
@Slf4j
public class SaveRequestPhoto implements Strategy<RequestDTO> {

    @Autowired
    private RequestService requestService;
    @Autowired
    private FlickrService flickrService;
    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    public SaveRequestPhoto(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public RequestDTO savePhoto(Integer requestId, InputStream photo, String title) throws Exception {
        Request request = requestRepository.findById(requestId).get();
        String urlPhoto = this.flickrService.savePhoto(photo, title);
        if (!StringUtils.hasLength(urlPhoto)){
            throw new Exception("Update photo error");
        }
        request.setPhoto(urlPhoto);
        requestRepository.save(request);
        return RequestDTO.fromEntity(request);
    }
}
