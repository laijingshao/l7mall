package com.ls.l7mall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author laijs
 * @date 2020-3-17-15:28
 */
public interface FileService {
    public String upload(MultipartFile multipartFile, String path);
}
