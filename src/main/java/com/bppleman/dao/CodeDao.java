package com.bppleman.dao;

import com.bppleman.entity.Code;
import com.bppleman.entity.IDParam;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by BppleMan on 2017/11/20.
 */
@Repository
public interface CodeDao {
    List<Code> getCode(IDParam idParam);
    Code getCodeByID(int id);
    int insertCode(Code code);
}
