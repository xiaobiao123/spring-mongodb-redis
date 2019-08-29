package com.cn.mongodb.example.data.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.alibaba.fastjson.JSON;
import com.mongodb.DBObject;
/**
 * 消息表
 * @author miaoys
 * @version 2016年8月19日下午5:31:54
 */
@Document(collection = "message")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

    private Long id;//主键

    private String msgType;//消息类型

    private String msgTheme;//消息主题

    private String msgContent;//消息内容

    private String sendTime;//发送时间

    private Integer readStatus;//读取状态:1未读 2已读

    private String sendUserNo;//发送用户编号

    private String sendUserName;//发送用户名称

    private String receiveUserNo;//接收用户编号

    private String receiveUserName;//接收用户名称

    private Integer sendDelStatus;//删除状态：1未删除 0已删除

    private String sendDelTime;//删除时间

    private Integer receiveDelStatus;//删除状态：1未删除 0已删除

    private String receiveDelTime;//删除时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    public String getMsgTheme() {
        return msgTheme;
    }

    public void setMsgTheme(String msgTheme) {
        this.msgTheme = msgTheme == null ? null : msgTheme.trim();
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent == null ? null : msgContent.trim();
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime == null ? null : sendTime.trim();
    }

    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    public String getSendUserNo() {
        return sendUserNo;
    }

    public void setSendUserNo(String sendUserNo) {
        this.sendUserNo = sendUserNo == null ? null : sendUserNo.trim();
    }

    public String getSendUserName() {
        return sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName == null ? null : sendUserName.trim();
    }

    public String getReceiveUserNo() {
        return receiveUserNo;
    }

    public void setReceiveUserNo(String receiveUserNo) {
        this.receiveUserNo = receiveUserNo == null ? null : receiveUserNo.trim();
    }

    public String getReceiveUserName() {
        return receiveUserName;
    }

    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName == null ? null : receiveUserName.trim();
    }

    public Integer getSendDelStatus() {
        return sendDelStatus;
    }

    public void setSendDelStatus(Integer sendDelStatus) {
        this.sendDelStatus = sendDelStatus;
    }

    public String getSendDelTime() {
        return sendDelTime;
    }

    public void setSendDelTime(String sendDelTime) {
        this.sendDelTime = sendDelTime == null ? null : sendDelTime.trim();
    }

    public Integer getReceiveDelStatus() {
        return receiveDelStatus;
    }

    public void setReceiveDelStatus(Integer receiveDelStatus) {
        this.receiveDelStatus = receiveDelStatus;
    }

    public String getReceiveDelTime() {
        return receiveDelTime;
    }

    public void setReceiveDelTime(String receiveDelTime) {
        this.receiveDelTime = receiveDelTime == null ? null : receiveDelTime.trim();
    }

	public void parse(DBObject next) {
		Message group2 = JSON.parseObject(JSON.toJSONString(next), Message.class);  
	}
}