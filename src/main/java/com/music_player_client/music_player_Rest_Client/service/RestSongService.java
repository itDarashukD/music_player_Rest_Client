package com.music_player_client.music_player_Rest_Client.service;

import com.music_player_client.music_player_Rest_Client.entity.Song;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RestSongService implements IRestSongService {

    private final String HTTP_REQUEST_GET_ALL_SONGS = "http://localhost:8888/song/";
    private final String HTTP_REQUEST_GET_SONG_BY_ID = "http://localhost:8888/song/getSong/{song_id}";
    private final String HTTP_REQUEST_GET_FILE_IN_ARRAY
            = "http://localhost:8888/song/file/{name}?file_type={file_type}&storage_type={storage_type}";

    public void prepareData() {

        //Solution with HTTPHeaders
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("my_outher_key", "my_outher_value");
        HttpEntity<Song[]> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Song[]> response = restTemplate.exchange(HTTP_REQUEST_GET_ALL_SONGS,
                HttpMethod.GET, entity, Song[].class);
        Song[] list = response.getBody();
    }

    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public List<Song> getAllSongs() {
        Song[] songs = getRestTemplate().getForObject(HTTP_REQUEST_GET_ALL_SONGS, Song[].class);
        return Arrays.asList(songs);
    }

    public Song findById(Long song_id) {
        return getRestTemplate().getForObject(HTTP_REQUEST_GET_SONG_BY_ID, Song.class, song_id);
    }

    @Override
    public byte[] getFile(String songName, String fileType, String storageType) {
        byte[] songFile = getRestTemplate().getForObject(HTTP_REQUEST_GET_FILE_IN_ARRAY
                , byte[].class
                , songName
                , fileType
                , storageType);
        return songFile;

//        return ResponseEntity
//                .ok()
//                .contentLength(songFile.length)
//                .header("Content-type", "application/octet-stream")
//                .header("Content-disposition", "attachment; filename=\"" + songName + "\"")
//                .body(songFile);
    }
}

