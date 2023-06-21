package com.example.aloneDrinlk.domain.github;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class GithubVO {

    private String githubId;
    private String githubProjectNm;
    private GithubActorVO githubActorVO;
    private GithubRepoVO githubRepoVO;


}
