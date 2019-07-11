package com.cloud.user.service.impl;

import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.util.TimeUtil;
import com.cloud.user.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
//    @Resource
//    private UserAddressMapper userAddressMapper;
//
//    // sun
//    // 根据id获取地址
//    @Override
//    public UserAddress getUserAddressById(Long addressId) {
//        return userAddressMapper.selectById(addressId);
//    }
//
//    // sun
//    // 添加地址
//    @Override
//    @Transactional
//    public void addUserAddress(Long userId, Integer cityId, String address, String detail, String phone, String name, Integer isDefault) {
//        UserAddress userAddress = new UserAddress();
//        userAddress.setUserId(userId);
//        userAddress.setCityId(cityId);
//        userAddress.setAddress(address);
//        userAddress.setDetail(detail);
//        userAddress.setPhone(phone);
//        userAddress.setName(name);
//        userAddress.setIsDefault(isDefault);
//        userAddress.setCreateTime(TimeUtil.getTimeStamp());
//        if (isDefault == 1) {
//            // 设置其他地址为非默认
//            userAddressMapper.setAsUnDefault(userId);
//        }
//        userAddressMapper.insert(userAddress);
//    }
//
//    // sun
//    // 编辑地址
//    @Override
//    @Transactional
//    public void editUserAddress(Long userId, Long addressId, Integer cityId, String address, String detail, String phone, String name, Integer isDefault) {
//        UserAddress userAddress = userAddressMapper.selectById(addressId);
//        if (userAddress == null)
//            Res.fail(ErrorType.NOT_EXIST);
//        if (userAddress.getIsDefault() == 0 && isDefault == 1) {
//            // 设置其他地址为非默认
//            userAddressMapper.setAsUnDefault(userId);
//        }
//        userAddress.setCityId(cityId);
//        userAddress.setAddress(address);
//        userAddress.setDetail(detail);
//        userAddress.setPhone(phone);
//        userAddress.setName(name);
//        userAddress.setIsDefault(isDefault);
//        userAddressMapper.updateById(userAddress);
//    }
//
//    // sun
//    // 删除地址
//    @Override
//    public void delUserAddress(Long userId, Long addressId) {
//        userAddressMapper.deleteUserAddress(userId, addressId);
//    }
//
//    // sun
//    // 设置默认地址
//    @Override
//    @Transactional
//    public void setDefaultUserAddress(Long userId, Long addressId) {
//        userAddressMapper.setAsUnDefault(userId);
//        userAddressMapper.setAsDefault(userId, addressId);
//    }
//
//    // sun
//    // 获取默认地址
//    @Override
//    public UserAddress getUserDefaultAddress(Long userId) {
//        return userAddressMapper.getUserDefaultAddress(userId);
//    }
//
//    // sun
//    // 获取地址列表
//    @Override
//    public List<UserAddress> getUserAddressList(Long userId) {
//        return userAddressMapper.getUserAddressList(userId);
//    }
}
