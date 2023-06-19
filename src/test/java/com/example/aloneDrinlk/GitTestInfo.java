package com.example.aloneDrinlk;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Slf4j
public class GitTestInfo {
    public static void main(String[] args) {
        String username = "jh950925"; // 대상 사용자의 GitHub 사용자 이름

        try {
            // 잔디 정보 API 엔드포인트 URL 생성
            String apiUrl = "https://api.github.com/users/" + username;

            log.info("apiUrl : " + apiUrl);

            // API 호출 및 데이터 가져오기
            String jsonResponse = sendGetRequest(apiUrl);

            log.info("jsonResponse"+ jsonResponse);

            // JSON 파싱
            JsonArray jsonArray = new Gson().fromJson(jsonResponse, JsonArray.class);

            // 커밋 수와 날짜 추출 및 가공
            for (JsonElement jsonElement : jsonArray) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                String date = jsonObject.get("date").getAsString();
                int commitCount = jsonObject.get("count").getAsInt();

                // TODO: 원하는 가공 작업 수행

                // 예시: 출력
                System.out.println("Date: " + date);
                System.out.println("Commit Count: " + commitCount);
                System.out.println("------------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // GET 요청을 보내고 응답 데이터를 문자열로 반환하는 메소드
    private static String sendGetRequest(String url) throws IOException {
//        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//        connection.setRequestMethod("GET");
//        connection.connect();
//        int responseCode = connection.getResponseCode();
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            return IOUtils.toString(connection.getInputStream(), StandardCharsets.UTF_8);
//        } else {
//            throw new IOException("GET request failed with response code: " + responseCode);
//        }
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>(null, null);
        log.info("1");
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        log.info("2");
        log.info("response : " + response);
        log.info("=============");
        String body = response.getBody();
        log.info("body : " + body);
        log.info("=============");
        if (response.getStatusCode() == HttpStatus.OK) {
            return body;
        } else {
            throw new IOException("GET request failed with response code: " + body);
        }
    }
}
