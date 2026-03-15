package com.fandream.usercenter.dto;

import lombok.Data;

/**
 * UserDto
 *
 * @author fandream
 * @date 2026-03-11 20:01:02
 */
@Data
public class UserDto {
    private String account;

    private String password;

    private String checkPassword;
}
