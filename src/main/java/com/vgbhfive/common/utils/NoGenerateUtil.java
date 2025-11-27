package com.vgbhfive.common.utils;

import com.vgbhfive.common.constants.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/26 21:49
 */
@Component
public class NoGenerateUtil {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMdd");

    private static final String NO_FORMAT = "%s%s%05d";

    @Resource
    private RedisUtil redisUtil;

    public String generateNo(String type) {
        String date = dtf.format(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()));
        Integer increment = redisUtil.incrementAndExpire(Constant.REDIS_PREFIX + "type:" + type + date);
        return String.format(NO_FORMAT, type, date, increment);
    }

}
