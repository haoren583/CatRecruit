package com.cat.recruit.controller;

import com.cat.recruit.common.exception.BusinessException;
import com.cat.recruit.common.exception.BusinessExceptionEnum;
import com.cat.recruit.common.result.Result;
import com.cat.recruit.common.result.ResultEnum;
import com.cat.recruit.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author cat
 */
@RestController
public class UserController {
    private static final String APPID = "你的appid";
    private static final String SECRET = "你的secret";
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("api/wx/login/**")
    public Result login(@RequestParam("code") String code) throws Exception {
        // TODO: token相关都还没写
        //code:临时登录凭证
            // 构造微信官方接口 URL，js_code 就是前端传来的 code
            String url = String.format(
                    "https://api.weixin.qq.com/sns/jscode2session" +
                            "?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
                    APPID, SECRET, code
            );
            // RestTemplate 用于发送 HTTP GET 请求
            RestTemplate restTemplate = new RestTemplate();
            // 调用微信接口，返回 JSON 字符串
            String response = restTemplate.getForObject(url, String.class);

            // 使用 Jackson 的 ObjectMapper 解析 JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response);
            // 如果返回 JSON 中有 errcode，说明请求失败
            if (jsonNode.has("errcode")) {
                // 可以返回给前端或者在日志中记录
                throw BusinessException.bizException(BusinessExceptionEnum.BAD_REQUEST);
            }
            // 从返回的 JSON 中获取 openId
            String openId = jsonNode.get("openid").asText();
            if (userService.selectUserIsExist(openId) == 0) {
                return userService.createUser(openId);
            }else {
                return Result.success(openId);
            }
    }
}
