//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.03.09 at 08:23:15 AM EST 
//


package com.soen487.log_ws;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.soen487.log_ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.soen487.log_ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetChangeLogsResponse }
     * 
     */
    public GetChangeLogsResponse createGetChangeLogsResponse() {
        return new GetChangeLogsResponse();
    }

    /**
     * Create an instance of {@link LogList }
     * 
     */
    public LogList createLogList() {
        return new LogList();
    }

    /**
     * Create an instance of {@link GetChangeLogsRequest }
     * 
     */
    public GetChangeLogsRequest createGetChangeLogsRequest() {
        return new GetChangeLogsRequest();
    }

    /**
     * Create an instance of {@link ClearLogRequest }
     * 
     */
    public ClearLogRequest createClearLogRequest() {
        return new ClearLogRequest();
    }

    /**
     * Create an instance of {@link LogEntry }
     * 
     */
    public LogEntry createLogEntry() {
        return new LogEntry();
    }

}