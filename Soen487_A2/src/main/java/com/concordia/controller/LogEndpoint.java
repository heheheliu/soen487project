package com.concordia.controller;

import com.concordia.exception.MissParameterException;
import com.concordia.exception.RepException;
import com.concordia.pojo.ServiceStatus;
import com.concordia.service.impl.LogService;
import com.soen487.log_ws.ClearLogRequest;
import com.soen487.log_ws.GetChangeLogsRequest;
import com.soen487.log_ws.GetChangeLogsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class LogEndpoint {
    private static final String NAMESPACE_URI = "http://www.soen487.com/log-ws";

    @Autowired
    private LogService logService;

    @Autowired
    public LogEndpoint(LogService service){
        this.logService = service;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getChangeLogsRequest")
    @ResponsePayload
    public GetChangeLogsResponse getChangeLogs(@RequestPayload GetChangeLogsRequest request) throws MissParameterException {
        GetChangeLogsResponse response = new GetChangeLogsResponse();
        if(request.getFrom().equals("") && request.getTo().equals("") && request.getType().equals("")){
            response.setChangedLogs(logService.getAllChangeLogs());
            return  response;
        }
        if(request.getFrom().equals("") || request.getTo().equals("") || request.getType().equals("")){
           throw new MissParameterException("missing parameter error!");
        }
        response.setChangedLogs((logService.getChangeLogs(request.getFrom(), request.getTo(), request.getType())));
        return response;

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "clearLogRequest")
    @ResponsePayload
    public ClearLogRequest ClearLogRequest() throws RepException {
        ServiceStatus serviceStatus = logService.clearLogs();
        if(serviceStatus.getCode().equalsIgnoreCase("Error")){
            throw new RepException(serviceStatus.getDescription());
        }
        ClearLogRequest response = new ClearLogRequest();
        response.setResult(serviceStatus.getDescription());
        return response;

    }






}
