package com.bppleman.service;

import com.bppleman.entity.Label;
import com.bppleman.entity.Problem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by BppleMan on 2017/11/24.
 */
@Repository
public interface LabelService {
    List<Label> getLabels();
    List<String> getLabelValues();
}
