package com.github.dts.domain.dto.resourceinfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wang
 * @description ResourceInfoDTO 是一个用于封装资源信息的数据传输对象。
 * @since 2024/8/24 18:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceInfoValidateDTO {
    private String connection;
    private String host;
    private Integer port;
    private Integer resourceType;
    private String userName;
    private String passWord;
}
