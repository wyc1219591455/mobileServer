package com.justech.mobile.mobileserver.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName
 * @Author: xiaofeng.yang
 * @Date: Create in 9:36 2019/10/22
 * @Description : TODO
 * @Version: 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class UserevaluationIdClass implements Serializable {
    private static final long serialVersionUID = 2L;
    private String text;
    private Integer weight;

    @Override
    public int hashCode() {
        return text.hashCode()+weight.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UserevaluationIdClass){
            UserevaluationIdClass idClass= (UserevaluationIdClass) obj;
            return idClass.getText().equals(this.text)&&idClass.getWeight().equals(this.weight);
        }
        return false;
    }
}
