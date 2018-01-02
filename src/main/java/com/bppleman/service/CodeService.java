package com.bppleman.service;

import com.bppleman.entity.Code;
import com.bppleman.entity.IDParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeService {
    List<Code> getCode(IDParam param);
    Code getCodeById(Integer codeId);
    boolean insertCode(Code code);
}
