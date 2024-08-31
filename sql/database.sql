-- auto-generated definition
create table task_info
(
    id                   bigint auto_increment comment '主键id'
        primary key,
    task_name            varchar(64)                        not null comment '任务名称',
    task_type            int      default -1                not null comment '任务类型',
    source_type          int                                null comment '数据来源类型',
    source_ids           varchar(1024)                      null comment '数据来源资源id',
    sink_type            int                                null comment '数据去向类型',
    sink_ids             varchar(1024)                      null comment '数据去向资源id',
    task_info_extra_conf longtext                           null comment '任务表配置信息',
    ctime                datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    mtime                datetime default CURRENT_TIMESTAMP not null comment '修改时间'
)
    comment '数据集成任务配置表';

create index task_info_task_name_index
    on task_info (task_name);



-- auto-generated definition
create table resource_info
(
    id             bigint auto_increment comment '主键id'
        primary key,
    resource_name  varchar(64)   null comment '数据源名称',
    resource_type  int           not null comment '数据源类型',
    connection     longtext      not null comment '资源连接信息',
    host           varchar(1024) null,
    port           int           null comment '端口号-可选项',
    catalog_name   varchar(32)   null comment '湖仓场景',
    database_name  varchar(64)   null comment '数据库名',
    table_name     varchar(64)   null comment '表名、topic',
    partition_name varchar(64)   null comment '分区字段',
    storage_path   varchar(1024) null comment '存储路径',
    cluster_name   varchar(1024) null comment '集群名称',
    user_name      varchar(64)   null comment '用户名',
    pass_word      varchar(64)   null comment '密码',
    extra_conf     longtext      null comment '扩展信息'
)
    comment '数据集成资源信息表';

create index resource_info_resource_name_index
    on resource_info (resource_name);
