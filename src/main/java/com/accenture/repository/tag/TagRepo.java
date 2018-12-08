package com.accenture.repository.tag;

import com.accenture.entity.tag.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepo extends CrudRepository<Tag, Long> {
}
