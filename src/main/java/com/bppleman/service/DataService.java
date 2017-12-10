package com.bppleman.service;

import com.bppleman.entity.Data;
import org.springframework.stereotype.Repository;

/**
 * Created by BppleMan on 2017/11/21.
 */
@Repository
public interface DataService {
    Data getDataByProblemIDAndShellName(int problemId, String shellName);
}
