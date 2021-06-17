package com.justech.mobile.mobileserver.To;

import com.justech.mobile.mobileserver.entity.JustechDept;
import com.justech.mobile.mobileserver.entity.JustechOrg;
import com.justech.mobile.mobileserver.entity.JustechUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.naming.Name;
import java.util.List;

/**
 * @ClassName
 * @Author: xiaofeng.yang
 * @Date: Create in 13:49 2019/10/22
 * @Description : TODO
 * @Version: 1.0
 */
@Getter
@Setter
public class OrganizationTo {
    private String id;
    private String name;
    private String type;
    private int num;
    private List<OrganizationTo> orgChildren;
    private List<JustechUserVo> userChildren;
    private JustechUserVo manager;
    public OrganizationTo(){

    }
    public OrganizationTo(JustechDeptVo deptVo){
        this.id=deptVo.getId();
        this.name=deptVo.getName();
        this.num=deptVo.getNum().intValue();
        this.type="DEPT";
    }
    public OrganizationTo(JustechOrgVo justechOrgVo){
        this.id=justechOrgVo.getId();
        this.name=justechOrgVo.getOrgName();
        this.num=justechOrgVo.getNum().intValue();
        this.type="ORG";
    }
    public OrganizationTo(JustechOrg org){
        this.id=org.getId();
        this.name=org.getOrgName();
        this.type="ORG";
    }
    public OrganizationTo(JustechDept dept){
        this.id=dept.getId();
        this.name=dept.getName();
        this.type="DEPT";
    }

}
