package bitc.full502.sceneshare.config.user;

import bitc.full502.sceneshare.interceptor.LoginCheck;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheck())
                .addPathPatterns("/user/**")
                .addPathPatterns("/main/**")
                .addPathPatterns("/bookmarks/**")
                .addPathPatterns("/movieDetail/**")
                .excludePathPatterns("/user/login.do")
                .excludePathPatterns("/user/loginProcess.do")
                .excludePathPatterns("/user/loginFail.do")
                .excludePathPatterns("/user/logout.do")
                .excludePathPatterns("/user/join.do")
                .excludePathPatterns("/main")
                .excludePathPatterns("/user/create")
                .excludePathPatterns("/main/search")
                .excludePathPatterns("/user/searchResult")
                .excludePathPatterns("/main/movieListBookmarkCnt")
                .excludePathPatterns("/main/movieListReleaseDate")
                .excludePathPatterns("/main/boardList");
    }
}
