package com.retaiontest.relationtest.controller;
import com.retaiontest.relationtest.EXception.DataInvalid;
import com.retaiontest.relationtest.Enumiration.FILIERE;
import com.retaiontest.relationtest.Post;
import com.retaiontest.relationtest.servic.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/register")
    public ResponseEntity<Post> registerPostWithUserAuthentifie(@RequestBody Post post) {
        try {
            Post registeredPost = postService.registerPostWithUserAuthentifie(post);
            return new ResponseEntity<>(registeredPost, HttpStatus.CREATED);
        } catch (DataInvalid e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Post>> getPostById(@PathVariable Long id) {
        try {
            Optional<Post> post = postService.afficherpostById(id);
            return new ResponseEntity<>(post, HttpStatus.OK);
        } catch (DataInvalid e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/filiere/{filiere}")
//    public ResponseEntity<Optional<Post>> getPostByFiliere(@PathVariable FILIERE filiere) {
//        try {
//            Optional<Post> post = postService.afficherPostByFiliere(filiere);
//            return new ResponseEntity<>(post, HttpStatus.OK);
//        } catch (DataInvalid e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
@GetMapping("/filiere/{filiere}")
public ResponseEntity<List<Post>> getPostByFiliere(@PathVariable FILIERE filiere) {
    try {
        List<Post> posts = postService.afficherPostByFiliere(filiere);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    } catch (DataInvalid e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

    @GetMapping("/titre/{titre}")
    public ResponseEntity<List<Post>> getPostByTitre(@PathVariable String titre) {
        try {
            List<Post> posts = postService.afficherPostByTitre(titre);
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (DataInvalid e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/titre/{titre}")
//    public ResponseEntity<Optional<Post>> getPostByTitre(@PathVariable String titre) {
//        try {
//            Optional<Post> post = postService.afficherPostByTitre(titre);
//            return new ResponseEntity<>(post, HttpStatus.OK);
//        } catch (DataInvalid e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        try {
            postService.deletePostById(id);
            return new ResponseEntity<>("Post supprimé avec succès", HttpStatus.OK);
        } catch (DataInvalid e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        try {
            Post result = postService.updatePost(id, updatedPost);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (DataInvalid e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
