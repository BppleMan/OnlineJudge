package com.bppleman.dao;

import com.bppleman.entity.IDParam;
import com.bppleman.entity.UserSolve;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSolveDao {
    List<UserSolve> getUserSolve(IDParam idParam);
    int insertUserSolve(UserSolve userSolve);
    int updateUserSolve(UserSolve userSolve);
    /**
     * 删除满足条件的UserSolve
     * @param idParams 这是临时封装的一个集合
     * @return 影响的行数
     */
    int deleteUserSolve(List<IDParam> idParams);
}
