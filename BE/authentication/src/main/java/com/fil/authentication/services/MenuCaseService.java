package com.fil.authentication.services;

import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.models.MenuCase;
import com.sun.org.apache.regexp.internal.RE;

public interface MenuCaseService extends InterfaceService<MenuCase> {

    ResponseAPI createWith(MenuCase menuCase, String clientId) throws Exception;

    ResponseAPI putWith(MenuCase menuCase, boolean modifiedChild) throws Exception;

    ResponseAPI getChildByParentId(Long id) throws Exception;

}
