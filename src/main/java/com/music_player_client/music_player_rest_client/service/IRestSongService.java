package com.music_player_client.music_player_rest_client.service;

import com.music_player_client.music_player_rest_client.entity.Song;

import java.util.List;

public interface IRestSongService {

    List<Song> getAllSongs();
    Song findById(Long song_id);
    byte[] getFile(String songName, String fileType, String storageType);

}
