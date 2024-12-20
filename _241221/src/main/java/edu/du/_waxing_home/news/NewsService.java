package edu.du._waxing_home.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    // 게시판 목록 불러오기
    public List<News> getAllNews() {
        return newsRepository.findAllByOrderByCreatedAtDesc();
    }

    // 이미지가 없는 경우
    public void saveNews(News news) {
        newsRepository.save(news);
    }

    // 이미지가 있는 경우
    public void saveNewsWithImages(News news, List<MultipartFile> images) throws IOException {
        // 이미지 저장 후 이미지 URL 목록을 반환
        List<String> imageUrls = saveImages(images);
        news.setImageUrls(imageUrls); // 이미지 URL을 뉴스 객체에 설정
        newsRepository.save(news); // 뉴스 객체와 이미지를 함께 저장
    }

    // 이미지 저장 메서드
    private List<String> saveImages(List<MultipartFile> images) throws IOException {
        List<String> imageUrls = new ArrayList<>();

        for (MultipartFile image : images) {
            // 이미지 파일 이름을 UUID로 변경하여 중복을 방지
            String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();

            // 이미지 파일을 서버 내 지정된 디렉터리에 저장
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.createDirectories(path.getParent());  // 디렉터리가 없으면 생성
            image.transferTo(path);  // 이미지 파일을 지정된 경로로 저장

            // 웹에서 접근 가능한 이미지 경로를 URL 형식으로 생성하여 리스트에 추가
            imageUrls.add("/uploads/" + fileName);
        }

        return imageUrls;
    }
}