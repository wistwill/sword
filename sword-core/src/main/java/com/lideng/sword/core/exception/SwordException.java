package com.lideng.sword.core.exception;

/**
 * @Author: lideng
 * @Date: 2019/7/30 19:42
 */
public class SwordException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String msg;
    private int code = 500;
    
    public SwordException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public SwordException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public SwordException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public SwordException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
