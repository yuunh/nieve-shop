package com.nieve.service;

import com.nieve.entity.FileEntity;
import com.nieve.entity.ReviewEntity;
import com.nieve.model.Review;
import com.nieve.repository.FileRepository;
import com.nieve.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    @Autowired private ReviewRepository reviewRepository;
    @Autowired private FileRepository fileRepository;

    public List<Review> getReviewList() {

        List<ReviewEntity> reviewList = reviewRepository.findAll();
        List<Review> reviews = new ArrayList<>();
        for (ReviewEntity re : reviewList) {
            FileEntity fe = re.getFile();
            Review r = Review.builder()
                    .reviewTitle(re.getReviewTitle())
                    .reviewContent(re.getReviewContent())
                    .fileName(fe.getChangeName())
                    .build();
            reviews.add(r);
        }

        return reviews;
    }

    public void addReview(Review review) {
        FileEntity file = fileRepository.findById(review.getFileNo()).orElseThrow();
        ReviewEntity e = ReviewEntity.builder()
                .reviewTitle(review.getReviewTitle())
                .reviewContent(review.getReviewContent())
                .reviewStar(review.getReviewStar())
                .file(file)
                .build();
        reviewRepository.save(e);
    }
}
