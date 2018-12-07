package com.accenture.repository.post;

import com.accenture.entity.post.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepo extends CrudRepository<Post, Long> {
}
