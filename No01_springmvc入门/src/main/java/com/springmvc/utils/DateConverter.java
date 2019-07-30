package com.springmvc.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateConverter class
 * 自定义转换器：字符串转换日期类型
 * 实现Converter接口，该接口中的泛型，前面的类是待转换的类型，后面的是转换之后的类型。
 *
 * @author BowenWang
 * @date 2019/07/31
 */
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        if (s != null && !"".equals(s)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return sdf.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
