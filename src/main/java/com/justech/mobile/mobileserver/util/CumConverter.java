package com.justech.mobile.mobileserver.util;

import javax.persistence.AttributeConverter;

/**
 * @ClassName CumConverter
 * @Author: xiaofeng.yang
 * @Date: Create in 13:19 2019/7/30
 * @Description : TODO
 * @Version: 1.0
 */
public class CumConverter implements AttributeConverter {
    @Override
    public Object convertToDatabaseColumn(Object o) {
        return o;
    }

    @Override
    public Object convertToEntityAttribute(Object o) {
        return o;
    }
}
