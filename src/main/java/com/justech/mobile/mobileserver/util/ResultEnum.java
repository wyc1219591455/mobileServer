package com.justech.mobile.mobileserver.util;

public enum ResultEnum {
    SUCCESS(0,"success"),
    BAD(1000,"request bad"),
    INVALIDPARAM(1001,"invalid params"),
    INTERNALFAIL(1002,"fail on getting the data internally"),
    NOTFOUND(1004,"not found"),
    FORBIDDEN(1403," invalid authorization"),
    PARAMS_VALID_ERR(1005, "parameter validation error"),
    PASSWORD_EXPRIED(1006,"password has expired , please reset it!"),
    NEW_USER_RESETPASSWORD(1007," you are a new user, please reset password quickly !"),
    UNSUPPORT_DEVICETYPE(1008," unsupport devicetype !"),
    MESSAGE_SEND_ERROR(1009,"message send error!"),
    TOO_MANY_REQUEST(1010,"短信已经发送,请一分钟后再试！"),
    USER_NOT_FIND(1011,"手机号不存在,请确认在公司HR登记的号码是否有误!"),
    SMS_CODE_ERROR(1012,"短信验证码错误!"),
    GRANT_FAILED(1013,"授权失败!"),
    UNBIND_FAILED(1014,"解绑失败!"),
    ACCOUNT_NOT_FIND(1015,"用户不存在!"),
    DEVICE_NOT_FIND(1016,"设备不存在!");

    private int code;
    private String msg;
    ResultEnum(int code, String msg) {
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
