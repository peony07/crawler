
CREATE TABLE
    CRA_CHOUTI
    (
        ID CHAR(32) NOT NULL COMMENT '主键',
        COME_FROM CHAR(32) COMMENT '来源',
        TITLE VARCHAR(255) COMMENT '标题',
        SUMMARY VARCHAR(255) COMMENT '摘要',
        CONTENT text COMMENT '内容',
        EXT_URL VARCHAR(2000) COMMENT '链接',
        PUBLISH_TIME VARCHAR(100) COMMENT '发布时间',
        HEART INT COMMENT '点赞数',
        COMMENT INT COMMENT '评论数',
        CREATE_TIME DATETIME COMMENT '创建时间',
        PRIMARY KEY (ID)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';
    
    CREATE TABLE
    SYS_LOG
    (
        ID CHAR(32) NOT NULL COMMENT '编号',
        TYPE CHAR(1) DEFAULT '1' COMMENT '日志类型',
        TITLE VARCHAR(255) COMMENT '日志标题',
        CREATE_ID CHAR(32) COMMENT '创建者',
        CREATE_TIME DATETIME COMMENT '创建时间',
        REMOTE_ADDR VARCHAR(255) COMMENT '操作IP地址',
        USER_AGENT VARCHAR(255) COMMENT '用户代理',
        REQUEST_URI VARCHAR(255) COMMENT '请求URI',
        METHOD VARCHAR(5) COMMENT '操作方式',
        PARAMS text COMMENT '操作提交的数据',
        EXCEPTION text COMMENT '异常信息',
        APP_ID VARCHAR(32) COMMENT '应用编号',
        DEVICE_TYPE CHAR(2) COMMENT '终端类型',
        DEVICE_MODEL VARCHAR(100) COMMENT '终端厂商',
        REMOTE_IP CHAR(18) COMMENT '终端的IP地址',
        SYSTEM_VERSION VARCHAR(32) COMMENT '终端系统版本号',
        MAC_ADDRESS VARCHAR(32) COMMENT 'MAC地址',
        IMEI VARCHAR(100) COMMENT 'IEMI(国际移动设备标识)',
        RESP_TIME INT COMMENT '响应时间',
        LOCATION VARCHAR(64) COMMENT '位置',
        NETTYPE VARCHAR(64) COMMENT '网络类型',
        DEVOPERATORS VARCHAR(32) COMMENT '运营商',
        SESSION_ID VARCHAR(64) COMMENT 'SESSION_ID',
        COOKIE_ID VARCHAR(64) COMMENT 'COOKIE_ID',
        APP_USER_ID VARCHAR(32) COMMENT 'app用户id',
        EQUIPMENT_MODEL VARCHAR(100) COMMENT '终端型号',
        APP_VERSION VARCHAR(50) COMMENT 'app版本号',
        PRIMARY KEY (ID),
        INDEX SYS_LOG_CREATE_ID (CREATE_ID),
        INDEX SYS_LOG_REQUEST_URI (REQUEST_URI),
        INDEX SYS_LOG_TYPE (TYPE),
        INDEX SYS_LOG_CREATE_TIME (CREATE_TIME)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';
    

CREATE TABLE
    LAGOU_JOB_CATEGORY
    (
        ID CHAR(32) NOT NULL COMMENT '主键',
        LARGE_TYPE_NAME VARCHAR(50) DEFAULT NULL COMMENT '大类描述',
        MIDDLE_TYPE_ID VARCHAR(50) DEFAULT NULL COMMENT '中类ID',
        MIDDLE_TYPE_NAME VARCHAR(50) DEFAULT NULL COMMENT '中类描述',
        MIDDLE_TYPE_ENNAME VARCHAR(50) DEFAULT NULL COMMENT '中类key',
        SMALL_TYPE_ID VARCHAR(50) DEFAULT NULL COMMENT '小类',
        SMALL_TYPE_NAME VARCHAR(50) DEFAULT NULL COMMENT '小类描述',
        SMALL_TYPE_ENNAME VARCHAR(50) DEFAULT NULL COMMENT '小类key',
        SEARCH_URL VARCHAR(1000) DEFAULT NULL COMMENT '链接',
        CREATE_ID CHAR(32) DEFAULT NULL COMMENT '创建者',
        CREATE_TIME DATETIME DEFAULT NULL  COMMENT '创建时间',
        UPDATE_ID CHAR(32) DEFAULT NULL  COMMENT '更新者',
        UPDATE_TIME DATETIME  DEFAULT NULL COMMENT '更新时间',
        PRIMARY KEY (ID)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='拉勾网工作分类表';


CREATE TABLE
    LAGOU_JOB_COMPANY
    (
        ID CHAR(32) NOT NULL COMMENT '主键',
        COMPANY_SHORT_NAME VARCHAR(50) DEFAULT NULL COMMENT '公司简称',
        COMPANY_FULL_NAME VARCHAR(50) DEFAULT NULL COMMENT '公司全称',
        COMPANY_CODE VARCHAR(50) DEFAULT NULL COMMENT '公司代码',
        JOB_NUM INT(11) DEFAULT 0 COMMENT '招聘职位数',
        PROMPT_TREATMENT_RATE decimal(18,6) DEFAULT NULL COMMENT '简历及时处理率',
        TREATMENT_DAYS INT(11) DEFAULT 0 COMMENT '简历处理用时',
        INTERVIEW_EVALUATION_NUM int(11) DEFAULT 0 COMMENT '面试评价',
        LAST_LOGIN VARCHAR(50) DEFAULT NULL COMMENT '最近登录',
        FINANCING VARCHAR(1000) DEFAULT NULL COMMENT '融资级别',
        COMPANY_SIZE VARCHAR(50) DEFAULT NULL COMMENT '公司规模',
        CITY_NAME VARCHAR(50) DEFAULT NULL COMMENT '所在城市',
        ADDRESS VARCHAR(5000) DEFAULT NULL COMMENT '位置',
        INTRODUCTION TEXT DEFAULT NULL COMMENT '公司介绍',
        HOME_PAGE VARCHAR(500) DEFAULT NULL COMMENT '公司主页',
        LOGO VARCHAR(500) DEFAULT NULL COMMENT '公司logo',
        SLOGAN VARCHAR(500) DEFAULT NULL COMMENT '口号',
        MAIN_BUSSINESS VARCHAR(500) DEFAULT NULL COMMENT '主营业务',
        CREATE_ID CHAR(32) DEFAULT NULL COMMENT '创建者',
        CREATE_TIME DATETIME DEFAULT NULL  COMMENT '创建时间',
        UPDATE_ID CHAR(32) DEFAULT NULL COMMENT '更新者',
        UPDATE_TIME DATETIME DEFAULT NULL COMMENT '更新时间',
        PRIMARY KEY (ID)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='拉勾网公司信息表';
    
    
    CREATE TABLE
    LAGOU_TAGS
    (
        ID CHAR(32) NOT NULL COMMENT '主键',
        BUSINESS_ID VARCHAR(50) DEFAULT NULL COMMENT '外键',
        TAG_CATEGORY VARCHAR(50) DEFAULT NULL COMMENT '标签别类',
        TAG_DESCRIBE VARCHAR(150) DEFAULT NULL COMMENT '标签值',
        CREATE_ID CHAR(32) DEFAULT NULL COMMENT '创建者',
        CREATE_TIME DATETIME DEFAULT NULL  COMMENT '创建时间',
        UPDATE_ID CHAR(32) DEFAULT NULL COMMENT '更新者',
        UPDATE_TIME DATETIME DEFAULT NULL COMMENT '更新时间',
        PRIMARY KEY (ID)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='拉勾网标签信息表';
    
    
CREATE TABLE
    LAGOU_JOB_INFO
    (
        ID CHAR(32) NOT NULL COMMENT '主键',
        JOB_CODE VARCHAR(50) DEFAULT NULL COMMENT '编号',
        JOB_CATEGORY_ID VARCHAR(50) DEFAULT NULL COMMENT '所属类别id',
        JOB_COMPANY_ID VARCHAR(50) DEFAULT NULL COMMENT '所属公司id',
        TITLE VARCHAR(50) DEFAULT NULL COMMENT '标题',
        MONTHLY_PAY VARCHAR(50) DEFAULT NULL COMMENT '月薪',
        CITY_NAME VARCHAR(50) DEFAULT NULL COMMENT '所在城市',
        SENIORITY VARCHAR(50) DEFAULT NULL COMMENT '工作年限',
        EDUCATION VARCHAR(50) DEFAULT NULL COMMENT '学历',
        NATURE VARCHAR(1000) DEFAULT NULL COMMENT '全职/兼职',
        PUBLISH_TIME VARCHAR(50) DEFAULT NULL  COMMENT '发布时间',
        OFFICE_ADDRESS VARCHAR(500) DEFAULT NULL COMMENT '工作地址',
        ADVANTAGE VARCHAR(500) DEFAULT NULL COMMENT '得益',
        CREATE_ID CHAR(32) DEFAULT NULL COMMENT '创建者',
        CREATE_TIME DATETIME DEFAULT NULL  COMMENT '创建时间',
        UPDATE_ID CHAR(32) DEFAULT NULL COMMENT '更新者',
        UPDATE_TIME DATETIME DEFAULT NULL COMMENT '更新时间',
        PRIMARY KEY (ID)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='拉勾网职位信息表';
    
    
    CREATE INDEX LAGOU_JOB_INFO_N1 ON   LAGOU_JOB_INFO(JOB_CATEGORY_ID,JOB_COMPANY_ID);
    
    CREATE OR REPLACE VIEW LAGOU_JOB_INFO_V as 
        SELECT  C.LARGE_TYPE_NAME,C.MIDDLE_TYPE_NAME,C.SMALL_TYPE_NAME,
        B.COMPANY_SHORT_NAME,B.FINANCING,B.COMPANY_SIZE,B.MAIN_BUSSINESS,
        A.TITLE,
        A.MONTHLY_PAY,
        A.CITY_NAME,
        A.SENIORITY,
        A.EDUCATION,
        A.NATURE,
        A.PUBLISH_TIME,
        A.OFFICE_ADDRESS,
        A.ADVANTAGE
          FROM LAGOU_JOB_INFO A , LAGOU_JOB_COMPANY B , LAGOU_JOB_CATEGORY C 
         WHERE A.JOB_CATEGORY_ID=C.ID AND A.JOB_COMPANY_ID=B.ID 
        ;
         
         