package com.game.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name = "player", schema = "rpg")
@NamedQuery(
        name = "Player.selectCount",
        query = "SELECT COUNT(*) FROM Player"
)
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Имя не может быть равно null")
    @Size(min = 0, max = 12, message = "Введите валидное имя, до 12 символов")
    private String name;

    @NotNull(message = "Заголовок не может быть равен null")
    @Size(min = 0, max = 30, message = "Введите валидный заголовок, до 30 символов")
    private String title;

    @NotNull(message = "Заголовок не может быть равен null")
    @Enumerated(EnumType.ORDINAL)
    private Race race;

    @NotNull(message = "Заголовок не может быть равен null")
    @Enumerated(EnumType.ORDINAL)
    private Profession profession;

    @NotNull(message = "День рождения не может быть равен null")
    private Date birthday;

    @NotNull(message = "Статус бана не может быть равен null")
    private Boolean banned;

    @NotNull(message = "Уровень не может быть равен null")
    private Integer level;

    public Player() {
    }

    public Player(Long id, String name, String title, Race race, Profession profession, Date birthday, Boolean banned, Integer level) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.birthday = birthday;
        this.banned = banned;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}