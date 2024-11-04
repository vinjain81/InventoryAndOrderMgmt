package com.bgarage.autopartsmgmt.common.exception.appexceptions;

public class BadRequest extends RuntimeException {

    public BadRequest(String message){
        super(message);
    }
}
