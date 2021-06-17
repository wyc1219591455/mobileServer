package com.justech.mobile.mobileserver.To;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @ClassName SMSResDto
 * @Author: xiaofeng.yang
 * @Date: Create in 8:43 2019/8/12
 * @Description : TODO
 * @Version: 1.0
 */
@Getter
@Setter
@RequiredArgsConstructor
public class SMSResDto {
    private String code;
    private String msgId;
    private String time;
    private String errorMsg;
}
