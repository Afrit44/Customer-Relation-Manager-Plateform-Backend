package com.example.crmbackend.IService;


import com.example.crmbackend.DTO.FeedbackDTO;
import com.example.crmbackend.Model.Feedback;

import java.util.List;

public interface FeedbackIService {

    public FeedbackDTO save(FeedbackDTO feedbackDTO);

    public FeedbackDTO createFeedback(int customerId, int requestId, FeedbackDTO feedbackDTO);

    public List<FeedbackDTO> getAllFeedbacks();

    public List<FeedbackDTO> getAllFeedbacksByCustomer(String email);

    public FeedbackDTO getFeedbackById(int id);

    public FeedbackDTO updateFeedbackDescription(int id, String description);

    public FeedbackDTO updateFeedbackRating(int id, int rating);

    public void deleteFeedback(int id);

}
