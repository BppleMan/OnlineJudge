package com.bppleman.dao;

import com.bppleman.entity.Code;
import com.bppleman.entity.IDParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by BppleMan on 2017/11/20.
 */
@Repository
public interface CodeDao {
    List<Code> getCode(IDParam param);
    Code getCodeByID(Integer codeId);
    Integer insertCode(Code code);
}
