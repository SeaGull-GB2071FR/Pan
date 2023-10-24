package com.GB2071FR.Pan.controller;


import com.GB2071FR.Pan.annotation.GlobalInterceptor;
import com.GB2071FR.Pan.annotation.VerifyParam;
import com.GB2071FR.Pan.entity.constants.Constants;
import com.GB2071FR.Pan.entity.dto.CreateImageCode;
import com.GB2071FR.Pan.entity.dto.SessionWebUserDto;
import com.GB2071FR.Pan.entity.enums.VerifyRegexEnum;
import com.GB2071FR.Pan.entity.vo.ResponseVO;
import com.GB2071FR.Pan.exception.BusinessException;
import com.GB2071FR.Pan.service.EmailCodeService;
import com.GB2071FR.Pan.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author GB2071FR
 * @since 2023-10-10
 */
@RestController
public class UserInfoController extends ABaseController {

    //    已完成：Nginx 的 nginx.conf 配置记得改
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private EmailCodeService emailCodeService;


    //      获取验证码
    @RequestMapping("/checkCode")
    public void checkCode(HttpServletResponse response, HttpSession session, Integer type) throws IOException {
        CreateImageCode vCode = new CreateImageCode(130, 38, 5, 10);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        String code = vCode.getCode();
        if (type == null || type == 0) {
            session.setAttribute(Constants.CHECK_CODE_KEY, code);
        } else {
            session.setAttribute(Constants.CHECK_CODE_KEY_EMAIL, code);
        }
        vCode.write(response.getOutputStream());

    }

    //      发送邮箱验证码
    @RequestMapping("/sendEmailCode")
    /**
     * AOP参数拦截
     */
    @GlobalInterceptor(checkParams = true,checkLogin = false)
    public ResponseVO sendEmailCode(HttpSession session,
                                    @VerifyParam(required = true, regex = VerifyRegexEnum.EMAIL, max = 150) String email,
                                    @VerifyParam(required = true) String checkCode,
                                    @VerifyParam(required = true) Integer type) {
        /**
         * 1 判断验证码是否正确
         * 2 正确则开始发送邮箱验证码
         *
         */

        try {
            if (!(checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY_EMAIL)))) {
                throw new BusinessException("验证码不正确");
            }
            emailCodeService.sendEmailCode(email, type);
            return getSuccessResponseVO(null);

        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY_EMAIL);
        }

    }


    //      注册
    @RequestMapping("/register")
    /**
     * AOP参数拦截
     */
    @GlobalInterceptor(checkParams = true,checkLogin = false)
    public ResponseVO register(HttpSession session,
                               @VerifyParam(required = true, regex = VerifyRegexEnum.EMAIL, max = 150) String email,
                               @VerifyParam(required = true) String nickName,
                               @VerifyParam(required = true, regex = VerifyRegexEnum.PASSWORD, min = 8, max = 18) String password,
                               @VerifyParam(required = true) String checkCode,
                               @VerifyParam(required = true) String emailCode) {
        /**
         * 1 判断验证码是否正确
         * 2 正确则开始发送邮箱验证码
         *
         */

        try {
            if (!(checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY)))) {
                throw new BusinessException("图片验证码不正确");
            }

            userInfoService.register(email, nickName, password, emailCode);
            return getSuccessResponseVO(null);

        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY);
        }

    }


    //      注册
    @RequestMapping("/login")
    /**
     * AOP参数拦截
     */
    @GlobalInterceptor(checkParams = true,checkLogin = false)
    public ResponseVO login(HttpSession session,
                            @VerifyParam(required = true) String email,
                            @VerifyParam(required = true) String password,
                            @VerifyParam(required = true) String checkCode) {
        /**
         * 1 判断验证码是否正确
         * 2 正确则开始发送邮箱验证码
         *
         */

        try {
            if (!(checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY)))) {
                throw new BusinessException("验证码不正确");
            }

            SessionWebUserDto sessionWebUserDto = userInfoService.login(email, password);
            session.setAttribute(Constants.SESSION_KEY, sessionWebUserDto);
            return getSuccessResponseVO(sessionWebUserDto);

        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY);
        }

    }
}

