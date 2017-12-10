package com.bppleman.service;

import com.bppleman.entity.IDParam;
import com.bppleman.entity.UserSolve;
import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Repository;

import java.security.PrivateKey;
import java.util.List;
import java.util.Map;

@Repository
public interface UserSolveService {
    /**
     * 通过param获取user solve
     * @param idParam 一个临时封装的参数实体
     * @return 返回user solve的集合
     */
    List<UserSolve> getUserSolve(IDParam idParam);

    /**
     * 插入一条新的user solve
     * @param userSolve 将要插入的user solve
     * @return 返回是否插入成功
     */
    boolean insertUserSolve(UserSolve userSolve);

    /**
     * 更新一条user solve
     * 主要是更新user solve的solveState
     * @param userSolve 将要更新的user solve
     * @return 返回是否更新成功
     */
    boolean updateUserSolve(UserSolve userSolve);

    /**
     * 删除满足条件的UserSolve
     * @param idParams 这是临时封装的一个集合
     * @return 影响的行数
     */
    boolean deleteUserSolve(List<IDParam> idParams);

    /**
     * 通过参数获取一个题目id--是否解决的键值对
     * @param idParam 一个临时封装的参数实体
     * @return 返回题目id的集合
     */
    Map<Integer, String> getUserSolveMap(IDParam idParam);

}
