package com.bppleman.service.impl;

import com.bppleman.dao.LabelDao;
import com.bppleman.entity.Label;
import com.bppleman.entity.Problem;
import com.bppleman.service.LabelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<String> getLabelValues() {
        return labelDao.getLabelValues();
    }
}
