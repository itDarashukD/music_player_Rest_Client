package com.music_player_client.music_player_Rest_Client.service;

import com.music_player_client.music_player_Rest_Client.entity.Song;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SongService {

    List<Song> finedAllSongs();

    Song findSongById(Long id);

    Long addSong(Song song);

    Long update(Long id, Song song);

    Boolean  deleteById(Long song_Id);

    Boolean isExistByName(String name);

    Boolean  deleteSongByName(String name);
}
