package com.bppleman.dao;

import com.bppleman.entity.Label;
import com.bppleman.entity.Problem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by BppleMan on 2017/11/24.
 */
@Repository
public interface LabelDao {
    List<Label> getLabels();
    List<String> getLabelValues();
}
