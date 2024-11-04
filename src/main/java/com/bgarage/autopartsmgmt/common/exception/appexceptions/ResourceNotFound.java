package com.bgarage.autopartsmgmt.common.exception.appexceptions;

public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(String message){
        super(message);
    }
}
