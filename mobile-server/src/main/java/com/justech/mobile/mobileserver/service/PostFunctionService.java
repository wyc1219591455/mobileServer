package com.justech.mobile.mobileserver.service;

import com.justech.mobile.mobileserver.To.FunUserTo;
import com.justech.mobile.mobileserver.To.PostFunctionInfoTo;
import com.justech.mobile.mobileserver.entity.PostFunctionType;
import com.justech.mobile.mobileserver.entity.PostFunctionUser;
import com.justech.mobile.mobileserver.repository.PostFunctionTypeRepostitory;
import com.justech.mobile.mobileserver.repository.PostFunctionUserRepostitory;
import com.justech.mobile.mobileserver.repository.UserRepostitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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
         * 耗时较长
         */
        List tasks = new ArrayList<Callable<String>>();


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

        ExecutorService pool = Executors.newCachedThreadPool();

        try {
            pool.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /**
         * end
         */

        System.err.println(list.size());

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


}
