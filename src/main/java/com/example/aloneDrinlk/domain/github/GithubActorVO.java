package com.example.aloneDrinlk.domain.github;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class GithubActorVO {

    private String id;
    private String loginId;
    private String avatar_url;

}
