package io.github.mayc0n23.ordermanager.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Builder
@Getter
public class ProblemDetails {

    private Integer status;

    private String detail;

    private OffsetDateTime timestamp;

}