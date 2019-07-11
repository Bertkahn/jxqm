package com.cloud.common.constant;

public class OrderConst {
    // 订单类型
    public static final int orderType_online = 1;
    public static final int orderType_locale = 2;
    // 支付类型
    public static final int payMethod_ali = 1;
    public static final int payMethod_wxSdk = 2;
    public static final int payMethod_wxMin = 3;
    public static final int payMethod_yibaoWxSdk = 4;
    public static final int payMethod_yibaoWxMin = 5;
    // 支付状态
    public static final int orderStatus_un = 0;
    public static final int orderStatus_pay = 1;
    public static final int orderStatus_cancel = 2;
    public static final int orderStatus_close = 3;
    public static final int orderStatus_send = 4;
    public static final int orderStatus_receive = 5;
    public static final int orderStatus_complete = 6;
    // 退款状态
    public static final int refundStatus_part = 1;
    public static final int refundStatus_all = 2;


}
