package com.iamshashiraj.splitwise.service;

import com.iamshashiraj.splitwise.model.Group;
import com.iamshashiraj.splitwise.model.User;

import java.util.List;

public interface GroupService {
	public Group addGroup(Group group);
	public Group removeGroup(Group group);
	public List<Group> getAllGroups();
	public Group getGroupById(Integer id);
}
