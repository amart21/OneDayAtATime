package org.wcci.apimastery;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.wcci.apimastery.Repositories.EmotionRepository;
import org.wcci.apimastery.Repositories.RatingRepository;
import org.wcci.apimastery.Repositories.ResponseRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JpaWiringTest {
    @Autowired
    private EmotionRepository emotionRepo;
    @Autowired
    private RatingRepository ratingRepo;
    @Autowired
    private ResponseRepository responseRepo;
    @Autowired
    private TestEntityManager entityManager;



    @Test
    public void ratingShouldHaveEmotion(){
        Emotion testEmotion = new Emotion();
        Rating testRating = new Rating();


        emotionRepo.save(testEmotion);
        ratingRepo.save(testRating);

        entityManager.flush();
        entityManager.clear();

        Emotion retrievedEmotion = emotionRepo.findById(testEmotion.getId()).get();
        Rating retrievedRating = ratingRepo.findById(testRating.getId()).get();

        assertThat(retrievedEmotion.getRating()).contains(retrievedRating);

    }

//    @Test
//    public ratingShouldHaveResponse(){
//        Response testResponse = new Response();
//
//        responseRepo.save(testResponse);
//    }




}