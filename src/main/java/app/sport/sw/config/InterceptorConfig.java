package app.sport.sw.config;

import app.sport.sw.interceptor.board.BoardExistsInterceptor;
import app.sport.sw.interceptor.board.BoardMethodInterceptor;
import app.sport.sw.interceptor.club.ClubExistsInterceptor;
import app.sport.sw.interceptor.club.ClubJoinInterceptor;
import app.sport.sw.interceptor.club.ClubMethodInterceptor;
import app.sport.sw.interceptor.comment.CommentOwnerInterceptor;
import app.sport.sw.interceptor.meeting.MeetingExistsInterceptor;
import app.sport.sw.interceptor.meeting.MeetingMethodInterceptor;
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
    private final BoardMethodInterceptor boardMethodInterceptor;

    private final CommentOwnerInterceptor commentOwnerInterceptor;

    private final MeetingExistsInterceptor meetingExistsInterceptor;
    private final MeetingMethodInterceptor meetingMethodInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 모임이 존재하는지 확인하는 인터셉터
        registry.addInterceptor(clubExistsInterceptor)
                .order(1)
                .addPathPatterns("/club/**", "/public/club/**")
                .excludePathPatterns("/club");


        // 클럽 조회, 생성, 수정, 삭제 메소드 인터셉터
        registry.addInterceptor(clubMethodInterceptor)
                .order(2)
                .addPathPatterns("/club/*");

        // 모임에 참가중인지 확인하는 인터셉터
        registry.addInterceptor(clubJoinInterceptor)
                .order(3)
                .addPathPatterns("/club/*/**")
                .excludePathPatterns("/club/*");



        // 게시물이 존재하는지 확인하는 인터셉터
        registry.addInterceptor(boardExistsInterceptor)
                .order(4)
                .addPathPatterns("/club/*/board/**")
                .excludePathPatterns("/club/*/board", "/club/*/board/create");

        // 게시글 상세 조회, 수정, 삭제 권한 인터셉터
        registry.addInterceptor(boardMethodInterceptor)
                .order(5)
                .addPathPatterns("/club/*/board/*");



        // 댓글 수정 또는 삭제 권한 인터셉터
        registry.addInterceptor(commentOwnerInterceptor)
                .order(6)
                .addPathPatterns("/club/*/board/*/comment/**")
                .excludePathPatterns("/club/*/board/*/comment");


        // 일정이 존재하는지 확인하는 인터셉터
        registry.addInterceptor(meetingExistsInterceptor)
                .order(7)
                .addPathPatterns("/club/*/meeting/**", "/public/club/*/meeting")
                .excludePathPatterns("/club/*/meeting");

        // 일정 생성, 수정, 삭제, 나가기 권한 인터셉터
        registry.addInterceptor(meetingMethodInterceptor)
                .order(8)
                .addPathPatterns("/club/*/meeting/**");
    }
}
