package com.concordia.exception;

import com.concordia.pojo.ServiceStatus;
import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM,
        customFaultCode = "{" + RepException.NAMESPACE_URI + "}fault",
        faultStringOrReason = "the method is not yet supported...")

public class RepException extends RuntimeException{
    private ServiceStatus serviceStatus;

    public static final String NAMESPACE_URI = "http://www.soen487.com/exception";

    public RepException(String msg){
        super(msg);
    }

    public RepException(String msg, ServiceStatus serviceStatus){
        super(msg);
        this.serviceStatus = serviceStatus;
    }
    public ServiceStatus getServiceStatus(){
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
}
