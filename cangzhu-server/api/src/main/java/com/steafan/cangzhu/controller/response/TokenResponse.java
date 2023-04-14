package com.steafan.cangzhu.controller.response;

import com.steafan.cangzhu.controller.response.user.UserInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
    private String token;
    private String validBefore;
    private String validAfter;
    private String refreshToken;
    private String refreshTokenValidBefore;
    private UserInfoResponse userInfo;
}
