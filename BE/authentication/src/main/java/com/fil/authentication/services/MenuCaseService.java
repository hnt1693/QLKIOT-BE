package com.fil.authentication.services;

import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.models.MenuCase;

public interface MenuCaseService extends InterfaceService<MenuCase> {

    ResponseAPI createWith(MenuCase menuCase, String clientId) throws Exception;

    ResponseAPI putWith(MenuCase menuCase, boolean modifiedChild) throws Exception;

}
