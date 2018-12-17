package com.ouresports.grimstroke.base.template;

import com.ouresports.grimstroke.base.enums.ApplicationError;
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

    public static ResultTemplate of(ApplicationError error) {
        return ResultTemplate.of(error.status(), error.value(), error.message());
    }
}
