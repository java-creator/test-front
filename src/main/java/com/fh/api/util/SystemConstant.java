package com.fh.api.util;

public class SystemConstant {
    public static final  String TEMPLATE_PATH="/template";
    public static final  String EXCEL_FILE_PATH="D:/outFile/";
    public static final  String EXCEL_TIMPATE_FILE_PATH="bbb.xml";
    public static final  String WORD_TIMPATE_FILE_PATH="word-template.xml";
    public static final  String PDF_TIMPATE_FILE_PATH="pdf-template.html";
    public static final  String UPLOAD_FILE_PATH="upload";
    public static final  int LOGGIN_USERNAME_ERROR=1;
    public static final  int LOGGIN_PASSWORD_ERROR=2;
    public static final  int LOGGIN_SUCCESS=3;
    public static final  String LOGGIN_CURRENT_USER="user:";
    public static final  String COOKIE_KEY="login";
    public static final  int COOKIE_OUT_TIME=7*24*60*60;//记住一周
    public static final  int COOKIE_OUT_TIME_DEFAULT=30*60;//cookie默认有效时间
    public static final  int LOG_ERROR=0;
    public static final  int LOG_SUCCESS=1;
    public static final  String LOGIN_PAGE="/login.jsp";
    public static final  String NO_PREMISSION_PAGE="/noPremission.jsp";
    public static final  String AJAX_SESSION_OUT="timeOut";
    public static final  int ADD_PRODUCT_LIST_SIZE =10;

    public static final String  CURRENT_RESOURCELIST="current_resource:";//当前用户所拥有的资源 key
    public static final String  ALL_RESOURCELIST="all_resource:";//所有的资源 key
    public static final String  CODE="code:";//所有的资源 key
    public static final int  CODE_OUT_TIME=5*60;//手机验证码过期时间
    public static final int  JWT_OUT_TIME=24*60*60*1000;//jwt过期时间


    public static final String REDIS_KEY_CATEGORY_LIST = "categoryList";
    public static final String REDIS_KEY_CART = "CartServiceImplTest";

    public static final String SECRET_KEY = "WAN@SHIXIANG";
    public static final String TOKEN_IS_NULL = "token为空";
    public static final String TOKEN_CHECK_ERROR = "token解析失败";
    public static final String SESSION_MEMBER = "member";
    public static final int ORDER_STATUS_WAIT = 1;//订单支付状态 待支付
    public static final int ORDER_STATUS_SUCCESS = 2;//订单支付状态 成功

    public static final int FAT_STATUS_WAIT = 1;//订单支付状态 成功
    public static final int FAT_STATUS_SUCCESS = 2;//订单支付状态 成功




    public static final String FAT_KEY = "pay:";
    public static final String FAT_TIME_OUT = "支付超时";




}
