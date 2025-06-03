package com.example.todo_api.common.message;

public class ErrorMessage {

    // ✅ Todo 관련 메시지
    public static final String TODO_NOT_FOUND = "해당 할 일이 존재하지 않습니다.";
    public static final String TODO_CONTENT_REQUIRED = "내용을 입력해주세요.";
    public static final String TODO_CONTENT_TOO_LONG = "내용은 100자 이내여야 합니다.";
    public static final String TODO_DATE_REQUIRED = "날짜를 입력해주세요.";
    public static final String TODO_USER_ID_REQUIRED = "userId는 필수입니다.";
    public static final String TODO_CATEGORY_ID_REQUIRED = "categoryId는 필수입니다.";

    // ✅ 사용자 관련 메시지
    public static final String USER_NOT_FOUND = "존재하지 않는 사용자입니다.";
    public static final String USER_ID_REQUIRED = "사용자 ID는 필수입니다.";

    // ✅ 공통 메시지
    public static final String INTERNAL_SERVER_ERROR = "서버 에러가 발생했습니다.";

    // ✅ 추가 (서비스에서 사용)
    public static final String CATEGORY_NOT_FOUND = "존재하지 않는 카테고리입니다.";
}