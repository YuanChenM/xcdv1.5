<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8" />
    <title>配置管理服务器</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="配置管理服务器" name="description" />
    <meta content="" name="author" />
    <link href="static/css/demo.css" rel="stylesheet" type="text/css"/>
    <link href="static/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
    <!-- upload css -->
    <link rel="Stylesheet" href="static/css/uploadify.css" />
    <link rel="Stylesheet" href="static/css/upload.css" />
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="static/media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="static/media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
    <link href="static/media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="static/media/css/style-metro.css" rel="stylesheet" type="text/css"/>
    <link href="static/media/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="static/media/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="static/media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="static/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="static/media/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>
    <link href="static/media/css/daterangepicker.css" rel="stylesheet" type="text/css" />
    <link href="static/media/css/fullcalendar.css" rel="stylesheet" type="text/css"/>
    <link href="static/media/css/jqvmap.css" rel="stylesheet" type="text/css" static/media="screen"/>
    <link href="static/media/css/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" static/media="screen"/>
    <!-- END PAGE LEVEL STYLES -->
    <link rel="shortcut icon" href="static/media/image/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="static/media/css/select2_metro.css" />
    <link rel="stylesheet" href="static/media/css/DT_bootstrap.css" />
</head>
<body class="page-header-fixed">
<div class="header navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="brand" href="#">
                配置管理服务器
            </a>
        </div>
    </div>
</div>
<div class="page-container">

    <#include "menu.ftl" />

    <!-- END SIDEBAR -->
    <!-- BEGIN PAGE -->
    <div class="page-content" id="mainContext">
        <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
        <div id="portlet-config" class="modal hide">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"></button>
                <h3>Widget Settings</h3>
            </div>
            <div class="modal-body">
                Widget settings form goes here
            </div>
        </div>
        <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
        <!-- BEGIN PAGE CONTAINER-->
        <div class="container-fluid">
            <!-- BEGIN PAGE HEADER-->
            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                    <h3 class="page-title">
                        首页 <small>基础信息</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="#">首页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <div id="dashboard">
                <!-- BEGIN DASHBOARD STATS -->
                <div class="row-fluid">
                    <div class="span3 responsive" data-tablet="span6" data-desktop="span3">
                        <div class="dashboard-stat blue">
                            <div class="visual">
                                <i class="icon-comments"></i>
                            </div>
                            <div class="details">
                                <div class="number">
                                    1349
                                </div>
                                <div class="desc">
                                    Properties数量
                                </div>
                            </div>
                            <a class="more" href="#">
                               查看 <i class="m-icon-swapright m-icon-white"></i>
                            </a>
                        </div>
                    </div>
                    <div class="span3 responsive" data-tablet="span6" data-desktop="span3">
                        <div class="dashboard-stat green">
                            <div class="visual">
                                <i class="icon-shopping-cart"></i>
                            </div>
                            <div class="details">
                                <div class="number">549</div>
                                <div class="desc">CodeMaster数量</div>
                            </div>
                            <a class="more" href="#">
                                查看 <i></i>
                            </a>
                        </div>
                    </div>
                    <div class="span3 responsive" data-tablet="span6  fix-offset" data-desktop="span3">
                        <div class="dashboard-stat purple">
                            <div class="visual">
                                <i class="icon-globe"></i>
                            </div>
                            <div class="details">
                                <div class="number">+89%</div>
                                <div class="desc">Config Constant数量</div>
                            </div>
                            <a class="more" href="#">
                                查看 <i class="m-icon-swapright m-icon-white"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- END PAGE CONTAINER-->
    </div>
    <!-- END PAGE -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<div class="footer" >
    <div class="footer-inner">
       Copyright © 2016 Xian Chi Da Group. All Rights Reserved.
       网站备案/许可证号：沪ICP备14048034号-1
    </div>
    <div class="footer-tools">
			<span class="go-top">
			<i class="icon-angle-up"></i>
			</span>
    </div>
</div>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="static/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="static/js/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="static/js/jquery.uploadify.js"></script>
<script type="text/javascript" src="static/js/swfobject.js"></script>


<script src="static/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="static/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
<script src="static/media/js/bootstrap.min.js" type="text/javascript"></script>

<script src="static/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="static/media/js/jquery.blockui.min.js" type="text/javascript"></script>
<script src="static/media/js/jquery.cookie.min.js" type="text/javascript"></script>
<script src="static/media/js/jquery.uniform.min.js" type="text/javascript" ></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="static/media/js/index.js" type="text/javascript"></script>
<script src="static/js/core.js"/>
<!--datatable-->
<script type="text/javascript" src="static/media/js/select2.min.js"></script>
<script type="text/javascript" src="static/media/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="static/media/js/DT_bootstrap.js"></script>
<script src="static/media/js/app.js"></script>
<script src="static/media/js/table-editable.js"></script>

<!-- END JAVASCRIPTS -->
<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script></body>
<!-- END BODY -->
</html>