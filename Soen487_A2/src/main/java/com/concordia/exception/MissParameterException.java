package com.concordia.exception;

import com.concordia.pojo.ServiceStatus;
import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM,
        customFaultCode = "{" + MissParameterException.NAMESPACE_URI + "}fault",
        faultStringOrReason = "Missing parameter...")
public class MissParameterException extends RuntimeException{
    private ServiceStatus serviceStatus;
    public static final String NAMESPACE_URI = "http://www.soen487.com/exception";

    public MissParameterException(String msg){
        super(msg);
    }

    public MissParameterException(String msg, ServiceStatus serviceStatus){
        super(msg);
        this.serviceStatus = serviceStatus;

    }
    public ServiceStatus getServiceStatus(){
        return this.serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
}
