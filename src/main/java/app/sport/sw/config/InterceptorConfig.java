package app.sport.sw.config;

import app.sport.sw.interceptor.board.BoardExistsInterceptor;
import app.sport.sw.interceptor.board.BoardOwnerInterceptor;
import app.sport.sw.interceptor.club.ClubExistsInterceptor;
import app.sport.sw.interceptor.club.ClubJoinInterceptor;
import app.sport.sw.interceptor.club.ClubMethodInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final ClubExistsInterceptor clubExistsInterceptor;
    private final ClubMethodInterceptor clubMethodInterceptor;
    private final ClubJoinInterceptor clubJoinInterceptor;

    private final BoardExistsInterceptor boardExistsInterceptor;
    private final BoardOwnerInterceptor boardOwnerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 모임이 존재하는지 확인하는 인터셉터
        registry.addInterceptor(clubExistsInterceptor)
                .order(1)
                .addPathPatterns("/club/**")
                .excludePathPatterns("/club");

        // 클럽 조회, 생성, 수정, 삭제 메소드 인터셉터
        registry.addInterceptor(clubMethodInterceptor)
                .order(2)
                .addPathPatterns("/club/*");

        // 모임에 참가중인지 확인하는 인터셉터
        registry.addInterceptor(clubJoinInterceptor)
                .order(3)
                .addPathPatterns("/club/*/**")
                .excludePathPatterns("/club/*", "/club/*/board", "/club/*/users");


        // 게시물이 존재하는지 확인하는 인터셉터
        registry.addInterceptor(boardExistsInterceptor)
                .order(4)
                .addPathPatterns("/club/*/board/**")
                .excludePathPatterns("/club/*/board", "/club/*/board/create");

        // 게시물을 작성한 유저인지 확인하는 인터셉터 ( 그룹장, 매니저도 허용 )
        registry.addInterceptor(boardOwnerInterceptor)
                .order(5)
                .addPathPatterns("/club/*/board/*/edit", "/club/*/board/*/delete");
    }
}
