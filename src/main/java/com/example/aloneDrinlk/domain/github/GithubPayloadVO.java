package com.example.aloneDrinlk.domain.github;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class GithubPayloadVO {

    private String repositoryId;
    private String pushId;
    private int size;
    private int distinctSize;
    private List<String> message;
    private String createDt;

}
