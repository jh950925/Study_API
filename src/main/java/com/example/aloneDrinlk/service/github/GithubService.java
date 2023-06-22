package com.example.aloneDrinlk.service.github;

import com.example.aloneDrinlk.domain.github.GithubPayloadVO;

import java.io.IOException;

public interface GithubService {

    // 깃허브 모든 URL 가져오기
    public GithubPayloadVO githubGetData() throws IOException;

}
