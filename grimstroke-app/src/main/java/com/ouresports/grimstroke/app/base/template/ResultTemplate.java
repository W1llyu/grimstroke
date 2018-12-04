package com.ouresports.grimstroke.app.base.template;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

/**
 *
 * @author will
 * @date 2018/11/27
 */
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName="of")
public class ResultTemplate {
    @NonNull
    private HttpStatus status;
    @NonNull
    private int code;
    @NonNull
    private String message;

    public static ResultTemplate createOk() {
        return ResultTemplate.of(HttpStatus.CREATED, 0, "创建成功");
    }

    public static ResultTemplate updateOk() {
        return ResultTemplate.of(HttpStatus.OK, 0, "更新成功");
    }

    public static ResultTemplate deleteOk() {
        return ResultTemplate.of(HttpStatus.OK, 0, "删除成功");
    }

    public static ResultTemplate notFound() {
        return ResultTemplate.of(HttpStatus.NOT_FOUND, 1000, "资源不存在");
    }

    public static ResultTemplate invalidParam() {
        return ResultTemplate.of(HttpStatus.NOT_ACCEPTABLE, 1001, "参数错误");
    }
}
