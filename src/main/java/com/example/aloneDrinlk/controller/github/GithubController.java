package com.example.aloneDrinlk.controller.github;


import com.example.aloneDrinlk.service.github.GithubService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/github")
public class GithubController {

    private final GithubService githubService;

    @GetMapping("/userInfo")
    public String gitStart() throws IOException {
        String methodName = new Object(){}.getClass().getName().toString();
        log.info(methodName);
        githubService.githubGetData();

        return "ok";

    }
}
