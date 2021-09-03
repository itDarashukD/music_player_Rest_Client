package com.music_player_client.music_player_Rest_Client.service;

import com.music_player_client.music_player_Rest_Client.entity.Album;

import java.util.List;

public interface IRestAlbumService {

    List<Album> getAllAlbums();

    Album findById(Long album_id);

}