package app.sport.sw.config;

import app.sport.sw.interceptor.ClubExistsInterceptor;
import app.sport.sw.interceptor.ClubJoinInterceptor;
import app.sport.sw.interceptor.ClubOwnerInterceptor;
import app.sport.sw.jparepository.JpaClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final ClubExistsInterceptor clubExistsInterceptor;
    private final ClubJoinInterceptor clubJoinInterceptor;
    private final ClubOwnerInterceptor clubOwnerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 모임이 존재하는지 확인하는 인터셉터
        registry.addInterceptor(clubExistsInterceptor)
                .order(1)
                .addPathPatterns("/club/**")
                .excludePathPatterns("/club/create");

        // 모임에 참가중인지 확인하는 인터셉터
        registry.addInterceptor(clubJoinInterceptor)
            .order(2)
            .addPathPatterns("/club/**")
            .excludePathPatterns(
                "/club/*", "/club/*/join", "/club/*/board", "/club/*/edit", "/club/*/delete"
            );

        // 모임장인지 확인하는 인터셉터
        registry.addInterceptor(clubOwnerInterceptor)
            .order(3)
            .addPathPatterns("/club/*/edit", "/club/*/delete");
    }
}
