package com.example.aloneDrinlk.serviceImpl.github;

import com.example.aloneDrinlk.domain.github.GithubPayloadVO;
import com.example.aloneDrinlk.service.github.GithubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GithubServiceimpl implements GithubService {

    @Autowired
    private final GithubPayloadVO githubPayloadVO;

    public GithubServiceimpl(GithubPayloadVO githubPayloadVO) {
        this.githubPayloadVO = githubPayloadVO;
    }

    @Override
    public GithubPayloadVO githubGetData() throws IOException {
        String username = "jh950925"; // 대상 사용자의 GitHub 사용자 이름

        GithubPayloadVO githubPayloadVO = new GithubPayloadVO();

        try {
            // 잔디 정보 API 엔드포인트 URL 생성
            String apiUrl = "https://api.github.com/users/" + username;

            log.info("apiUrl : " + apiUrl);

            // API 호출 및 데이터 가져오기
            Map<String, Object> gitData = sendGetRequest(apiUrl);
            String eventUrl = gitData.get("events_url").toString();
            log.info("eventUrl : " + eventUrl);
            log.info("jsonResponse" + gitData);

            List<Map<String, Object>> gitEventDaa = sendGetRequestEvent(eventUrl);

            log.info("gitEventDaa : " + gitEventDaa);

            ArrayList<String> arrayList = new ArrayList<>();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
            LocalDateTime latestCreatedAt = null;
            List<String> latestCommitMessages = new ArrayList<>();

            for (Map<String, Object> map : gitEventDaa) {
                String createdAtString = map.get("created_at").toString();
                LocalDateTime createdAt = LocalDateTime.parse(createdAtString, formatter);

                githubPayloadVO.setCreateDt(createdAtString.substring(0,10));

                if (latestCreatedAt == null || createdAt.isAfter(latestCreatedAt)) {
                    latestCreatedAt = createdAt;
                    latestCommitMessages.clear();

                    Map<String, Object> payloadMap = (Map<String, Object>) map.get("payload");
                    List<Map<String, Object>> commitsList = (List<Map<String, Object>>) payloadMap.get("commits");
                    if (commitsList != null) {
                        for (Map<String, Object> commitMap : commitsList) {
                            String message = (String) commitMap.get("message");
                            latestCommitMessages.add(message);
                            log.info("Message: " + message);
                            arrayList.add(message);
                        }
                    }
                }
            }

            githubPayloadVO.setMessage(arrayList);


            log.info("arrayList : " + arrayList);
            log.info("푸쉬한 데이터 숫자 : " + arrayList.size());

            log.info("저장한 VO : " + githubPayloadVO.getMessage());
            log.info("저장한 VO : " + githubPayloadVO.getCreateDt());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return githubPayloadVO;
    }



    // GET 요청을 보내고 응답 데이터를 문자열로 반환하는 메소드
    // 유저 정보 기준해서 데이터 받아오기
    private static Map<String, Object> sendGetRequest(String url) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>(null, null);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        Map<String, Object> parseBody = response.getBody();

        if (response.getStatusCode() == HttpStatus.OK) {
            return parseBody;
        } else {
            throw new IOException("GET request failed with response code: " + parseBody);
        }
    }

    // 깃허브 푸쉬 이벤트 가져오기
    private static List<Map<String, Object>> sendGetRequestEvent(String url) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        log.info("url : " + url);
        HttpEntity<String> httpEntity = new HttpEntity<>(null, null);

        ResponseEntity<List> response = restTemplate.exchange(url.replaceAll("\\{\\/privacy\\}", ""), HttpMethod.GET, httpEntity, List.class);
        List<Map<String, Object>> parseBody = response.getBody();

        log.info("=====\n" + parseBody);


        return parseBody;
    }
}

