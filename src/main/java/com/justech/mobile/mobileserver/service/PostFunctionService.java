package com.justech.mobile.mobileserver.service;

import com.justech.mobile.mobileserver.To.FunUserTo;
import com.justech.mobile.mobileserver.To.FunctionTreeTo;
import com.justech.mobile.mobileserver.To.PostFunctionInfoTo;
import com.justech.mobile.mobileserver.To.UserOfErpEntity;
import com.justech.mobile.mobileserver.entity.PostFunctionType;
import com.justech.mobile.mobileserver.entity.PostFunctionUser;
import com.justech.mobile.mobileserver.repository.PostFunctionTypeRepostitory;
import com.justech.mobile.mobileserver.repository.PostFunctionUserRepostitory;
import com.justech.mobile.mobileserver.repository.UserRepostitory;
import com.justech.mobile.mobileserver.util.CumstonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName PostFunctionService
 * @Author: xiaofeng.yang
 * @Date: Create in 10:10 2019/10/12
 * @Description : TODO
 * @Version: 1.0
 */
@Service
public class PostFunctionService {

    @Autowired
    private PostFunctionTypeRepostitory postFunctionTypeRepostitory;

    @Autowired
    private PostFunctionUserRepostitory postFunctionUserRepostitory;

    @Autowired
    private UserRepostitory userRepostitory;

