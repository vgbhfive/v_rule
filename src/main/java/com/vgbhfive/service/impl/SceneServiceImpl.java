package com.vgbhfive.service.impl;

import com.vgbhfive.mapper.SceneMapper;
import com.vgbhfive.service.SceneService;
import javafx.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @projectName: v-rule
 * @author: vgbhfive
 * @date: 2025/11/23 23:16
 */
@Service
public class SceneServiceImpl implements SceneService {

    private static final Logger logger = LoggerFactory.getLogger(SceneServiceImpl.class);

    @Resource
    private SceneMapper sceneMapper;

    @Override
    public List<Scene> queryList() {
        return null;
    }
}
