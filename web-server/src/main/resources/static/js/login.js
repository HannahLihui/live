var ifsign = false;//根据此布尔值判断当前为注册状态还是登录状态
var ifgetmail = false;//是否发送邮件
var ifcheck = false;//账号是否通过检查
var confirm = document.getElementsByClassName("confirm")[0];
var sendcode = document.getElementById("send_code");
var canvas = document.getElementById("img");
var status_title = document.getElementById("flag");
var forget_title = document.getElementById("forget");
var log_bt = document.getElementById("log_bt");
var sign_bt = document.getElementById("sign_bt");
var submit_bt = document.getElementById("submit_bt");
var cannel_bt = document.getElementById("cannel_bt");
var nickNameIn = document.getElementsByClassName("nick")[0];
var check = document.getElementById("check");

//邮件地址正则
var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;

//刷新图片验证码
function refresh() {
    document.getElementById("img").src = "/sys/code/getImgCode?" + Math.random();
}

$(document).keypress(function (e) {
    // 回车键事件
    if (e.which == 13) {
        $('input[id="sign_bt"]').click();
    }
});
//粒子背景特效
$('body').particleground({
    dotColor: '#E8DFE8',
    lineColor: '#1b3273'
});

$('input[name="pwd"]').focus(function () {
    $(this).attr('type', 'password');
});
$('input[type="text"],input[type="password"]').focus(function () {
    $(this).prev().animate({'opacity': '1'}, 200);
});
$('input[type="text"],input[type="password"]').blur(function () {
    $(this).prev().animate({'opacity': '.5'}, 200);
});

var open = 0;

//登录按钮
$('input[id="log_bt"]').click(function () {

    var login = $('.username').val();
    var password = $('.passwordNumder').val();
    var code = $('.ValidateNum').val();
    if (login == '') {
        toastr.warning('请输入您的账号');
        return false;
    } else if (login.search(reg) == -1) {
        toastr.warning('邮箱地址不正确');
        return false;
    } else if (password == '') {
        toastr.warning('请输入密码');
        return false;
    } else if (code == '' || code.length != 5) {
        toastr.warning('输入验证码');
        return false;
    } else {
        //登陆
        var md5pass = md5(password);
        var JsonData = {username: login, password: md5pass, code: code};
        toastr.info('登录中...');
        Login(JsonData, function (data) {
            //ajax返回
            if (data.code == 100) {
                //登录成功
                toastr.success('登录成功');
                //跳转操作

                document.cookie = "userId=" + data.data.id + ";";
                document.cookie = "userName=" + data.data.nickName + ";";
                document.cookie = "headUrl=" + data.data.headUrl + ";";
                window.location.href = "index.html";


            } else {
                toastr.error(data.msg);
            }
        });
    }
});

//注册按钮
$('input[id="sign_bt"]').click(function () {

    $('.username').val("");
    $('.passwordNumder').val("");
    $('.ValidateNum').val("");
    log_bt.style.visibility = "hidden";
    sign_bt.style.visibility = "hidden";
    submit_bt.style.visibility = "visible";
    cannel_bt.style.visibility = "visible";
    forget_title.style.display = "none";
    nickNameIn.style.display = "block";
    canvas.style.display = "none";
    sendcode.style.display = "block";
    confirm.style.display = "block";
    status_title.innerText = "注册";
    ifsign = true;
});

