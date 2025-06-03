package com.example.todo_api.todo.dto;

import com.example.todo_api.common.message.ErrorMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "할 일 생성 요청 DTO")
public class TodoCreateRequest {

    @Schema(description = "할 일 내용 (100자 이내)", example = "운동하기")
    @NotBlank(message = ErrorMessage.TODO_CONTENT_REQUIRED)
    @Size(max = 100, message = ErrorMessage.TODO_CONTENT_TOO_LONG)
    private String content;

    @Schema(description = "완료 여부", example = "false")
    private boolean isCompleted;

    @Schema(description = "할 일 날짜 (yyyy-MM-dd 형식)", example = "2025-06-03")
    @NotBlank(message = ErrorMessage.TODO_DATE_REQUIRED)
    private String date;

    @Schema(description = "사용자 ID", example = "1")
    @NotNull(message = ErrorMessage.TODO_USER_ID_REQUIRED)
    private Long userId;

    @Schema(description = "카테고리 ID", example = "2")
    @NotNull(message = ErrorMessage.TODO_CATEGORY_ID_REQUIRED)
    private Long categoryId;

    public boolean getIsCompleted() {
        return isCompleted;
    }
}