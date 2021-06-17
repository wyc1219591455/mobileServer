package com.justech.mobile.mobileserver.To;

import com.justech.mobile.mobileserver.entity.PostFunctionUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName FunUserTo
 * @Author: xiaofeng.yang
 * @Date: Create in 9:57 2019/10/14
 * @Description : TODO
 * @Version: 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FunUserTo {
    private String funName;
    private UserOfErpEntity userOfErpEntity;
    private PostFunctionUser postFunctionUser;

}
