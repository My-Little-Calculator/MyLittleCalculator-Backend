package com.example.mylittlecalculator.domain.user.application;

import com.example.mylittlecalculator.domain.user.domain.User;
import com.example.mylittlecalculator.domain.user.domain.UserRepository;
import com.example.mylittlecalculator.domain.user.dto.request.UserPatchRequest;
import com.example.mylittlecalculator.global.exception.CustomException;
import com.example.mylittlecalculator.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;

    @Transactional
    public void update(Long userId, UserPatchRequest userPatchRequest) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        User user = userOptional.get();
        user.updateInfo(userPatchRequest.getEmail(), userPatchRequest.getNickname());
    }

    @Transactional
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }
}
