package com.springdemo.buryingpoint.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

/**
 *	行为埋点
 * @author Ice sun
 * @date 2020/10/29 18:01
 */
@Data
public class EventLog {

	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 用户邮箱
	 */
	private String email;
	/**
	 * 机构名称
	 */
	private String orgName;
	/**
	 * 部门名称
	 */
	private String deptName;
	/**
	 * 请求路径
	 */
	private String url;
	/**
	 * 请求 IP
	 */
	private String ip;
	/**
	 * 请求参数
	 */
	private String params;
	/**
	 * 接口用时（毫秒）
	 */
	private Long useTime;
	/**
	 * 浏览器类型
	 */
	private String browser;
	/**
	 * 操作系统类型
	 */
	private String operatingSystem;
	/**
	 * 请求响应状态，示例：200 302等
	 */
	private Integer status;
	/**
	 * 统一接口响应状态代码 1 成功 其他见码表
	 */
	private String code;
	/**
	 * 统一接口响应状态描述
	 */
	private String msg;
	/**
	 * 统一接口响应状态结果
	 */
	private String data;
	/**
	 * 记录时间
	 */
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime createTime;
}