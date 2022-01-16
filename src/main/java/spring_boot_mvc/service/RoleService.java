package spring_boot_mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_boot_mvc.model.Role;
import spring_boot_mvc.model.User;
import spring_boot_mvc.repository.RoleRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    RoleRepository roleRepository;


    public Role getRoleById(Long id) {
        Role role = roleRepository.findById(id).get();
        return role;
    }

    public List<Role> allRoles() {
        List<Role> rList = new ArrayList<>();
        roleRepository.findAll().forEach(rList::add);
        return rList;
    }
}
