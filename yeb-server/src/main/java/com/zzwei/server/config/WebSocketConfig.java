package com.zzwei.server.config;

import com.zzwei.server.config.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /**
         * 1.将/ws/ep注册为stomp端点，用户连接了这个端点就可以进行websocket通讯，支持socketJS 客户端--》服务端
         * 2.setAllowedOrigins("*") 允许跨域
         * 3.withSockJS()支持socketJS访问
         */
        registry.addEndpoint("/ws/ep").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(new ChannelInterceptor() {
//            @Override
//            public Message<?> preSend(Message<?> message, MessageChannel channel) {
//                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
//                //判断是否是链接，如果是，需要获取token 并且设置用户对象
//                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
//                    String token = accessor.getFirstNativeHeader("Auth-Token");
//                    if (!StringUtils.isEmpty(token)) {
//                        String authToken = token.substring(tokenHead.length());
//                    }
//                }
//            }
//        });
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //配置代理域，支持配置多个，配置代理目的地前缀为/queue，可以在配置域上向客户端推送消息 服务端--》客户端
        registry.enableSimpleBroker("/queue");
    }
}
