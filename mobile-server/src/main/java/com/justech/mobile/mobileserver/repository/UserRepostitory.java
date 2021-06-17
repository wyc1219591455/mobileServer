package com.justech.mobile.mobileserver.repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.justech.mobile.mobileserver.To.*;
import com.justech.mobile.mobileserver.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @ClassName UserRepostitory
 * @Author: xiaofeng.yang
 * @Date: Create in 9:54 2019/7/11
 * @Description : TODO
 * @Version: 1.0
 */
@Service
public class UserRepostitory {
    @Autowired
    private UserIconRepository userIconRepository;
    @Autowired
    private JustechJobRepository justechJobRepository;
    @Autowired
    private JustechDeptRepository justechDeptRepository;
    @Autowired
    private JustechOrgRepository justechOrgRepository;
    @Autowired
    private JustechUserRepository justechUserRepository;

    private Map<String, String> sexMap = new HashMap();

    {
        sexMap.put("0", "男");
        sexMap.put("1", "女");
    }

    /**
     * @return java.util.Map
     * @Name getUserInfo
     * @Author xiaofeng.yang
     * @Description 获取用户信息
     * @Date 9:59 2019/7/11
     * @Param [number]
     **/
    public Map getUserInfo(String number) {
        //TODO
        JustechUser justechUser = justechUserRepository.findTopByEmpId(number);
        if (justechUser == null)
            return null;
        return JSONObject.parseObject(JSON.toJSONString(justechUser), Map.class);

    }


    /*
     * @Name getUserInfo
     * @Author xiaofeng.yang
     * @Description //TODO
     * @Date 16:37 2019/7/17
     * @Param [number]
     * @return com.justech.mobile.mobileserver.entity.UserOfErpEntity
     **/
    public UserOfErpEntity readOnlyUserInfo(String number) {
        Map user = this.getUserInfo(number);
        if (user == null) return null;
        UserOfErpEntity userOfErpEntity = JSON.parseObject(JSON.toJSONString(user), UserOfErpEntity.class);
        userOfErpEntity.setSex(sexMap.get(userOfErpEntity.getSex()));
        userOfErpEntity.setOrg(this.getorgEntity(userOfErpEntity.getOrg()).getName());
        //转换部门名称
        DeptEntity dept = this.getDeptEntity(userOfErpEntity.getDept());
        userOfErpEntity.setDept(dept.getDeptName());
        //部门助理

        if (dept == null || StringUtils.isEmpty(dept.getAssistant())) {
            userOfErpEntity.setAssistant(null);
        } else {
            String[] assistantsNo = dept.getAssistant().split(",");
            userOfErpEntity.setAssistant("");
            Set<String> set = new HashSet<String>(Arrays.asList(assistantsNo));
            set.stream().forEach(s -> {
                Map assistant = this.getUserInfo(s);
                if (assistant != null && !assistant.isEmpty())
                    userOfErpEntity.setAssistant(userOfErpEntity.getAssistant() + "," + String.valueOf(assistant.get("FEmpName")));
            });
            userOfErpEntity.setAssistant(userOfErpEntity.getAssistant().length() > 1 ? userOfErpEntity.getAssistant().substring(1) : "");
        }
        //转换直属上级姓名
        Map manager = this.getUserInfo(userOfErpEntity.getManager());
        if (!StringUtils.isEmpty(manager) && manager.containsKey("FEmpName")) {
            userOfErpEntity.setManagerWorkId(userOfErpEntity.getManager());
            userOfErpEntity.setManager(String.valueOf(this.getUserInfo(userOfErpEntity.getManager()).get("FEmpName")));
        } else
            userOfErpEntity.setManager(null);
        try {
            userOfErpEntity.setImageUrl(this.getImage(number).getPath());
        } catch (Exception e) {
        }
        try {
            userOfErpEntity.setJob(this.getJob(userOfErpEntity.getJob()).getName());
        } catch (Exception e) {

        }
        return userOfErpEntity;
    }


    private JobEntity getJob(String id) {
        Optional<JustechJob> optionalJustechJob = justechJobRepository.findById(id);
        if (optionalJustechJob.isPresent())
            return new JobEntity(optionalJustechJob.get().getId(), optionalJustechJob.get().getName());
        else
            return new JobEntity();
    }

    private OrgEntity getorgEntity(String id) {
        //TODO
        Optional<JustechOrg> optionalJustechOrg = justechOrgRepository.findById(id);
        if (optionalJustechOrg.isPresent()) {
            JustechOrg org = optionalJustechOrg.get();
            OrgEntity orgEntity = new OrgEntity();
            orgEntity.setName(org.getOrgName());
            orgEntity.setOrgFullName(org.getOrgFullName());
            orgEntity.setOrgID(org.getId());
            orgEntity.setParentID(org.getParentId());
            orgEntity.setOrgNO(org.getOrgNo());
            orgEntity.setUnUse(org.getUnUse());
            return orgEntity;
        } else {
            return new OrgEntity();
        }
    }

    private DeptEntity getDeptEntity(String id) {
        //TODO
        Optional<JustechDept> deptOptional = justechDeptRepository.findById(id);
        if (deptOptional.isPresent()) {
            JustechDept justechDept = deptOptional.get();
            return new DeptEntity(justechDept.getId(), justechDept.getName(), justechDept.getOrgId(), justechDept.getParentId(),
                    Boolean.valueOf(justechDept.getUnUse()), Integer.valueOf(justechDept.getType()), justechDept.getAssistant());
        } else {
            return null;
        }
    }


    public ImageTo getImage(String empNo) {
        try {
            List<UserIcon> userIcons = userIconRepository.findAllByEmpNo(empNo);
            if (userIcons == null || userIcons.isEmpty()) {
                return null;
            }
            UserIcon userIcon = userIcons.get(0);
            return new ImageTo(userIcon.getPath());
        } catch (Exception e) {
            return null;
        }
    }


}
