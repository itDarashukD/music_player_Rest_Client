package com.music_player_client.music_player_rest_client.service;

import com.music_player_client.music_player_rest_client.entity.Song;
import com.music_player_client.music_player_rest_client.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private SongRepository songRepository;

    @Autowired
    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> finedAllSongs() {
        return Optional.ofNullable(songRepository.finedAllSongs())
                .orElseThrow(() -> new IllegalStateException("songs not find"));
    }

    public Song findSongById(Long id) {
        return Optional.ofNullable(songRepository.findById(id))
                .orElseThrow(() -> new IllegalStateException("song with id" + id + " not find"));
    }

    @Override
    public Long addSong(Song song) {
        if (!songRepository.isExistByName(song.getName())) {
            songRepository.save(song);
        } else {
            throw new IllegalStateException("file " + song.getName() + " exist in DB in this moment, please upload another file!");
        }
        return song.getId();
    }

//    @Transactional
    public Long update(Long id, Song song) {
        Song song1 = Optional.ofNullable(songRepository.findById(id))
                .orElseThrow(() -> new IllegalStateException("song with id " + id + "  not find"));
        song1.setName(song.getName());
        song1.setYear(song.getYear());
        song1.setNotes(song.getNotes());
        songRepository.update(song1);
        return song1.getId();
    }

    public Boolean deleteById(Long song_id) {
        songRepository.deleteById(song_id);
        return true;
    }

    public Boolean deleteSongByName(String name) {
        songRepository.deleteByName(name);
        return true;
    }

    public Boolean isExistByName(String name) {
        return songRepository.isExistByName(name);
    }
}

