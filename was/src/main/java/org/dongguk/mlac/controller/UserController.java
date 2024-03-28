package org.dongguk.mlac.controller;

import lombok.RequiredArgsConstructor;
import org.dongguk.mlac.dto.common.ResponseDto;
import org.dongguk.mlac.dto.request.UpdateUserStateRequestDto;
import org.dongguk.mlac.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PatchMapping("")
    public ResponseDto<?> updateUserState(
            @RequestBody UpdateUserStateRequestDto updateUserStateRequestDto
    ){
        userService.updateUserState(updateUserStateRequestDto);
        return ResponseDto.ok(null);
    }
}
