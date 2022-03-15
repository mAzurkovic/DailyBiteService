package model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Article {

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    private String url;

    @NonNull
    private String image;

    @NonNull
    private String publishedAt;

    @NonNull
    private String source;
}
