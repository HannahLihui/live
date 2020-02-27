function changecolor(d) {
    var list = document.getElementsByName("li1");
    for (var i = 0; i < list.length; i++) {
        if (list[i].id != d) {
            list[i].style.backgroundColor = "transparent";
            list[i].className = "unselected";
        } else {
            list[i].style.backgroundColor = "#1E9FFF";
            list[i].className = "selected";
        }
    }
}

function changecolor2(d) {
    var list = document.getElementsByName("li2");
    for (var i = 0; i < list.length; i++) {
        if (list[i].id != d) {
            list[i].style.backgroundColor = "transparent";
            list[i].className = "unselected";
        } else {
            list[i].style.backgroundColor = "#1E9FFF";
            list[i].className = "selected";
        }
    }
}

function mouseover(d) {
    var se = document.getElementById(d);
    if (se.className != "selected") {
        se.style.backgroundColor = "rgba(0,0,0,0.2)";
    }
}

function mouseout(d) {
    var se = document.getElementById(d);
    if (se.className != "selected") se.style.backgroundColor = "transparent";
}

var kind = 0;
var courseList;


function createCourseList() {
    $('.course-list-body').empty();
    var htmlstr = "";
    var temp;
    for (var i in courseList) {
        var time = new Date(courseList[i].createdTime);
        var coursetime = time.getFullYear() + "-" + filterNum(time.getMonth() + 1) + "-" + filterNum(time.getDate()) + " "
            + filterNum(time.getHours()) + ":" + filterNum(time.getMinutes());

        temp = ` <div class="one-course" onclick='window.open(&#39;chapter.html?id=${courseList[i].id}&#39;)'>
            <div class="course-info-left">
                <img class="course-img" src='${resImgUrl + courseList[i].courseHeadUrl}' onerror='this.src="res/defaultCourse.jpg"'>
            </div>
            <div class="course-info-center">
                <div>
                <span class="course-title">${courseList[i].title}</span>
                <span class="course-kind">${courseList[i].kindName}</span>
                </div>
                <div class="course-introduce">${courseList[i].introduction}</div>
            </div>
            <div class="course-info-right">
                <img class="course-head-img" src='${resImgUrl + courseList[i].userHeadUrl}' onerror='this.src="res/default.jpg"'>
                <div class="course-userName">${courseList[i].userNickName}</div>
                <div class="course-time">${coursetime}</div>
                <div class="course-info-right-bottom">
                    <img class="course-like" src="res/like.png">
                    <span class="course-like-num">${courseList[i].like}</span>
                    <img src="res/star.png">
                    <span class="course-star-num">${courseList[i].star}</span>
                </div>
            </div>
        </div>`;

        htmlstr += temp;
    }
    $('.course-list-body').append(htmlstr);
}

function getCourseList() {
    var js = {pageIndex: 1, kind: kind};
    showCourseList(js, function (data) {
        if (data.code == 100) {
            courseList = data.data;
            if (courseList != null && courseList.length > 0) {
                new myPagination({
                    id: 'page',
                    curPage: 1, //初始页码
                    pageTotal: Math.ceil(parseInt(data.msg) / 10), //总页数
                    dataTotal: parseInt(data.msg), //总共多少条数据
                    pageSize: 10, //可选,分页个数
                    showPageTotalFlag: true, //是否显示数据统计
                    showSkipInputFlag: true, //是否支持跳转
                    getPage: function (page) {
                        var json = {pageIndex: page, kind: kind};
                        showCourseList(json, function (data) {
                            courseList = data.data;
                            createCourseList();
                        });
                    }
                });
                createCourseList();
            } else {
                $('.course-list-body').empty();
                $('#page').empty();
                $('.course-list-body').append(`<h1>暂无课程信息</h1>`);
            }
        } else {
            $('#page').empty();
            $('.course-list-body').empty();
            $('.course-list-body').append(`<h1>暂无课程信息</h1>`);
            toastr.error('获取失败');
        }
    });
}

