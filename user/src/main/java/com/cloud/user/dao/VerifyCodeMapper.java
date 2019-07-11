package com.cloud.user.dao;

import com.cloud.user.entity.VerifyCode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sun
 * @since 2019-07-10
 */
public interface VerifyCodeMapper extends BaseMapper<VerifyCode> {
    // 根据手机号获取验证码
    @Select("select * from verify_code where phone = #{phone} and type = #{type} order by createTime desc limit 1")
    VerifyCode getCodeByPhoneAndType (@Param("phone") String phone, @Param("type") Integer type);

    // 根据手机号获取发送数量
    @Select("select createTime from verify_code where phone = #{phone} order by createTime desc limit #{limit}")
    List<VerifyCode> getCheckCodeByPhone (@Param("phone") String phone, @Param("limit") Integer limit);

    // 根据ip获取发送的数量
    @Select("select count(*) from verify_code where ip = #{ip} and createTime > #{time}")
    Integer getCheckCodeByIp (@Param("ip") String ip, @Param("time") Integer time);
}
