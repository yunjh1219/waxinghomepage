package edu.du._waxing_home.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/list")
    public String getNews(Model model) {
        List<News> news = newsService.getAllNews();
        model.addAttribute("news", news);
        return "pages/community/news";
    }

    @PostMapping("/submit")
    public String submitReview(News news,
                               @RequestParam(value = "images", required = false) List<MultipartFile> images, // 파일 선택이 선택적
                               RedirectAttributes redirectAttributes) {
        try {
            // 이미지가 첨부되었다면 처리
            if (images != null && !images.isEmpty()) {
                newsService.saveNewsWithImages(news, images);  // 여러 이미지를 서비스에서 처리
            } else {
                newsService.saveNews(news);  // 이미지가 없으면 이미지 없이 저장
            }

            redirectAttributes.addFlashAttribute("message", "소식이 성공적으로 등록되었습니다.");
            return "redirect:/news/list"; // 소식 목록 페이지로 리다이렉트
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "소식 등록에 실패했습니다.");
            return "redirect:/news/write"; // 작성 페이지로 리다이렉트
        }
    }
}