package service;

import com.google.gson.JsonObject;
import com.jzhangdeveloper.newsapi.models.TopHeadlines;
import com.jzhangdeveloper.newsapi.net.NewsAPI;
import com.jzhangdeveloper.newsapi.net.NewsAPIClient;
import com.jzhangdeveloper.newsapi.net.NewsAPIResponse;
import com.jzhangdeveloper.newsapi.params.TopHeadlinesParams;
import lombok.Builder;
import model.Article;
import model.ServiceParams;

import java.io.IOException;
import java.util.*;

public class NewsService {
    NewsAPIClient client = NewsAPI.newClientBuilder()
            .setApiKey("6f2eed73b40a432cab4b7b4299061293")
            .build();

    public List<Article> callNewsApi(ServiceParams serviceParams) throws Exception {
        List<Article> articles = new ArrayList<>();

        Map<String, String> topHeadlineParams = TopHeadlinesParams.newBuilder()
                .setSearchQuery(serviceParams.getQuery())
                .setCategory(serviceParams.getCategory())
                .setPageSize(serviceParams.getPageSize())
                .setPage(serviceParams.getPage())
                .build();
        topHeadlineParams.put("language", serviceParams.getLanguage());

        NewsAPIResponse response = client.getTopHeadlines(topHeadlineParams);

        TopHeadlines topHeadlines = response.getBody();

        topHeadlines.getArticles().stream().forEach(article -> {
            articles.add(Article.builder()
                    .title( Objects.isNull(article.getTitle()) ? "" : article.getTitle() )
                    .description( Objects.isNull(article.getDescription()) ? "" : article.getDescription() )
                    .url( Objects.isNull(article.getUrl()) ? "" : article.getUrl() )
                    .image( Objects.isNull(article.getUrlToImage()) ? "" : article.getUrlToImage() )
                    .publishedAt( Objects.isNull(article.getPublishAt()) ? "" : article.getPublishAt() )
                    .source( Objects.isNull(article.getAuthor()) ? "" : article.getAuthor() )
                    .build());
        });
        return articles;
    }
    public ServiceParams inputToParams(Map<String, String> lambdaInput) {
        return ServiceParams.builder()
                .query(lambdaInput.get("query"))
                .category(lambdaInput.get("category"))
                .page(Integer.parseInt(lambdaInput.get("page")))
                .pageSize(Integer.parseInt(lambdaInput.get("pageSize")))
                .language(lambdaInput.get("language"))
                .build();
    }

}
