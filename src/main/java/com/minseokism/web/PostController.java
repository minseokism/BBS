package com.minseokism.web;

import com.minseokism.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.minseokism.service.PostService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("posts")
public class PostController {
	@Autowired
	PostService postService;

	@RequestMapping(method = RequestMethod.GET)
	String list(){
		return "";
	}

	@RequestMapping("board")
	String board1() {
		return "posts/board";
	}

	@RequestMapping("writeForm")
	String writeForm() {
		return "posts/writeForm";
	}

	@RequestMapping("write")
	String write(Post post) {
		System.out.println(post.getContent() + "   " + post.getSubject() + post.getSubject());
		return "/";
	}
}
