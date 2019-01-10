package com.ouresports.grimstroke.lib.zeus.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author will
 * @date 2019/1/10
 */
@Data
@Accessors(chain=true)
public class RewardRbo {
    private Info reward;

    @Data
    @Accessors(chain=true)
    public static class Info {
        private String type;
        private Integer amount;
        private Long receiverId;
    }
}
