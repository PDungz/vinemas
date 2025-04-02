package com.example.vinemas_server.infrastructure.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Enumeration;
import java.util.stream.Collectors;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Bọc request nếu chưa được bọc
        if (!(request instanceof ContentCachingRequestWrapper)) {
            request = new ContentCachingRequestWrapper(request);
        }

        // Lấy thông tin headers, query params, và body
        String headers = getHeaders(request);
        String queryParams = getQueryParams(request);
        String requestBody = getRequestBody((ContentCachingRequestWrapper) request);

        // Ghi log request
        logger.info("Request: [{}] {} - Headers: {} - Query Params: {} - Body Params: {}",
                request.getMethod(), request.getRequestURI(), headers, queryParams, requestBody);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws IOException {
        ContentCachingRequestWrapper wrappedRequest = (request instanceof ContentCachingRequestWrapper)
                ? (ContentCachingRequestWrapper) request : null;
        ContentCachingResponseWrapper wrappedResponse = (response instanceof ContentCachingResponseWrapper)
                ? (ContentCachingResponseWrapper) response : null;

        // Lấy thông tin request và response body
        String requestBody = (wrappedRequest != null) ? getRequestBody(wrappedRequest) : "EMPTY_BODY";
        String responseBody = (wrappedResponse != null) ? getResponseBody(wrappedResponse) : "EMPTY_BODY";

        // Ghi log response
        logger.info("Response: [{}] {} - Status: {} - Request Body: {} - Response Body: {}",
                request.getMethod(), request.getRequestURI(), response.getStatus(), requestBody, responseBody);

        // Nếu có exception, ghi log lỗi
        if (ex != null) {
            logger.error("Exception: {}", ex.getMessage());
        }

        // Quan trọng: Copy lại body vào response để tránh mất dữ liệu
        if (wrappedResponse != null) {
            wrappedResponse.copyBodyToResponse();
        }
    }

    private String getHeaders(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames == null) {
            return "NO_HEADERS";
        }
        return Collections.list(headerNames).stream()
                .map(header -> header + ": " + request.getHeader(header))
                .collect(Collectors.joining(", "));
    }

    private String getQueryParams(HttpServletRequest request) {
        return request.getParameterMap().entrySet().stream()
                .map(entry -> entry.getKey() + "=" + String.join(",", entry.getValue()))
                .collect(Collectors.joining("&"));
    }

    private String getRequestBody(ContentCachingRequestWrapper request) {
        byte[] content = request.getContentAsByteArray();
        return (content.length > 0) ? new String(content, StandardCharsets.UTF_8) : "EMPTY_BODY";
    }

    private String getResponseBody(ContentCachingResponseWrapper response) {
        byte[] content = response.getContentAsByteArray();
        return (content.length > 0) ? new String(content, StandardCharsets.UTF_8) : "EMPTY_BODY";
    }
}
