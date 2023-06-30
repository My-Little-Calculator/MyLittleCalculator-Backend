package com.example.mylittlecalculator.global.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    /** 400 BAD_REQUEST : 잘못된 요청 */
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "BAD_REQUEST"),


    /** 401 UNAUTHORIZED : 인증되지 않은 사용자 */


    /** 403 FORBIDDEN : 사용자가 콘텐츠에 접근할 권리를 가지고 있지 않음 */


    /** 404 NOT_FOUND : 리소스를 찾을 수 없음 */
    NOT_FOUND(HttpStatus.NOT_FOUND, "NOT_FOUND"),

    /** 409 : CONFLICT : 리소스의 현재 상태와 충돌. 보통 중복된 데이터 존재 */

    ;
    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    // ErrorCode enum name
    public String getName() {
        return this.name();
    }

    // BAD_REQUEST 등의 Status 이름
    public String getHttpStatusErrorName() {
        return httpStatus.name();
    }

    // 400 등의 Status 코드
    public int getHttpStatusCode() {
        return httpStatus.value();
    }

    // 상세 메시지
    public String getMessage() {
        return message;
    }

}
