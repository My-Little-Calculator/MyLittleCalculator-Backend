package com.example.mylittlecalculator.domain.user.presentation;

import com.example.mylittlecalculator.domain.user.application.UserService;
import com.example.mylittlecalculator.domain.user.dto.request.UserPatchRequest;
import com.example.mylittlecalculator.domain.user.dto.response.UserResponse;
import com.example.mylittlecalculator.global.argumentresolver.SessionInfo;
import com.example.mylittlecalculator.global.argumentresolver.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<UserResponse> read(@SessionInfo SessionUser sessionUser) {
        UserResponse userResponse = new UserResponse();
        userResponse.patch(sessionUser);

        return ResponseEntity.ok(userResponse);
    }

    @PatchMapping("/user")
    public ResponseEntity<Void> update(@SessionInfo SessionUser sessionUser, @RequestBody UserPatchRequest userPatchRequest) {
        userService.update(sessionUser.getId(), userPatchRequest);

        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/user")
    public ResponseEntity<Void> delete(@SessionInfo SessionUser sessionUser) {
        userService.delete(sessionUser.getId());

        return ResponseEntity.ok(null);
    }
}
