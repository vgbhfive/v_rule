package com.vgbhfive.utils;

import com.vgbhfive.common.constants.Constant;
import com.vgbhfive.common.utils.NoGenerateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/26 22:10
 */
@SpringBootTest
public class NoGenerateUtilTest {

    @Test
    public void testNoGenerate() {
        System.out.println(NoGenerateUtil.generateNo(Constant.NO_SJYFL));
    }

}
