package com.vgbhfive.v_rule.utils;

import com.vgbhfive.v_rule.common.constants.Constant;
import com.vgbhfive.v_rule.common.utils.NoGenerateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @projectName: v_rule
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
