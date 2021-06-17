package com.justech.mobile.mobileserver.To;

import java.util.regex.Pattern;

/**
 * @ClassName ExcludePathDto
 * @Author: xiaofeng.yang
 * @Date: Create in 8:53 2019/7/29
 * @Description : TODO
 * @Version: 1.0
 */
public class ExcludePathDto {
    private String path;
    private String method;

    public ExcludePathDto() {

    }

    public ExcludePathDto(String path, String method) {
        this.path = path;
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ExcludePathDto)
//            return this.getMethod().equals(((ExcludePathDto) obj).getMethod())&&this.getPath().equals(((ExcludePathDto) obj).getPath());
        {
            boolean flageMetho=  this.getMethod().equals(((ExcludePathDto) obj).getMethod());
            boolean flagePath= Pattern.matches(((ExcludePathDto) obj).getPath(),this.getPath());
            return flagePath&&flageMetho;
        }
        return false;
    }
}
