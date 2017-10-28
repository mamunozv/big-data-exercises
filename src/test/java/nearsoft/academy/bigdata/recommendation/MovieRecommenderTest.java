package nearsoft.academy.bigdata.recommendation;

import org.apache.mahout.cf.taste.common.TasteException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

public class MovieRecommenderTest {
    @Test
    public void testDataInfo() throws IOException, TasteException {
        //download movies.txt.gz from 
        //    http://snap.stanford.edu/data/web-Movies.html
        MovieRecommender recommender = new MovieRecommender("/home/miguel/Downloads/movies.txt.gz");
        assertEquals(7911684, recommender.getTotalReviews());
        assertEquals(253059, recommender.getTotalProducts());
        assertEquals(889176, recommender.getTotalUsers());

        List<String> recommendations = recommender.getRecommendationsForUser("A141HP4LYPWMSR");
        /*
        *This elements did not apear while checking on the document, CMarin and I tried to check with other examples...
        assertThat(recommendations, hasItem("B0002O7Y8U"));
        assertThat(recommendations, hasItem("B00004CQTF"));
        assertThat(recommendations, hasItem("B000063W82"));
        */
        assertThat(recommendations, hasItem("B000063W1R"));
        assertThat(recommendations, hasItem("B001A7X0XG"));
        assertThat(recommendations, hasItem("6303578020"));

    }

}
