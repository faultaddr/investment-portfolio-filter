<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="entity.GUserEntity" %>
<%

    GUserEntity gUserEntity = (GUserEntity) session.getAttribute("user");
    // 获取session创建时间
    String name = gUserEntity.getUserName();
    System.out.print(name);
%>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>index</title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="icon" type="image/png" href="table/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="table/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
    <script src="table/js/echarts.min.js"></script>
    <link rel="stylesheet" href="table/css/amazeui.min.css"/>
    <link rel="stylesheet" href="table/css/amazeui.datatables.min.css"/>
    <link rel="stylesheet" href="table/css/app.css">
    <script src="table/js/jquery.min.js"></script>
    <script>
        function showContent(s){
            alert("aaa");
            myWindow=window.open('','','width=1000,height=1000')
            myWindow.document.write("<html lang='en'><head><link rel='stylesheet' href='table/css/entry.css' /> <div class='entry'><p>" +
                    s+
                "</p></div></head></html>");
        }
        function loseFocus(obj) {

            count=0;
            $(obj).find('td').each(function () {

                var old=$('#input'+count).val();
                if (old.length != 0) {
                    $(this).html("<b>" + old + "</b>");
                }
                count++;
            })
        }

        function postUpdate() {
            //alert("aaaa")

            var gUserEntity = {};
            gUserEntity.userName = $('#input0').val();
            gUserEntity.userId = $('#input1').val();
            gUserEntity.userWechat = $('#input2').val();
            gUserEntity.userTelephoneNumber = $('#input3').val();
            gUserEntity.userGrade = $('#input4').val();
            gUserEntity.userClass = $('#input5').val();
            gUserEntity.userInstitution = $('#input6').val();
            gUserEntity.userRole = $('#input7').val();
            gUserEntity.userPassword = $('#input8').val();
            gUserEntity.userAlipay = $('#input9').val();
            //alert(JSON.stringify(gUserEntity))
            post(gUserEntity)


        }
        function post(obj) {
            $.ajax({
                url: '/updateUser',
                type: 'POST', //GET
                async: false,    //或false,是否异步
                data: JSON.stringify(obj),
                contentType: "application/json",
                timeout: 5000,    //超时时间
                dataType: 'text',    //返回的数据格式：json/xml/html/script/jsonp/text
                /* beforeSend:function(xhr){
                 console.log(xhr)
                 console.log('发送前')
                 },*/

                success: function (data, textStatus, jqXHR) {

                    if (data == "true") {
                        alert("修改成功")
                    }

                },
                error: function (xhr, textStatus) {
                    alert("sb")
                    alert('错误')
                    alert(xhr)
                    alert(textStatus)
                    //window.location.href="404.html"
                },
                complete: function () {

                }
            })
        }
        function changeInput(obj) {
            //alert("双击退出编辑")
            var len = $(obj).find('b');
            if (len.length != 0) {


                var count = 0;
                $(obj).find('td').each(function () {
                    var old = $(this).text();
                    if (old.length != 0) {
                        $(this).html("<input style='background-color: #0c7cb5' id='input" + count + "' type='text' data-color='black' value='" + old + "'> ");
                        count++;
                    }

                })
                $(obj).blur()
            }
        }
        function initList() {
            $.ajax({
                url: '/showArticle',
                type: 'GET', //GET
                async: false,    //或false,是否异步
                timeout: 5000,    //超时时间
                dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                /* beforeSend:function(xhr){
                 console.log(xhr)
                 console.log('发送前')
                 },*/
                success: function (data, textStatus, jqXHR) {
                    var json = eval(data);
                    $.each(json, function (index, item) {
                        $('#a_content').append($("<tr class='gradeX'><td>" + data[index].articleContent + "</td><td> " +
                            "<div class='tpl-table-black-operation'> <a href='/showArticle?num=" + (index + 1) + "'> <i class='am-icon-pencil'></i> 查看 </a> <a href='/deleteArticle?articleId=" + data[index].articleId + "' class='tpl-table-black-operation-del'> <i class='am-icon-trash'></i> 删除 </a> </div> </td></tr>"))
                    });

                },
                error: function (xhr, textStatus) {
                    console.log('错误')
                    console.log(xhr)
                    console.log(textStatus)
                    //window.location.href="404.html"
                },
                complete: function () {

                }
            })
        }
        function initList1() {
            $.ajax({
                url: '/showAllUser',
                type: 'GET', //GET
                async: false,    //或false,是否异步
                timeout: 5000,    //超时时间
                dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                /* beforeSend:function(xhr){
                 console.log(xhr)
                 console.log('发送前')
                 },*/

                success: function (data, textStatus, jqXHR) {
                    var json = eval(data);
                    $('#b_content').append("  <tr class='gradeX'> <td>用户名</td> <td>用户Id</td> <td>微信</td> <td>手机</td> <td>年级</td><td>班级</td> <td>学院</td> <td>用户角色</td><td>密码</td><td>支付宝</td></tr> ")
                    $.each(json, function (index, item) {
                        $('#b_content').append($("<tr id='abc' class='gradeX' onclick='changeInput(this)' ondblclick='loseFocus(this)'><td><b>" +
                            data[index].userName + "</b></td><td><b>" +
                            data[index].userId + "</b></td><td><b>" +
                            data[index].userWechat + "</b></td><td ><b>" +
                            data[index].userTelephoneNumber + "</b></td><td><b>" +
                            data[index].userGrade + "</b></td><td><b>" +
                            data[index].userClass + "</b></td><td><b>" +
                            data[index].userInstitution + "</b></td><td><b>" +
                            data[index].userRole + "</b></td><td><b>" +
                            data[index].userPassword + "</b></td><td><b>" +
                            data[index].userAlipay + "</b></td></tr><td>" +
                            "<div class='tpl-table-black-operation'> <a href='javascript:postUpdate();' > <i class='am-icon-pencil'></i> 保存 </a> <a href='/delete?userId=" + data[index].userId + "&password=" + data[index].password + "'class='tpl-table-black-operation-del'> <i class='am-icon-trash'></i> 删除 </a> </div> </td>"))
                    });

                },
                error: function (xhr, textStatus) {
                    console.log('错误')
                    console.log(xhr)
                    console.log(textStatus)
                    //window.location.href="404.html"
                },
                complete: function () {

                }
            })
        }
        function initList12() {
            $.ajax({
                url: '/showAllFeedBack',
                type: 'GET', //GET
                async: false,    //或false,是否异步
                timeout: 5000,    //超时时间
                dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                /* beforeSend:function(xhr){
                 console.log(xhr)
                 console.log('发送前')
                 },*/

                success: function (data, textStatus, jqXHR) {
                    var json = eval(data);
                    $('#c_content').append("  <tr class='gradeX'> <td>编号</td> <td>用户Id</td> <td>标题</td> <td>内容</td> <td>时间</td>  </tr> ")
                    $.each(json, function (index, item) {
                        var str=data[index].feedbackContent;
                        $('#c_content').append($("<tr  class='gradeX'><td>" + data[index].id + "</td>" +
                            "<td>" + data[index].userId + "</td>" +
                            "<td>" + data[index].feedbackTitle + "</td>" +
                            "<td>" + data[index].feedbackContent + "</td>" +
                            "<td>" + data[index].feedbackTime + "</td><td>" +
                            "<div class='tpl-table-black-operation'> <a href='javascript:void(0);' onclick='showContent(\""+str+"\")'> <i class='am-icon-pencil'></i> 查看 </a> <a href='/delete?userId=" + data[index].userId + "&password=" + data[index].password + "'class='tpl-table-black-operation-del'> <i class='am-icon-trash'></i> 删除 </a> </div> </td></tr>"))

                    });

                },
                error: function (xhr, textStatus) {
                    console.log('错误')
                    console.log(xhr)
                    console.log(textStatus)
                    //window.location.href="404.html"
                },
                complete: function () {

                }
            })
        }
    </script>
