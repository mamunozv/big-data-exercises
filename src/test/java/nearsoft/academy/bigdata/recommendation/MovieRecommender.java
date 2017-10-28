package nearsoft.academy.bigdata.recommendation;


import java.io.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;


public class MovieRecommender {
    private int totalReviews;
    private Set<String> totalProducts = new LinkedHashSet<String>();
    private Set<String> totalUsers = new LinkedHashSet<String>();
    private Map<String, List<String>> userRecommendation = new HashMap<String, List<String>>();

    public MovieRecommender(String path) throws IOException {
        String content;
        String previousProduct = "";
        BufferedReader fileBufferedReader = getFileBufferedReader(path);
        while ((content = fileBufferedReader.readLine()) != null) {
            if (isNotEmptyLine(content)) {
                if (isValidReview(content)) {
                    String value = content.split(" ")[1].trim();
                    previousProduct = addValue(content, previousProduct, value);
                }
            }
        }
    }



    public int getTotalReviews() {
        return totalReviews;
    }

    public int getTotalProducts() {
        return totalProducts.size();
    }

    public int getTotalUsers() {
        return totalUsers.size();
    }

    public List<String> getRecommendationsForUser(String user) {
        return new ArrayList<String>(userRecommendation.get(user));
    }

    private boolean isNotEmptyLine(String content) {
        return !"".equals(content);
    }

    private boolean isValidReview(String content) {
        return isValidProduct(content) || isValidUser(content);
    }

    private boolean isValidUser(String content) {
        return content.contains("review/user");
    }

    private boolean isValidProduct(String content) {
        return content.contains("product/product");
    }

    private void addingUserRecomendation(String previousProduct, String value) {
        if (!userRecommendation.containsKey(value)) {
            userRecommendation.put(value, new ArrayList<String>());
        }
        userRecommendation.get(value).add(previousProduct);
    }

    private BufferedReader getFileBufferedReader(String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        InputStream gzipStream = new GZIPInputStream(fileInputStream);
        Reader decoder = new InputStreamReader(gzipStream, "UTF-8");
        return new BufferedReader(decoder);
    }

    private String addValue(String content, String previousProduct, String value) {
        if (isValidProduct(content)) {
            previousProduct = value;
            totalProducts.add(value);
        } else {
            addingUserRecomendation(previousProduct, value);
            totalUsers.add(value);
            totalReviews++;
        }
        return previousProduct;
    }
}