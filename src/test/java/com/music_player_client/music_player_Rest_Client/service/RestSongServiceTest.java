package com.music_player_client.music_player_Rest_Client.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.http.Response;
import com.music_player_client.music_player_Rest_Client.entity.Song;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class RestSongServiceTest {

    @Autowired
    IRestSongService restSongService;

    WireMockServer wireMockServer = new WireMockServer(8888);
//    private final String HTTP_REQUEST_GET_ALL_SONGS = "http://localhost:"+wireMockServer.port()+"/song/";
//    private final String HTTP_REQUEST_GET_SONG_BY_ID = "http://localhost:8080/song/getSong/1";
//    private final String HTTP_REQUEST_GET_FILE_IN_ARRAY
//            = "http://localhost:8080/song/file/testName?file_type=testFile_type&storage_type=testStorage_type";

//    @Rule
//    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort().dynamicHttpsPort()); good for multithreading
//    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8888));

    @Test
    void getAllSongs() throws IOException {
        String jsonListSongs =
                "[{ \"id\" : \"1\", \"album_id\" : \"2\",\"source_id\" : \"1\", \"name\" : \"firstSong\" ,\"notes\" : \"firstSongNotes\", \"year\" : \"2020\",\"storageTypes\" : \"CLOUD_STORAGE\"   }" +
                        ", { \"id\" : \"2\", \"album_id\" : \"2\",\"source_id\" : \"2\", \"name\" : \"secondSong\" ,\"notes\" : \"secondSongNotes\", \"year\" : \"2021\",\"storageTypes\" : \"FILE_SYSTEM\" }]";
        wireMockServer.start();
        String expectedRequest = "/song/";
        wireMockServer.stubFor(get(urlPathEqualTo(expectedRequest))
                .willReturn(aResponse()
                        .withHeader("Content-type", "application/json")
                        .withStatus(HttpStatus.SC_OK)
                        .withBody(jsonListSongs)));

        List<Song> allSongs = restSongService.getAllSongs();

        assertEquals(allSongs.get(1).getYear(),2021);
        assertEquals(allSongs.size(),2);
        wireMockServer.verify(1, getRequestedFor(urlPathEqualTo(expectedRequest)));

        wireMockServer.stop();
    }

    @Test
    void findById() {
        wireMockServer.start();
        String jsonSong = "{ \"id\" : \"1\", \"album_id\" : \"1\", \"source_id\" : \"2\", \"name\" : \"firstSong\"," +
                " \"notes\" : \"firstSongNotes\",\"year\" : \"2020\",\"storageTypes\" : \"CLOUD_STORAGE\" }";

        String expectedRequest = "/song/getSong/";
        wireMockServer.stubFor(get(urlPathEqualTo(expectedRequest))
                .willReturn(aResponse()
                        .withHeader("Content-type", "application/json")
                        .withStatus(HttpStatus.SC_OK)
                        .withBody(jsonSong)));

        Song songById = restSongService.findById(1L);
        assertEquals(songById.getId(),1L);
        assertEquals(songById.getYear(),2020);
        wireMockServer.verify(1, getRequestedFor(urlPathEqualTo(expectedRequest)));

        wireMockServer.stop();
    }

    @Test
    void getFile() {
        byte[] testBody = "some test file".getBytes(StandardCharsets.UTF_8);
        String expectedRequest = "/song/file/.*";

        wireMockServer.start();

        wireMockServer.stubFor(get(urlMatching(expectedRequest))
                .willReturn(aResponse()
                        .withHeader("Content-type", "application/octet-stream")
                        .withStatus(HttpStatus.SC_OK)
                        .withBody(testBody))
        );

        byte[] file = restSongService.getFile("songName", "mp3", "CLOUD_STORAGE");

        wireMockServer.verify(1, getRequestedFor(urlMatching(expectedRequest)));
        assertArrayEquals(file,testBody);

        wireMockServer.stop();
    }
}