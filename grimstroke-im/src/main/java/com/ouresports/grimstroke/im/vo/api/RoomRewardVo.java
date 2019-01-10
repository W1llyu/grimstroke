package com.ouresports.grimstroke.im.vo.api;

import com.ouresports.grimstroke.base.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author will
 * @date 2019/1/10
 */
@Data
@Accessors(chain=true)
public class RoomRewardVo {
    private UserVo operator;
    private UserVo receiver;
    private Integer amount;
    private String type;

    public RoomRewardVo setOperator(User operator) {
        if (operator != null) {
            this.operator = (UserVo) new UserVo().convertFor(operator);
        }
        return this;
    }

    public RoomRewardVo setReceiver(User receiver) {
        if (receiver != null) {
            this.receiver = (UserVo) new UserVo().convertFor(receiver);
        }
        return this;
    }
}
