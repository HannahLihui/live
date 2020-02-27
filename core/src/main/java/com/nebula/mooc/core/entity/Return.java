/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.core.entity;

import com.nebula.mooc.core.Constant;

import java.io.Serializable;

/*
 * Common return
 * @param T Data type
 */
public class Return<T> implements Serializable {
    public static final long serialVersionUID = 1L;

    // 成功
    public static final Return<String> SUCCESS = new Return<>();
    // 验证码错误
    public static final Return<String> CODE_ERROR = new Return<>(Constant.CLIENT_ERROR_CODE, "验证码错误，请重试！");
    // 服务器错误
    public static final Return<String> SERVER_ERROR = new Return<>(Constant.SERVER_ERROR_CODE);
    // 已点赞或收藏
    public static final Return<String> STAR_LIKE_ALREADY = new Return<>(Constant.STAR_LIKE_ALREADY);
    // 取消点赞或收藏
    public static final Return<String> UN_STAR_LIKE = new Return<>(Constant.UN_STAR_LIKE);
    // 参数错误
    public static final Return<String> CLIENT_PARAM_ERROR = new Return<>(Constant.CLIENT_PARAM_ERROR);

    private int code;
    private String msg;
    private T data;

    public Return() {
        this.code = Constant.SUCCESS_CODE;
    }

    public Return(int code) {
        this.code = code;
    }

    public Return(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Return(T data) {
        this.code = Constant.SUCCESS_CODE;
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
