package com.rectifier.future_light.service;

import com.rectifier.future_light.model.Post;
import com.rectifier.future_light.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByIdDesc();
    }

    public List<Post> getLatestPosts() {
        return postRepository.findTop5ByOrderByIdDesc();
    }

}
