package com.accenture.repository.subdivision;

import com.accenture.entity.subdivision.SubDivision;
import org.springframework.data.repository.CrudRepository;

public interface SubDivRepo extends CrudRepository<SubDivision, Long> {
}
