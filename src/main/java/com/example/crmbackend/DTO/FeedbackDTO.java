package com.example.crmbackend.DTO;

import com.example.crmbackend.Model.Feedback;
import com.example.crmbackend.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDTO {

    private Integer Id;

    private String feedbackDesciption;

    private int rating;


    public static FeedbackDTO fromEntity(Feedback feedback) {

        if (feedback == null) {

            //TODO throw an exception
        }

        return FeedbackDTO.builder()
                .Id(feedback.getId())
                .feedbackDesciption(feedback.getFeedbackDesciption())
                .rating(feedback.getRating())
                .build();

    }

    public static Feedback toEntity(FeedbackDTO feedbackDTO) {
        if (feedbackDTO == null) {

            //TODO throw an exception
        }

        Feedback feedback = new Feedback();
        feedback.setId(feedbackDTO.getId());
        feedback.setFeedbackDesciption(feedbackDTO.getFeedbackDesciption());
        feedback.setRating(feedbackDTO.getRating());
        return feedback;
    }

}
