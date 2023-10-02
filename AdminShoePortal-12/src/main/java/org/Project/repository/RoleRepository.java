package org.Project.repository;

import org.Project.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends  JpaRepository<Role, Long>{

	Role findByName(String string);
	//Role findeById(Long id);


}
