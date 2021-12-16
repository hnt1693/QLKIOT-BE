package com.fil.authentication.services;

import com.fil.authentication.commons.ResponseAPI;
import com.fil.authentication.models.Group;

public interface GroupService extends InterfaceService<Group> {
    ResponseAPI createGroupBySuperAdmin(Group postData);
}
