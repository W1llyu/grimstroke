package com.ouresports.grimstroke.app.base.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

/**
 *
 * @author will
 * @date 2018/11/27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName="of")
public class ApiResult extends BaseDto {
    @NonNull
    private HttpStatus status;
    @NonNull
    private int code;
    @NonNull
    private String message;

    public static ApiResult createOk() {
        return ApiResult.of(HttpStatus.CREATED, 0, "创建成功");
    }

    public static ApiResult updateOk() {
        return ApiResult.of(HttpStatus.OK, 0, "更新成功");
    }

    public static ApiResult notFound() {
        return ApiResult.of(HttpStatus.NOT_FOUND, 1000, "资源不存在");
    }

    public static ApiResult invalidParam() {
        return ApiResult.of(HttpStatus.NOT_ACCEPTABLE, 1001, "参数错误");
    }
}
