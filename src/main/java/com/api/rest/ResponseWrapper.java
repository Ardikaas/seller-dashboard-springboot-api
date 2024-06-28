package com.api.rest;

public class ResponseWrapper<T> {
  private Status status;
  private T data;

  public ResponseWrapper(Status status, T data) {
    this.status = status;
    this.data = data;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public static class Status {
    private int code;
    private String message;

    public Status(int code, String message) {
      this.code = code;
      this.message = message;
    }

    public int getCode() {
      return code;
    }

    public void setCode(int code) {
      this.code = code;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }
  }
}
