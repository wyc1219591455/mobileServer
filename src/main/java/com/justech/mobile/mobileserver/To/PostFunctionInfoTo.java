package com.justech.mobile.mobileserver.To;

import com.alibaba.fastjson.annotation.JSONField;
import com.justech.mobile.mobileserver.entity.PostFunctionType;
import com.justech.mobile.mobileserver.entity.PostFunctionUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;

/**
 * @ClassName PostFunctionInfoTo
 * @Author: xiaofeng.yang
 * @Date: Create in 11:07 2019/10/12
 * @Description : TODO
 * @Version: 1.0
 */
@Getter
@Setter
public class PostFunctionInfoTo {
    private int id;
    private Integer pId;
    private String name;
    private int sort;
    private int level;
    @JSONField(ordinal=10)
    private List<PostFunctionInfoTo> childPostFunctionInfoTo;
    @JSONField(ordinal=10)
    private List<FunUserTo> childUsers;
    public PostFunctionInfoTo() {

    }
    public PostFunctionInfoTo(PostFunctionType postFunctionType) {
        this.id = postFunctionType.getId();
        this.pId=postFunctionType.getPId();
        this.name=postFunctionType.getName();
        this.sort=postFunctionType.getSort();
        this.level=postFunctionType.getLevel();
    }
}
