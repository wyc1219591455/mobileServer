package com.justech.mobile.mobileserver.To;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import java.util.List;

/**
 * @ClassName FunctionTreeTo
 * @Author: xiaofeng.yang
 * @Date: Create in 10:48 2019/10/14
 * @Description : TODO
 * @Version: 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FunctionTreeTo {
    private int id;
    private String funname;
    private String type;
    private String empNo;
    private String name;
    private int sort;
    private int level;
    private int pid;
    @JSONField(ordinal = 10)
    private List<FunctionTreeTo> children;
}
