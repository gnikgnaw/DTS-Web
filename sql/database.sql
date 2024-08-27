-- auto-generated definition
create table task_info
(
    id             bigint auto_increment comment '主键id'
        primary key,
    task_name      varchar(64)       not null comment '任务名称',
    batch_task_id  bigint default -1 not null comment '批任务表主键',
    stream_task_id bigint default -1 not null comment '流任务表主键',
    task_type      int    default -1 not null comment '任务类型',
    source_type    int    default -1 not null comment '数据来源类型',
    source_id      bigint            not null comment '数据源表主键id',
    sink_type      bigint default -1 not null comment '数据去向类型',
    sink_id        bigint default -1 not null comment '数据去向主键',
    extra_conf     longtext null comment '扩展配置信息',
    ctime          date              not null comment '创建时间',
    mtime          date              not null comment '修改时间'
) comment '数据集成任务配置表';


-- auto-generated definition
create table resource_info
(
    id            bigint auto_increment comment '主键id'
        primary key,
    name          varchar(64) null comment '数据源名称',
    resource_type int      not null comment '数据源类型',
    connection    longtext not null comment '资源连接信息',
    port          int null comment '端口号-可选项',
    user_name     varchar(64) null comment '用户名',
    pass_word     varchar(64) null comment '密码',
    extra_conf    longtext null comment '扩展信息'
) comment '数据集成资源信息表';