    public List<PostFunctionInfoTo> getAllFunctionInfo() {
        Sort sort = new Sort(Sort.Direction.DESC, "sort");
        List<PostFunctionType> types = postFunctionTypeRepostitory.findAll(sort);
        List<PostFunctionUser> users = postFunctionUserRepostitory.findAll(sort);
        List<PostFunctionInfoTo> list = new ArrayList<>();

        /**
         * 线程获取职能窗口平台数据
         */

        List tasks = new ArrayList<Callable<String>>();
        ExecutorService pool = Executors.newFixedThreadPool(28);

        for (PostFunctionType type : types) {

            Callable<String> task = () -> {

                PostFunctionInfoTo postFunctionInfoTo = new PostFunctionInfoTo(type);
                List<FunUserTo> childUsers = new ArrayList<>();
                for (PostFunctionUser user : users) {
                    if (user.getTId() == postFunctionInfoTo.getId()) {
                        childUsers.add(new FunUserTo(user.getFunName(), userRepostitory.readOnlyUserInfo(user.getEmpNo()), user));
                    }
                }
                postFunctionInfoTo.setChildUsers(childUsers);
                list.add(postFunctionInfoTo);
                return "ok";
            };

            tasks.add(task);
        }


        try {
            pool.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * end 主要耗时 1000ms 左右
         */


        List<PostFunctionInfoTo> returnList = new ArrayList<>();
        int defaultlevle = 1;
        for (PostFunctionInfoTo info : list) {
            if (info.getLevel() == defaultlevle) {
                info.setChildPostFunctionInfoTo(functionInfoTos(info, list));
                returnList.add(info);
            }
        }
        return returnList;
    }

    private List<PostFunctionInfoTo> functionInfoTos(PostFunctionInfoTo postFunctionInfoTo, List<PostFunctionInfoTo> list) {
        List<PostFunctionInfoTo> tempList = new ArrayList<>();
        for (PostFunctionInfoTo info : list) {
            if (info.getPId() != null && info.getPId() == postFunctionInfoTo.getId()) {
                info.setChildPostFunctionInfoTo(functionInfoTos(info, list));
                tempList.add(info);
            }
        }
        return tempList;
    }

    public PostFunctionType addType(PostFunctionType type) throws CumstonException {
        PostFunctionType newType = new PostFunctionType();
        newType.setSort(type.getSort());
        newType.setName(type.getName());
        if (type.getPId() == null) {
            newType.setLevel(1);
        } else {
            PostFunctionType ptype = postFunctionTypeRepostitory.getOne(type.getPId());
            if (ptype == null)
                throw new CumstonException("parent node invaild");
            newType.setPId(ptype.getId());
            newType.setLevel(ptype.getLevel() + 1);
        }
        return postFunctionTypeRepostitory.save(newType);
    }

    public PostFunctionUser addUser(PostFunctionUser user) throws CumstonException {
        PostFunctionUser newUser = new PostFunctionUser();
        newUser.setSort(user.getSort());
        if (user.getEmpNo() == null || "".equals(user.getTId() + "")) {
            throw new CumstonException("params invaild");
        }
        if (postFunctionTypeRepostitory.getOne(user.getTId()) == null) {
            throw new CumstonException("type invaild");
        }
        newUser.setTId(user.getTId());
        UserOfErpEntity userOfErpEntity = userRepostitory.readOnlyUserInfo(user.getEmpNo());
        if (userOfErpEntity == null)
            throw new CumstonException("user invaild");
        newUser.setEmpNo(user.getEmpNo());
        newUser.setFunName(user.getFunName());
        newUser.setUserName(userOfErpEntity.getUserName());
        return postFunctionUserRepostitory.save(newUser);
    }

    public List<FunctionTreeTo> adminAll() {
        List<PostFunctionInfoTo> list = this.getAllFunctionInfo();
        List<FunctionTreeTo> functionTreeTos = coverTree(list);
        return functionTreeTos;
    }

    private List<FunctionTreeTo> coverTree(List<PostFunctionInfoTo> list) {
        List<FunctionTreeTo> functionTreeTos = new ArrayList<>();
        for (PostFunctionInfoTo postFunctionInfoTo : list) {
            FunctionTreeTo functionTreeTo = new FunctionTreeTo();
            functionTreeTo.setId(postFunctionInfoTo.getId());
            functionTreeTo.setLevel(postFunctionInfoTo.getLevel());
            functionTreeTo.setSort(postFunctionInfoTo.getSort());
            functionTreeTo.setFunname(postFunctionInfoTo.getName());
            if (postFunctionInfoTo.getPId() != null)
                functionTreeTo.setPid(postFunctionInfoTo.getPId().intValue());
            functionTreeTo.setType("type");
            List<FunctionTreeTo> functionTreeToList = functionTreeTo.getChildren() == null ? new ArrayList<>() : functionTreeTo.getChildren();
            if (postFunctionInfoTo.getChildPostFunctionInfoTo() != null && !postFunctionInfoTo.getChildPostFunctionInfoTo().isEmpty()) {
                functionTreeToList.addAll(coverTree(postFunctionInfoTo.getChildPostFunctionInfoTo()));
                functionTreeTo.setChildren(functionTreeToList);
            }
            if (postFunctionInfoTo.getChildUsers() != null && !postFunctionInfoTo.getChildUsers().isEmpty()) {
                functionTreeToList.addAll(coverUserToTree(postFunctionInfoTo.getChildUsers()));
                functionTreeTo.setChildren(functionTreeToList);
            }
            functionTreeTos.add(functionTreeTo);
        }
        return functionTreeTos;
    }

    private List<FunctionTreeTo> coverUserToTree(List<FunUserTo> list) {
        List<FunctionTreeTo> list1 = new ArrayList<>();
        for (FunUserTo funUserTo : list) {
            FunctionTreeTo functionTreeTo = new FunctionTreeTo();
            functionTreeTo.setType("user");
            functionTreeTo.setId(funUserTo.getPostFunctionUser().getId());
            functionTreeTo.setPid(funUserTo.getPostFunctionUser().getTId());
            functionTreeTo.setFunname(funUserTo.getFunName());
            functionTreeTo.setSort(funUserTo.getPostFunctionUser().getSort());
            functionTreeTo.setName(funUserTo.getUserOfErpEntity().getUserName());
            functionTreeTo.setEmpNo(funUserTo.getPostFunctionUser().getEmpNo());
            list1.add(functionTreeTo);
        }
        return list1;
    }

    public void deleteUser(Integer id) {
        postFunctionUserRepostitory.deleteById(id);
    }

    public void deleteType(Integer id) throws CumstonException {
        List childType = postFunctionTypeRepostitory.findAllByPId(id);
        if (childType != null && !childType.isEmpty())
            throw new CumstonException("hava children");
        List childUser = postFunctionUserRepostitory.findAllByTId(id);
        if (childUser != null && !childUser.isEmpty())
            throw new CumstonException("hava children");
        postFunctionTypeRepostitory.deleteById(id);
    }

    public PostFunctionUser getOneById(int id) {
        return postFunctionUserRepostitory.getOne(id);
    }

    public PostFunctionType getOneBy(int id) {
        return postFunctionTypeRepostitory.getOne(id);
    }

    public PostFunctionType update(PostFunctionType type) {
        return postFunctionTypeRepostitory.save(type);
    }

    public PostFunctionUser update(PostFunctionUser user) throws CumstonException {
        UserOfErpEntity userOfErpEntity = userRepostitory.readOnlyUserInfo(user.getEmpNo());
        if (userOfErpEntity == null)
            throw new CumstonException("user invaild");
        user.setUserName(userOfErpEntity.getUserName());
        return postFunctionUserRepostitory.save(user);
    }

}
