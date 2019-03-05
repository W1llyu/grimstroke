package com.ouresports.grimstroke.info.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

/**
 *
 * @author will
 * @date 2018/11/28
 */
public enum Game implements IEnum {
    /**
     * DOTA2
     */
    DOTA2(1, "dota2"),
    /**
     * CSGO
     */
    CSGO(2, "csgo"),
    /**
     * 撸啊撸
     */
    LOL(3, "lol"),
    /**
     * 王者荣耀
     */
    GOK(4, "gok")
    ;

    private int gameId;
    private String game;

    Game(final int gameId, final String game) {
        this.gameId = gameId;
        this.game = game;
    }

    @Override
    public Serializable getValue() {
        return gameId;
    }

    @Override
    public String toString() {
        return game;
    }
}