//确认按钮
$('input[id="submit_bt"]').click(
    function () {
        var login = $('.username').val();
        var password = $('.passwordNumder').val();
        var cpwd = $('.confirmpasswordNumder').val();
        var code = $('.ValidateNum').val();
        var nick = $('.nickname').val();

        if (login == '') {
            toastr.warning('请输入您的账号');
            return false;
        } else if (login.search(reg) == -1) {
            toastr.warning('邮箱地址不正确');
            return false;
        } else if (password == '' || cpwd == '') {
            toastr.warning('请输入密码');
            return false;
        } else if (password != cpwd) {
            toastr.warning('密码不一致');
            return false;
        } else if (code == '' || code.length != 5) {
            toastr.warning('输入验证码');
            return false;
        } else if (!ifgetmail) {
            toastr.warning('请获取邮件验证码');
            return false;
        } else {
            if (ifsign) {
                if (nick == '') {
                    toastr.warning('请输入昵称');
                    return false;
                }
                toastr.info('注册中...');
                var md5pass = md5(password);
                //封装json
                var JsonData = {username: login, password: md5pass, code: code, nickname: nick};
                register(JsonData, function (data) {
                    //ajax返回
                    if (data.code == 100) {
                        //注册成功
                        toastr.success("注册成功，请登录！");
                        setTimeout(function () {
                            window.location.reload()
                        }, 2000);
                    } else {
                        toastr.error(data.msg);
                    }
                });
            } else {
                toastr.info('认证中...');
                var md5pass = md5(password);
                //封装json
                var JsonData = {username: login, password: md5pass, code: code};
                findPwd(JsonData, function (data) {
                    //ajax返回
                    if (data.code == 100) {
                        //成功
                        toastr.success("找回密码成功,请重新登录！");
                        window.location.reload();
                    } else {
                        toastr.error(data.msg);
                    }
                });
            }
        }
    }
);

//账号输入框失焦时检查是否可用
$('#user-name').blur(
    function () {
        if (ifsign) {
            check.style.visibility = 'visible';
            var login = $('.username').val();
            if (login.search(reg) == -1) {
                check.setAttribute('src', 'res/wrong.png');
                toastr.warning('邮箱格式错误');
                ifcheck = false;
                return;
            }
            checkUser(login, function (data) {
                if (data.code == 100) {
                    check.setAttribute('src', 'res/right.png');
                    toastr.success('账号可用');
                    ifcheck = true;
                } else {
                    check.setAttribute('src', 'res/wrong.png');
                    toastr.warning(data.msg);
                    ifcheck = false;
                }
            });
        }
    }
);


//获取邮件验证码按钮
$('input[id="send_code"]').click(
    function () {
        var login = $('.username').val();
        if (login == '') {
            toastr.warning('请输入您的账号');
            return false;
        } else if (login.search(reg) == -1) {
            toastr.warning('邮箱地址不正确');
            return false;
        } else if (!ifcheck) {
            toastr('该账号不可用');
            return false;
        } else {
            sendcode.setAttribute("disabled", true);
            sendcode.value = '获取中。。。';
            getemail(
                function (data) {
                    if (data.code == 100) {
                        ifgetmail = true;
                        toastr.success('验证码已发送');
                        cooltime(sendcode);
                    } else {
                        toastr.warning(data.msg);
                        sendcode.removeAttribute("disabled");
                        sendcode.value = '获取验证码';
                    }
                }, sendcode
            );
        }
    }
);

//取消按钮
$('input[id="cannel_bt"]').click(
    function () {
        status_title.innerText = "登录";
        confirm.style.display = "none";
        canvas.style.display = "block";
        sendcode.style.display = "none";
        nickNameIn.style.display = "none";
        $('.username').val("");
        $('.passwordNumder').val("");
        $('.ValidateNum').val("");
        $('.nickname').val("");
        forget_title.style.display = "block";
        log_bt.style.visibility = "visible";
        sign_bt.style.visibility = "visible";
        submit_bt.style.visibility = "hidden";
        cannel_bt.style.visibility = "hidden";
        check.style.visibility = 'hidden';
        ifsign = false;
    }
);

function findback() {
    status_title.innerText = "找回密码";
    confirm.style.display = "block";
    canvas.style.display = "none";
    sendcode.style.display = "block";
    $('.username').val("");
    $('.passwordNumder').val("");
    $('.confirmpasswordNumder').val("");
    $('.ValidateNum').val("");
    forget_title.style.display = "none";
    log_bt.style.visibility = "hidden";
    sign_bt.style.visibility = "hidden";
    submit_bt.style.visibility = "visible";
    cannel_bt.style.visibility = "visible";
    ifsign = false;
}




