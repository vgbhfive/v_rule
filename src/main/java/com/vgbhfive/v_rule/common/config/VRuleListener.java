package com.vgbhfive.v_rule.common.config;

import com.vgbhfive.v_rule.common.aop.Valid;
import com.vgbhfive.v_rule.common.factory.ValidServiceFactory;
import com.vgbhfive.v_rule.service.valid.ValidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * @Author vgbhfive
 * @Date 2026/2/12 17:11
 */
@Component
public class VRuleListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(VRuleListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("环境初始化...");

        Map<String, Object> eventHandlerBeans = event.getApplicationContext().getBeansWithAnnotation(Valid.class);
        for (Object bean : eventHandlerBeans.values()) {
            Valid valid = bean.getClass().getAnnotation(Valid.class);
            if (Objects.nonNull(valid)) {
                ValidServiceFactory.register(valid.type(), (ValidService<Object>) bean);
            }
        }
        logger.info("ValidFactory 初始化，ValidServiceFactoryList: {}", ValidServiceFactory.getHandlerMap().size());

        System.out.println("环境初始化完成！");
    }

}
