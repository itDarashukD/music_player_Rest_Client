package com.music_player_client.music_player_Rest_Client.service;

import com.music_player_client.music_player_Rest_Client.entity.Album;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RestAlbumService implements IRestAlbumService {

    private HttpHeaders httpHeaders;
    private RestTemplate restTemplate;
    private final String HTTP_REQUEST_GET_ALL_ALBUMS = "http://localhost:8080/album";
    private final String HTTP_REQUEST_GET_ALBUM_BY_ID = "http://localhost:8080/album/{album_id}";

    public void prepareData() {
        httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("my_outher_key", "my_outher_value");
        restTemplate = new RestTemplate();
    }

    @Override
    public List<Album> getAllAlbums() {
        prepareData();
        Album[] albums = restTemplate.getForObject(HTTP_REQUEST_GET_ALL_ALBUMS, Album[].class);
        return Arrays.asList(albums);
    }

    @Override
    public Album findById(Long album_id) {
        prepareData();
       return restTemplate.getForObject(HTTP_REQUEST_GET_ALBUM_BY_ID, Album.class, album_id);
    }
}
