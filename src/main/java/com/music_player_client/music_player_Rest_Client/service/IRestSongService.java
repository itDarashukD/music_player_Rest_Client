package com.music_player_client.music_player_Rest_Client.service;

import com.music_player_client.music_player_Rest_Client.entity.Song;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRestSongService {

    List<Song> getAllSongs();
    Song findById(Long song_id);
    byte[] getFile(String songName, String fileType, String storageType);
}
