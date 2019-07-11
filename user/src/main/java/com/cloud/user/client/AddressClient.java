package com.cloud.user.client;

import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.util.CommonUtil;
import com.cloud.user.service.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/client/address")
public class AddressClient extends ClientController {
//    @Resource
//    private AddressService addressService;
//
//    // sun
//    // 添加地址
//    @RequestMapping("/addUserAddress")
//    public void addUserAddress () {
//        Integer cityId = postInt("cityId");
//        String address = postString("address");
//        String detail = postString("detail");
//        String phone = postString("phone");
//        String name = postString("name");
//        Integer isDefault = postInt("isDefault", 0);
//        if (CommonUtil.isEmpty(cityId) || CommonUtil.isEmpty(address) || CommonUtil.isEmpty(detail) || CommonUtil.isEmpty(phone) || CommonUtil.isEmpty(name)) {
//            Res.fail(ErrorType.PARAM_ERR);
//        }
//        addressService.addUserAddress(getUserId(), cityId, address, detail, phone, name, isDefault);
//        Res.success();
//    }
//
//    // sun
//    // 编辑地址
//    @RequestMapping("/editUserAddress")
//    public void editUserAddress () {
//        Long addressId = postLong("id");
//        Integer cityId = postInt("cityId");
//        String address = postString("address");
//        String detail = postString("detail");
//        String phone = postString("phone");
//        String name = postString("name");
//        Integer isDefault = postInt("isDefault", 0);
//        if (CommonUtil.isEmpty(addressId) || CommonUtil.isEmpty(cityId) || CommonUtil.isEmpty(address) || CommonUtil.isEmpty(detail) || CommonUtil.isEmpty(phone) || CommonUtil.isEmpty(name)) {
//            Res.fail(ErrorType.PARAM_ERR);
//        }
//        addressService.editUserAddress(getUserId(), addressId, cityId, address, detail, phone, name, isDefault);
//        Res.success();
//    }
//
//    // sun
//    // 删除地址
//    @RequestMapping("/delUserAddress")
//    public void delUserAddress () {
//        Long addressId = postLong("id");
//        if (CommonUtil.isEmpty(addressId)) {
//            Res.fail(ErrorType.PARAM_ERR);
//        }
//        addressService.delUserAddress(getUserId(), addressId);
//        Res.success();
//    }
//
//    // sun
//    // 设置默认地址
//    @RequestMapping("/setDefaultUserAddress")
//    public void setDefaultUserAddress () {
//        Long addressId = postLong("id");
//        if (CommonUtil.isEmpty(addressId)) {
//            Res.fail(ErrorType.PARAM_ERR);
//        }
//        addressService.setDefaultUserAddress(getUserId(), addressId);
//        Res.success();
//    }
//
//    // sun
//    // 获取默认地址
//    @RequestMapping("/getUserDefaultAddress")
//    public void getUserDefaultAddress () {
//        Res.success(addressService.getUserDefaultAddress(getUserId()));
//    }
//
//    // sun
//    // 获取地址列表
//    @RequestMapping("/getUserAddressList")
//    public void getUserAddressList () {
//        Res.success(addressService.getUserAddressList(getUserId()));
//    }

}
