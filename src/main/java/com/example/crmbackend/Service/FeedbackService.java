package com.example.crmbackend.Service;
import com.example.crmbackend.DTO.CustomerDTO;
import com.example.crmbackend.DTO.FeedbackDTO;
import com.example.crmbackend.DTO.RequestDTO;
import com.example.crmbackend.IService.CustomerIService;
import com.example.crmbackend.IService.FeedbackIService;
import com.example.crmbackend.IService.RequestIService;
import com.example.crmbackend.Model.Customer;
import com.example.crmbackend.Model.Feedback;
import com.example.crmbackend.Model.Request;
import com.example.crmbackend.Repository.CustomerRepository;
import com.example.crmbackend.Repository.FeedbackRepository;
import com.example.crmbackend.Repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@CrossOrigin
public class FeedbackService implements FeedbackIService {

    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RequestRepository requestRepository;

    @Override
    public FeedbackDTO save(FeedbackDTO feedbackDTO){
        return FeedbackDTO.fromEntity(feedbackRepository.save(FeedbackDTO.toEntity(feedbackDTO)));
    }

    @Override
    public FeedbackDTO createFeedback(int customerId, int requestId, FeedbackDTO feedbackDTO){
        Feedback feedback = FeedbackDTO.toEntity(feedbackDTO);
        Customer customer = customerRepository.findById(customerId).get();
        feedback.setCustomer(customer);
        customer.setFeedbackGiven(feedback);

        Request request = requestRepository.findById(requestId).get();
        feedback.setRequest(request);
        request.setRelatedFeedback(feedback);

        FeedbackDTO savedFeedback = FeedbackDTO.fromEntity(feedbackRepository.save(feedback));
        requestRepository.save(request);
        customerRepository.save(customer);
        return savedFeedback;
    }

    @Override
    public List<FeedbackDTO> getAllFeedbacks() {
        return feedbackRepository.findAll().stream().map(FeedbackDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<FeedbackDTO> getAllFeedbacksByCustomer(String email){
        List<Feedback> listFeedback = feedbackRepository.findAll();
        Customer customer = customerRepository.findCustomerByEmail(email);
        List<Feedback> finalList =  new ArrayList<Feedback>();
        for ( Feedback feedback : listFeedback){
            if (feedback.getCustomer()==customer){
                finalList.add(feedback);
            }
        }
        return finalList.stream().map(FeedbackDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public FeedbackDTO getFeedbackById(int id) {
        return FeedbackDTO.fromEntity(feedbackRepository.findById(id).get());
    }

    @Override
    public FeedbackDTO updateFeedbackDescription(int id, String description){
        FeedbackDTO feedbackDTO = getFeedbackById(id);
        feedbackDTO.setFeedbackDesciption(description);
        return save(feedbackDTO);
    }

    @Override
    public FeedbackDTO updateFeedbackRating(int id, int rating){
        FeedbackDTO feedbackDTO = getFeedbackById(id);
        feedbackDTO.setRating(rating);
        return save(feedbackDTO);
    }

    @Override
    public void deleteFeedback(int id) {
        feedbackRepository.deleteById(id);
    }
}
