package com.GB2071FR.Pan.mapper;

import com.GB2071FR.Pan.entity.po.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author GB2071FR
 * @since 2023-10-10
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    UserInfo selectByEmail(@Param("email") String email);

    UserInfo selectByNickName(@Param("nickName") String nickName);

}
