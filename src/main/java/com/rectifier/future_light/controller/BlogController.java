package com.rectifier.future_light.controller;

import com.rectifier.future_light.model.Post;
import com.rectifier.future_light.service.FileStorageService;
import com.rectifier.future_light.service.PostService;
import com.rectifier.future_light.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BlogController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/blog")
    public String blog(Model m) {
        m.addAttribute("posts", postService.getAllPosts());
        return "blog";
    }

    @GetMapping("/blog/add")
    public String addPost() {
        return "addpost";
    }

    @PostMapping("/blog/add")
    public String addPost(@RequestParam("title") String title,
                          @RequestParam("content") String content,
                          @RequestParam("bannerImage") MultipartFile bannerImage) {

        try {
            // Store the banner image and get the file path
            String imagePath = fileStorageService.storeFile(bannerImage);

            // Create a new Post object
            Post post = new Post();
            post.setTitle(title);
            post.setContent(content);
            post.setBannerImagePath(imagePath);
            post.setAuthor(userService.getCurrentUser());

            // Save the post to the database
            postService.createPost(post);

            // Add a success flash message
            return "redirect:/blog/add?added";
        } catch (Exception e) {
            // Add error flash message

            return "redirect:/blog/add?error";
        }

    }

}
