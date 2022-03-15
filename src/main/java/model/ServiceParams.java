package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ServiceParams {

    private String query;

    @Builder.Default
    private String category = "general";

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int pageSize = 20;

    @Builder.Default
    private String language = "en";
}
