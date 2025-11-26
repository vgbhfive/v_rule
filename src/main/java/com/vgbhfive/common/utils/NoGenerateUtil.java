package com.vgbhfive.common.utils;

import com.vgbhfive.common.constants.Constant;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/26 21:49
 */
public class NoGenerateUtil {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMdd");

    private static final String NO_FORMAT = "%s%s%03d";

    public static String generateNo(String type) {
        String date = dtf.format(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()));
        Integer increment = RedisUtil.incrementAndExpire(Constant.REDIS_PREFIX + "type:" + type + date);
        return String.format(NO_FORMAT, type, date, increment);
    }

}
