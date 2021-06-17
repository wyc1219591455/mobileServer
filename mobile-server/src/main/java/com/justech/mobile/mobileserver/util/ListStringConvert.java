package com.justech.mobile.mobileserver.util;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName ListStringConvert
 * @Author: xiaofeng.yang
 * @Date: Create in 15:08 2019/7/16
 * @Description : TODO
 * @Version: 1.0
 */
public class ListStringConvert implements AttributeConverter<List<String>,String> {


    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        if (strings==null||strings.isEmpty()) return "";
        return String.join(",",strings);
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        if (s==null) return null;
        return Arrays.asList(s.split(","));
    }
}