function getHotCourseList() {
    showHotCourseList(function (data) {
        courseList = data.data;
        $('#page').empty();
        if (data.code == 100 && courseList) {
                createCourseList();
        } else {
            $('.course-list-body').empty();
            $('.course-list-body').append(`<h1>暂无课程信息</h1>`);
            toastr.error('获取失败');
        }
    });
}


function getRecommendCourseList() {
    showRecommendCourseList(function (data) {
        courseList = data.data;
        $('#page').empty();
        if (data.code == 100 && courseList) {
                createCourseList();
        } else {
            $('.course-list-body').empty();
            $('.course-list-body').append(`<h1>暂无课程信息</h1>`);
            toastr.error('获取失败');
        }
    });
}



$('#2-0').on('click', function () {
    getCourseList();
});
$('#2-1').on('click', function () {
    getHotCourseList();
});
$('#2-2').on('click', function () {
    getRecommendCourseList();
});

function init() {
    var li1 = document.getElementsByName('li1');
    li1[0].onclick = function () {
        kind = parseInt(this.id);
        changecolor(this.id);
        $('.menu-pipe').css('display', 'block');
        $('#2-1').css('display', 'block');
        $('#2-2').css('display', 'block');
        getCourseList();
    };
    for (var i = 1; i < li1.length; i++) {
        li1[i].onclick = function () {
            kind = parseInt(this.id);
            changecolor(this.id);
            $('.menu-pipe').css('display', 'none');
            $('#2-1').css('display', 'none');
            $('#2-2').css('display', 'none');
            $('#2-0').click();
        };
    }

    $('#new-course').on('click', function () {
        window.location.href = 'newCourse.html';
    });


    var js = {pageIndex: 1, kind: kind};
    showCourseList(js, function (data) {
        if (data.code == 100) {
            courseList = data.data;
            if (courseList != null && courseList.length > 0) {

                new myPagination({
                    id: 'page',
                    curPage: 1, //初始页码
                    pageTotal: Math.ceil(parseInt(data.msg) / 10), //总页数
                    dataTotal: parseInt(data.msg), //总共多少条数据
                    pageSize: 10, //可选,分页个数
                    showPageTotalFlag: true, //是否显示数据统计
                    showSkipInputFlag: true, //是否支持跳转
                    getPage: function (page) {
                        var json = {pageIndex: page, kind: kind};
                        showCourseList(json, function (data) {
                            courseList = data.data;
                            createCourseList();
                        });
                    }
                });


                createCourseList();
            } else {
                $('.course-list-body').append(`<h1>暂无课程信息</h1>`);
            }
        } else {
            toastr.error('获取失败');
        }
    });


    document.getElementsByTagName("body")[0].style.zoom = 1;
    $('.top-head').css("min-width", $('.top-head').width() + 'px');
    $('.top-head').css("width", $('.top-head').width() + 'px');
    $('.tab-wrap1').css("width", $('.top-head').width() + 'px');
    $('.tab-wrap1').css("min-width", $('.top-head').width() + 'px');

    var userName = getCookie("userName");
    var headUrl = getCookie("headUrl");
    if (userName == null || userName == "null" || headUrl == null || headUrl == "null") {
        $('#user-head').attr('src', 'res/default.jpg');
        $('#user-head').attr('onclick', "window.location.href='login.html'");
        $('#user-login').append(`<p class="user" id="unlogin" onclick="window.location.href='login.html'">点击登录</p>`);
    } else {
        $('#user-head').attr('src', `${resImgUrl + headUrl}`);
        $('#user-head').attr('onerror', 'this.src="res/default.jpg"');
        $('#user-login').append(`<p class="user">${userName}</p>`);

        var usermenu = $('#user-menu');
        $('#user').hover(function () {
            usermenu.slideDown();
        }, function () {
            usermenu.hide();
        });
        usermenu.hover(function () {
            usermenu.show();
        }, function () {
            usermenu.slideUp();
        });
    }

}

init();
