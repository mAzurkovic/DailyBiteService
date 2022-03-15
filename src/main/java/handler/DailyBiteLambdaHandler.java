package handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import model.Article;
import model.ServiceParams;
import service.NewsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
* DailyBiteLambdaHandler - entry point for Lambda
*/
public class DailyBiteLambdaHandler implements RequestHandler<Map<String, String>, List<Article>> {

    NewsService newsService = new NewsService();

    @Override
    public List<Article> handleRequest(Map<String, String> input, Context context) {

        List<Article> articles = new ArrayList<>();

        try {
            ServiceParams params = newsService.inputToParams(input);
            articles = newsService.callNewsApi(params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return articles;
    }
}