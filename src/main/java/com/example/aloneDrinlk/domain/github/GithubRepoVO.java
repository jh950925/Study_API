package com.example.aloneDrinlk.domain.github;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class GithubRepoVO {

    private String id;
    private String name;
    private String url;

}
