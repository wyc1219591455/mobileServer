package com.justech.mobile.mobileserver.controller;

import com.justech.mobile.mobileserver.common.Response;
import com.justech.mobile.mobileserver.entity.Suggestion;
import com.justech.mobile.mobileserver.service.SuggestionService;
import com.justech.mobile.mobileserver.util.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * @ClassName SuggestionController
 * @Author: xiaofeng.yang
 * @Date: Create in 14:41 2019/7/16
 * @Description : TODO
 * @Version: 1.0
 */
@RestController
public class SuggestionController {
    @Autowired
    private SuggestionService suggestionService;

    //    @RequestMapping(value = "/suggestion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes =
//            MediaType.APPLICATION_JSON_VALUE)
    @Deprecated
    public Response addSuggestion(@RequestBody Suggestion suggestion) {
        suggestionService.addSuggestion(suggestion);
        return new Response().of(ResultEnum.SUCCESS, null);
    }

    @RequestMapping(value = "/suggestions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response findAll() {
        return new Response().of(ResultEnum.SUCCESS, suggestionService.getSuggestionList());
    }

    @RequestMapping(value = "/suggestionList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response findAllByPage(@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize, @RequestParam(value =
            "pageNo", required = false, defaultValue = "0") int pageNo) {
        return new Response().of(ResultEnum.SUCCESS, suggestionService.getSuggestionList(pageSize,pageNo));
    }

    @RequestMapping(value = "/suggestion",method =RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response addSuggestion(@RequestParam(value = "content") String content, @RequestParam(value = "evaluate") int evaluate,
                                  @RequestParam(value = "suggester") String suggester, @RequestParam("files") MultipartFile[] multipartFile) {
        if (multipartFile.length > 3) return new Response().of(ResultEnum.BAD, null);
        Suggestion suggestion = new Suggestion(content, evaluate, suggester, new Date());
        try {
            suggestionService.addSuggestionWithIamge(suggestion, multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
            return new Response().of(ResultEnum.BAD, null);
        }
        return new Response().of(ResultEnum.SUCCESS, null);
    }
}
