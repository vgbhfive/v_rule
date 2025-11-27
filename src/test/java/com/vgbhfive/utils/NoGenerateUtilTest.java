package com.vgbhfive.utils;

import com.vgbhfive.common.constants.Constant;
import com.vgbhfive.common.utils.NoGenerateUtil;
import com.vgbhfive.common.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/26 22:10
 */
@SpringBootTest
public class NoGenerateUtilTest {

    @Resource
    private NoGenerateUtil noGenerateUtil;

    @Test
    public void testNoGenerate() {
        System.out.println(noGenerateUtil.generateNo(Constant.NO_SJYFL));
    }

}
