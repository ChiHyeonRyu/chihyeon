package univ.lecture.riotapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import univ.lecture.riotapi.model.Summoner;

import javax.xml.ws.Response;

/**
 * Created by tchi on 2017. 4. 1..
 */
@RestController
@RequestMapping("/api/v1")
public class RiotApiController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/summoner", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Summoner querySummoner(@PathVariable("name") String summonerName) {
        final String url = "https://kr.api.pvp.net/api/lol/kr/v1.4/summoner/by-name/%ED%85%8C%ED%81%AC%201%EB%93%B1%20%EC%AB%8C%EC%83%9D%EC%9D%B4?api_key=7f69a913-a7e3-4d41-b343-6389ba6fe730";

        Summoner summoner = restTemplate.getForObject(url, Summoner.class);
        return summoner;
    }
}
