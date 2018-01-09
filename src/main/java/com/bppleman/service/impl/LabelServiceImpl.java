package com.bppleman.service.impl;

import com.bppleman.dao.LabelDao;
import com.bppleman.entity.Label;
import com.bppleman.service.LabelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by BppleMan on 2017/11/24.
 */
@Service("labelService")
public class LabelServiceImpl implements LabelService {

    @Resource
    private LabelDao labelDao;

    @Override
    public List<Label> getLabels() {
        return labelDao.getLabels();
    }

    @Override
    public List<String> getValuesByType(String type) {
        return labelDao.getValuesByType(type);
    }
}
