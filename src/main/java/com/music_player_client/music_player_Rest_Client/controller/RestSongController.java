package com.music_player_client.music_player_Rest_Client.controller;

import com.music_player_client.music_player_Rest_Client.entity.Song;
import com.music_player_client.music_player_Rest_Client.service.IRestSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("/song")
@RestController
public class RestSongController {

    private IRestSongService songService;
    @Autowired
    public RestSongController(IRestSongService songService) {
        this.songService = songService;
    }

    @GetMapping(value = "/")
    List<Song> getAll() {
        return songService.getAllSongs();
    }

    @GetMapping(value = "/getSong/{song_id}")
    public Song findSongById(@PathVariable Long song_id) {
        return songService.findById(song_id);
    }

    @GetMapping("/file/{name}")
    public ResponseEntity<byte[]> getFile(@PathVariable String name
            , @RequestParam("storage_type") String storage_type
            , @RequestParam("file_type") String file_type) throws IOException {
        byte[] content = songService.getFile(name, file_type, storage_type);
        return ResponseEntity
                .ok()
                .contentLength(content.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + name + "\"")
                .body(content);
    }
}
