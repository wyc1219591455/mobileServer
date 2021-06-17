package com.justech.mobile.mobileserver.service;

import com.alibaba.fastjson.JSON;
import com.justech.mobile.mobileserver.To.*;
import com.justech.mobile.mobileserver.entity.JustechDept;
import com.justech.mobile.mobileserver.entity.JustechOrg;
import com.justech.mobile.mobileserver.entity.JustechOrgTree;
import com.justech.mobile.mobileserver.entity.JustechUser;
import com.justech.mobile.mobileserver.repository.*;
import com.justech.mobile.mobileserver.util.OrganizationEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName
 * @Author: xiaofeng.yang
 * @Date: Create in 13:38 2019/10/22
 * @Description : TODO
 * @Version: 1.0
 */
@Service
@Slf4j
public class OrganizationService {
    @Autowired
    private JustechOrgRepository justechOrgRepository;

    @Autowired
    private JustechDeptRepository justechDeptRepository;

    @Autowired
    private JustechUserRepository justechUserRepository;
    @Autowired
    private JustechOrgTreeRepository justechOrgTreeRepository;
    @Autowired
    private UserRepostitory userRepostitory;


    public List<OrganizationTo> getAllOrganization() {
//        List<JustechOrg> justechOrgs = justechOrgRepository.findAllByUnUseOrderByOrdSeq("false");
        List<JustechOrgVo> justechOrgVos = justechOrgRepository.findAllByUnUseAndCount("false");
//        List<JustechDept> justechDepts = justechDeptRepository.findAllByUnUse("false");
        List<JustechDeptVo> justechDeptVos = justechDeptRepository.findAllByUnUseAndCount("false");
        List<OrganizationTo> list = new ArrayList<>();
        for (JustechOrgVo org : justechOrgVos) {
            if ("0".equals(org.getParentId())) {
                OrganizationTo organizationTo = new OrganizationTo(org);
                List<OrganizationTo> childrenOrg = new ArrayList<>();
                for (JustechOrgVo org1 : justechOrgVos) {
                    if (org.getId().equals(org1.getParentId())) {
                        OrganizationTo organizationTo1 = new OrganizationTo(org1);
                        childrenOrg.add(organizationTo1);
                    }
                }
                for (JustechDeptVo dept : justechDeptVos) {
                    if (org.getId().equals(dept.getOrgId()) && org.getId().equals(dept.getParentId())) {
                        OrganizationTo organizationTodept = new OrganizationTo(dept);
                        childrenOrg.add(organizationTodept);
                    }
                }
                organizationTo.setOrgChildren(childrenOrg);
                list.add(organizationTo);
            }
        }
        return list;
    }

    public OrganizationTo getAllOrganization(OrganizationEnum organizationEnum, String id) {
        if ("ORG".equals(organizationEnum.name()))
            return this.getOrganizationByOrg(id);
        if ("DEPT".equals(organizationEnum.name()))
            return this.getOrganizationByDept(id);
        return null;
    }

    private OrganizationTo getOrganizationByOrg(String id) {
        JustechOrg org = null;
        JustechOrgTree justechOrgTree = null;
        try {
            org = justechOrgRepository.findById(id).get();
            justechOrgTree = justechOrgTreeRepository.findById(id).get();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (org == null || "true".equals(org.getUnUse())) {
            return null;
        }
//        List<JustechOrg> justechOrgs = justechOrgRepository.findAllByUnUseOrderByOrdSeq("false");
        List<JustechOrgVo> justechOrgVos = justechOrgRepository.findAllByUnUseAndCount("false");
//        List<JustechDept> justechDepts = justechDeptRepository.findAllByParentIdAndOrgId(org.getId(), org.getId());
        List<JustechDeptVo> deptVos = justechDeptRepository.findAllByParentIdAndOrgIdAndCount(org.getId(), org.getId(), "false");
        OrganizationTo organizationTo = new OrganizationTo(org);
        organizationTo.setNum(justechOrgTree.getPNum().intValue());
        List<OrganizationTo> childrenOrg = new ArrayList<>();
        JustechOrg finalOrg = org;
        justechOrgVos.forEach(org1 -> {
            if (finalOrg.getId().equals(org1.getParentId())) {
                childrenOrg.add(new OrganizationTo(org1));
            }
        });
        deptVos.forEach(dept -> {
            if ("false".equals(dept.getUnUse())) {
                childrenOrg.add(new OrganizationTo(dept));
            }
        });
        organizationTo.setOrgChildren(childrenOrg);
        return organizationTo;
    }

    private OrganizationTo getOrganizationByDept(String id) {
        JustechDept dept = null;
        JustechOrgTree justechOrgTree = null;
        try {
            dept = justechDeptRepository.findById(id).get();
            justechOrgTree = justechOrgTreeRepository.findById(id).get();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (dept == null || "true".equals(dept.getUnUse()))
            return null;
//        List<JustechDept> depts = justechDeptRepository.findAllByParentId(id);
        List<JustechDeptVo> deptVos = justechDeptRepository.findAllByParentIdAndCount(id, "false");
        List<JustechUser> users = justechUserRepository.findAllByDeptId(id);
        OrganizationTo organizationTo = new OrganizationTo(dept);
        organizationTo.setNum(justechOrgTree.getPNum().intValue());
        List<OrganizationTo> childrenOrg = new ArrayList<>();
        List<JustechUserVo> justechUserVos = new ArrayList<>();
        deptVos.forEach(dept1 -> {
            childrenOrg.add(new OrganizationTo(dept1));
        });
        users.forEach(user -> {
            String icon=null;
            try {
                ImageTo imageTo = userRepostitory.getImage(user.getEmpId());
                icon=imageTo.getPath();
            }catch (Exception e){

            }
            justechUserVos.add(new JustechUserVo(user,icon));
        });
        organizationTo.setOrgChildren(childrenOrg);
        organizationTo.setUserChildren(justechUserVos);
        return organizationTo;
    }
}
