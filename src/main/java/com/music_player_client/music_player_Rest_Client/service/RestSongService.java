package com.music_player_client.music_player_Rest_Client.service;

import com.music_player_client.music_player_Rest_Client.entity.Song;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RestSongService implements IRestSongService {

    private HttpHeaders httpHeaders;
    private RestTemplate restTemplate;
    private final String HTTP_REQUEST_GET_ALL_SONGS = "http://localhost:8080//song/";
    private final String HTTP_REQUEST_GET_SONG_BY_ID = "http://localhost:8080/song/getSong/{song_id}";
    private final String HTTP_REQUEST_GET_FILE_IN_ARRAY
            = "http://localhost:8080/song/file/{name}?file_type={file_type}&storage_type={storage_type}";

    public void prepareData() {
        httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("my_outher_key", "my_outher_value");
        restTemplate = new RestTemplate();
    }

    public List<Song> getAllSongs() {
        prepareData();
        Song[] songs = restTemplate.getForObject(HTTP_REQUEST_GET_ALL_SONGS, Song[].class);
        return Arrays.asList(songs);
    }

    public Song findById(Long song_id) {
        prepareData();
        return restTemplate.getForObject(HTTP_REQUEST_GET_SONG_BY_ID, Song.class, song_id);
    }

    @Override
    public ResponseEntity<byte[]> getFile(String songName, String fileType, String storageType) {
        prepareData();
        byte[] songFile = restTemplate.getForObject(HTTP_REQUEST_GET_FILE_IN_ARRAY
                , byte[].class
                , songName
                , fileType
                , storageType);

        return ResponseEntity
                .ok()
                .contentLength(songFile.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + songName + "\"")
                .body(songFile);
    }
}

