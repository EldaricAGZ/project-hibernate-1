package com.game.repository;

import com.game.entity.Player;
import com.game.exception.PlayerNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IPlayerRepository {
    List<Player> getAll(int pageNumber, int pageSize);

    int getAllCount();

    Optional<Player> save(Player player);

    Optional<Player> update(Player player);

    Optional<Player> findById(long id);

    Optional<Player> delete(long id) throws PlayerNotFoundException;
}