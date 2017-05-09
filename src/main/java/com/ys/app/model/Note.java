package com.ys.app.model;

import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Timestamp;
import java.util.Date;

public class Note {

  private int id;
  private int senderId;
  private int receiverId;

  @NotEmpty
  private String msg;
  private Date sendTime;
  private Date receiveTime;
  private boolean read;
  private boolean sendDel;
  private boolean receiveDel;


  public Note(){
    sendTime=new Date();
    read=false;
    sendDel=false;
    receiveDel=false;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getSenderId() {
    return senderId;
  }

  public void setSenderId(int senderId) {
    this.senderId = senderId;
  }

  public int getReceiverId() {
    return receiverId;
  }

  public void setReceiverId(int receiverId) {
    this.receiverId = receiverId;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Date getSendTime() {
    return sendTime;
  }

  public void setSendTime(Timestamp sendTime) {
    this.sendTime = sendTime;
  }

  public Date getReceiveTime() {
    return receiveTime;
  }

  public void setReceiveTime(Timestamp receiveTime) {
    this.receiveTime = receiveTime;
  }

  public boolean isRead() {
    return read;
  }

  public void setRead(boolean read) {
    this.read = read;
  }

  public boolean isSendDel() {
    return sendDel;
  }

  public void setSendDel(boolean sendDel) {
    this.sendDel = sendDel;
  }

  public boolean isReceiveDel() {
    return receiveDel;
  }

  public void setReceiveDel(boolean receiveDel) {
    this.receiveDel = receiveDel;
  }
}
