package com.justech.mobile.mobileserver.To;

import com.justech.mobile.mobileserver.properties.SMSProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName SMSDto
 * @Author: xiaofeng.yang
 * @Date: Create in 15:00 2019/8/9
 * @Description : TODO
 * @Version: 1.0
 */
public class SMSDto {

    private String account;
    private String password;
    private String msg;
    private String phone;
    private String report;
    public SMSDto() {
    }
    public SMSDto(String msg, String phone,SMSProperties smsProperties) {
        this.msg = msg;
        this.phone = phone;
        this.account= smsProperties.getAccount();
        this.password=smsProperties.getPassword();
        this.report="true";
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}
