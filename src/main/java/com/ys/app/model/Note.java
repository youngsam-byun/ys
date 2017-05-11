package com.ys.app.model;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

public class Note {

  private int id;
  private int sendId;
  private int recvId;

  @NotEmpty
  private String msg;
  private Date sendTime;
  private Date recvTime;
  private boolean read;
  private boolean sendDel;
  private boolean recvDel;


  public Note(){
    sendTime=new Date();
    read=false;
    sendDel=false;
    recvDel =false;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getSendId() {
    return sendId;
  }

  public void setSendId(int sendId) {
    this.sendId = sendId;
  }

  public int getRecvId() {
    return recvId;
  }

  public void setRecvId(int recvId) {
    this.recvId = recvId;
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

  public void setSendTime(Date sendTime) {
    this.sendTime = sendTime;
  }

  public Date getRecvTime() {
    return recvTime;
  }

  public void setRecvTime(Date recvTime) {
    this.recvTime = recvTime;
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

  public boolean isRecvDel() {
    return recvDel;
  }

  public void setRecvDel(boolean recvDel) {
    this.recvDel = recvDel;
  }
}
