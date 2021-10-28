package com.iamshashiraj.splitwise.service;

import com.iamshashiraj.splitwise.model.Group;
import com.iamshashiraj.splitwise.repos.GroupCrudRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupCrudRepo crudRepo;

    @Override
    public Group addGroup(Group group) {
        return crudRepo.save(group);
    }

    @Override
    public Group removeGroup(Group group) {
        return crudRepo.removeById(group.getId());
    }

    @Override
    public List<Group> getAllGroups() {
        return crudRepo.findAll();
    }

    @Override
    public Group getGroupById(Integer id) {
        return crudRepo.findById(id).get();
    }


}
