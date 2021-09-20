package com.music_player_client.music_player_rest_client.service;

import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.music_player_client.music_player_rest_client.entity.Song;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
class RestSongServiceTest {

    @Autowired
    IRestSongService restSongService;

    private WireMockRule wireMockRule;

    @BeforeEach
    public void setUp() {
        wireMockRule = new WireMockRule(Options.DYNAMIC_PORT);
        wireMockRule.start();

        int port = wireMockRule.port();
        System.setProperty("port", String.valueOf(port));
        ReflectionTestUtils.setField(restSongService, "port", String.valueOf(port));
    }

    @AfterEach
    public void tearDown() {
        wireMockRule.stop();
    }

    @Test
    void getAllSongs() {
        String expectedRequest = "/song/";
        wireMockRule.stubFor(get(urlPathEqualTo(expectedRequest))
                .willReturn(aResponse()
                        .withHeader("Content-type", "application/json")
                        .withStatus(HttpStatus.SC_OK)
                        .withBodyFile("listSongs.json")));

        List<Song> allSongs = restSongService.getAllSongs();

        assertEquals(allSongs.get(1).getYear(), 2021);
        assertEquals(allSongs.size(), 2);
        wireMockRule.verify(1, getRequestedFor(urlPathEqualTo(expectedRequest)));
    }

    @Test
    void findById() {
        String expectedRequest = "/song/getSong/1";
        wireMockRule.stubFor(get(urlPathEqualTo(expectedRequest))
                .willReturn(aResponse()
                        .withHeader("Content-type", "application/json")
                        .withStatus(HttpStatus.SC_OK)
                        .withBodyFile("song.json")));

        Song songById = restSongService.findById(1L);

        assertEquals(songById.getId(), 1L);
        assertEquals(songById.getYear(), 2020);
        wireMockRule.verify(1, getRequestedFor(urlPathEqualTo(expectedRequest)));
    }

    @Test
    void getFile() {
        byte[] testBody = "some test file".getBytes(StandardCharsets.UTF_8);
        String expectedRequest = "/song/file/.*";

        wireMockRule.stubFor(get(urlMatching(expectedRequest))
                .willReturn(aResponse()
                        .withHeader("Content-type", "application/octet-stream")
                        .withStatus(HttpStatus.SC_OK)
                        .withBody(testBody))
        );

        byte[] file = restSongService.getFile("songName", "mp3", "CLOUD_STORAGE");

        wireMockRule.verify(1, getRequestedFor(urlMatching(expectedRequest)));
        assertArrayEquals(file, testBody);
    }
}

//// *  JUnit 4.11 and above prohibits @Rule on static members so a slightly more verbose form, current version is 4.12*/
//    @ClassRule
//    public static WireMockClassRule wireMockRule = new WireMockClassRule(Options.DYNAMIC_PORT);
//
//    @Rule
//    public WireMockClassRule instanceRule = wireMockRule;
//    @Rule
//    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort().dynamicHttpsPort()); good for multithreading
//    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8888));

/**
 * @TestPropertySource("/application-test.properties")
 * @TestPropertySource(properties = {"foo=bar"}) //concrete var = value
 * working with
 * @Value("${foo}") private String foo;
 */