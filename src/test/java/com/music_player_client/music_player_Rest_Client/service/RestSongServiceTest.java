package com.music_player_client.music_player_Rest_Client.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class RestSongServiceTest {

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
        wireMockServer.start();
        String expectedRequest = "/song/";
        wireMockServer.stubFor(get(urlPathEqualTo(expectedRequest))
                .willReturn(aResponse()
                        .withHeader("Content-type", "application/json")
                        .withHeader("Accept", "application/json")
                        .withStatus(HttpStatus.SC_OK)
                        .withBody("{\"song\":[" +
                                "\"name\" : \"testSong\"" +
                                "\"notes\" : \"testNotes\"" +
                                "\"year\" : \"testYear\"" +
                                "\"storageTypes\" : \"testStorageTypes\"]}")));
//        restTemplate = new RestTemplate();
//        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/song/", String.class);

        String URL = "http://localhost:8888/song/";
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(URL);
        HttpResponse response = client.execute(request);

        assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        assertEquals(response.getEntity().getContentType().getValue(), "application/json");
        wireMockServer.verify(1, getRequestedFor(urlPathEqualTo(expectedRequest)));

        wireMockServer.stop();
    }

    @Test
    void findById() throws IOException {
        wireMockServer.start();
        String expectedRequest = "/song/getSong/";
        wireMockServer.stubFor(get(urlPathEqualTo(expectedRequest))
                .willReturn(aResponse()
                        .withHeader("Content-type", "application/json")
                        .withHeader("Accept", "application/json")
                        .withStatus(HttpStatus.SC_OK)
                        .withBody("{\"song\":[" +
                                "\"name\" : \"testSong\"" +
                                "\"notes\" : \"testNotes\"" +
                                "\"year\" : \"testYear\"" +
                                "\"storageTypes\" : \"testStorageTypes\"]}")));

        String URL = "http://localhost:8888/song/getSong/1";
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(URL);
        HttpResponse response = client.execute(request);

        assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        assertEquals(response.getEntity().getContentType().getValue(), "application/json");
        wireMockServer.verify(1, getRequestedFor(urlPathEqualTo(expectedRequest)));

        wireMockServer.stop();
    }

    @Test
    void getFile() throws IOException {
        byte[] testBody = "some test file".getBytes();
        wireMockServer.start();
        String expectedRequest = "/song/file/.*";
        wireMockServer.stubFor(get(urlMatching("/song/file/.*"))
                .willReturn(aResponse()
                        .withHeader("Content-type", "application/json")
                        .withHeader("Accept", "application/json")
                        .withStatus(HttpStatus.SC_OK)
                        .withBody(testBody))
        );

        String URL = "http://localhost:8888/song/file/testName?file_type=testFile_type&storage_type=testStorage_type";
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(URL);
        request.setHeader("Content-Type" ,"application/json");
        request.setHeader("Accept" ,"application/json");
        HttpResponse response = client.execute(request);

        assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        assertEquals(response.getEntity().getContentType().getValue(), "application/json");
        assertArrayEquals(response.getEntity().getContent().readAllBytes(), testBody);

        wireMockServer.verify(1, getRequestedFor(urlMatching(expectedRequest)));
        wireMockServer.verify(getRequestedFor(urlMatching(expectedRequest))
                .withHeader("Content-Type", equalTo("application/json"))
                .withHeader("Accept", equalTo("application/json")));

        wireMockServer.stop();
    }
}