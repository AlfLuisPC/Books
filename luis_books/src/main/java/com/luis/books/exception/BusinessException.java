package com.luis.books.exception;


import org.apache.commons.lang3.StringUtils;

public class BusinessException extends RuntimeException {


	private static final long serialVersionUID = 1L;
	
	private Integer codigoError;
	
	private ErrorInfo errorInfo = new ErrorInfo();
	
	public static BusinessException error(ErrorInfo errorInfo) {
		
		if(errorInfo == null) {
			throw new IllegalArgumentException("ErrorInfo is null");
		} else {
			if(StringUtils.isEmpty(errorInfo.getReferencia())) {
				throw new IllegalArgumentException("ErrorInfo.Referencia is empty");
			}
			if(StringUtils.isEmpty(errorInfo.getMensaje())) {
				throw new IllegalArgumentException("ErrorInfo.Mensaje is empty");
			}
		}
		BusinessException businessException = new BusinessException(errorInfo.getReferencia(), errorInfo.getMensaje());
		
		return businessException;
	}
	
	public BusinessException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String referencia, String mensaje) {
		super(mensaje);
		errorInfo.setReferencia(referencia);
		errorInfo.setMensaje(mensaje);
	}
	
	public BusinessException(String mensaje) {
		super(mensaje);
		errorInfo.setReferencia("Error");
		errorInfo.setMensaje(mensaje);
	}

	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}

	public Integer getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(Integer codigoError) {
		this.codigoError = codigoError;
	}
}
