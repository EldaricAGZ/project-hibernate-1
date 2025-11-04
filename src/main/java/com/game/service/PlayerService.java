package com.game.service;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.exception.*;
import com.game.repository.IPlayerRepository;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class PlayerService {
    private final Logger logger = LoggerFactory.getLogger(PlayerService.class);
    private final IPlayerRepository playerRepository;

    public PlayerService(@Qualifier("db") @Autowired IPlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAll(int pageNumber, int pageSize) {
        List<Player> result = null;
        try {
            result = playerRepository.getAll(pageNumber, pageSize);
            logger.debug("Получен список сущностей их БД");
            if (result.isEmpty()) {
                logger.warn("Cписок игроков пуст.");
            }
        } catch (Exception e) {
            HandlerException.handleSimpleException(e);
        }
        return result;
    }

    public Integer getAllCount() {
        int result = 0;
        try {
            result = playerRepository.getAllCount();
            logger.debug("Получено количество сущностей в БД");
        } catch (Exception e) {
            HandlerException.handleSimpleException(e);
        }
        return result;
    }

    public Player createPlayer(String name, String title, Race race, Profession profession, long birthday, boolean banned, int level) {
        try {
            Player player = new Player();
            player.setName(name);
            player.setTitle(title);
            player.setRace(race);
            player.setProfession(profession);
            player.setBirthday(new Date(birthday));
            player.setBanned(banned);
            player.setLevel(level);

            PlayerValidator.validate(player);

            return playerRepository.save(player).orElseThrow(() -> new SavePlayerNotSuccessfulException(player));

        } catch (ConstraintViolationException e) {
            HandlerException.handleConstraintViolationException(e);
        } catch (SavePlayerNotSuccessfulException e) {
            HandlerException.handleSavePlayerNotSuccessfulException(e);
        } catch (Exception e) {
            HandlerException.handleSimpleException(e);
        }
        return null;
    }

    public Player updatePlayer(long id, String name, String title, Race race, Profession profession, int level, long birthday, Boolean banned) {
        try {
            Player player = new Player();
            player.setId(id);
            player.setName(name);
            player.setTitle(title);
            player.setRace(race);
            player.setProfession(profession);
            player.setBanned(banned);
            player.setLevel(level);
            player.setBirthday(new Date(birthday));

            PlayerValidator.validate(player);

            return playerRepository.update(player).orElseThrow(() -> new UpdatePlayerNotSuccessfulException(player));

        } catch (UpdatePlayerNotSuccessfulException e) {
            HandlerException.handleUpdatePlayerNotSuccessfulException(e);
        } catch (ConstraintViolationException e) {
            HandlerException.handleConstraintViolationException(e);
        } catch (Exception e) {
            HandlerException.handleSimpleException(e);
        }
        return null;
    }

    public Player delete(long id) {
        try {
            return playerRepository.delete(id).orElseThrow(DeletePlayerNotSuccessfulException::new);
        } catch (DeletePlayerNotSuccessfulException e) {
            HandlerException.handleDeletePlayerNotSuccessfulException(e);
        } catch (PlayerNotFoundException e) {
            HandlerException.handlePlayerNotFoundException(e);
        } catch (Exception e) {
            HandlerException.handleSimpleException(e);
        }
        return null;
    }
}