</head>

<body data-type="index" onload="initList();initList1();initList12()">
<script src="table/js/theme.js"></script>
<div class="am-g tpl-g">
    <!-- 头部 -->
    <header>
        <!-- logo -->
        <div class="am-fl tpl-header-logo">
            <a href="javascript:;"><img src="table/img/logo.png" alt=""></a>
        </div>
        <!-- 右侧内容 -->


    </header>
    <!-- 风格切换 -->
    <div class="tpl-skiner">
        <div class="tpl-skiner-toggle am-icon-cog">
        </div>
        <div class="tpl-skiner-content">
            <div class="tpl-skiner-content-title">
                选择主题
            </div>
            <div class="tpl-skiner-content-bar">
                <span class="skiner-color skiner-white" data-color="theme-white"></span>
                <span class="skiner-color skiner-black" data-color="theme-black"></span>
            </div>
        </div>
    </div>
    <!-- 侧边导航栏 -->
    <div class="left-sidebar">
        <!-- 用户信息 -->
        <div class="tpl-sidebar-user-panel">
            <div class="tpl-user-panel-slide-toggleable">
                <div class="tpl-user-panel-profile-picture">
                    <img src="table/img/user04.png" alt="">
                </div>
                <span class="user-panel-logged-in-text">
              <i class="am-icon-circle-o am-text-success tpl-user-panel-status-icon" id="userName" onload="document.cookie."></i>
          </span>

            </div>
        </div>

        <!-- 菜单 -->
        <ul class="sidebar-nav">
            <li class="sidebar-nav-link">
                <a href="index.jsp" class="active">
                    <i class="am-icon-home sidebar-nav-link-logo"></i> 首页
                </a>
            </li>

            <li class="sidebar-nav-link">
                <a href="order.jsp" class="active">
                    <i class="am-icon-home sidebar-nav-link-logo"></i> 预定
                </a>
            </li>
        </ul>
    </div>


    <!-- 内容区域 -->
    <div class="tpl-content-wrapper">


        <div class="row am-cf">


            <div class="am-u-sm-12 am-u-md-12 am-u-lg-8 widget-margin-bottom-lg">

                <div class="widget am-cf widget-body-lg">

                    <div class="widget-body  am-fr">
                        <div class="am-scrollable-horizontal ">
                            <table width="100%" class="am-table am-table-compact am-text-nowrap tpl-table-black "
                            >
                                <thead>
                                <tr>
                                    <th>文章标题</th>

                                </tr>
                                </thead>
                                <tbody id="a_content">


                                <!-- more data -->
                                </tbody>
                            </table>
                        </div>
                        <div class="am-scrollable-horizontal ">
                            <table width="100%" class="am-table am-table-compact am-text-nowrap tpl-table-black "
                                   id="example-r">
                                <thead>
                                <tr>
                                    <th>用户列表</th>

                                </tr>
                                </thead>

                                <tbody id="b_content">


                                <!-- more data -->
                                </tbody>
                            </table>
                        </div>
                        <div class="am-scrollable-horizontal ">
                            <table width="100%" class="am-table am-table-compact am-text-nowrap tpl-table-black "
                            >
                                <thead>
                                <tr>
                                    <th>有所感反馈</th>

                                </tr>
                                </thead>
                                <tbody id="c_content">


                                <!-- more data -->
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
</div>
</div>
<script src="table/js/amazeui.min.js"></script>

<script src="table/js/app.js"></script>

</body>

</html>