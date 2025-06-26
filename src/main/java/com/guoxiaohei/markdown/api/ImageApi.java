package com.guoxiaohei.markdown.api;

import com.guoxiaohei.markdown.model.ImageResult;
import com.guoxiaohei.markdown.model.common.Constant;
import com.guoxiaohei.markdown.utils.IpUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.time.LocalDate;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/image")
public class ImageApi {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${server.port:8080}")
    private String port;

    @Value("${web.classpath}")
    private String rootPath;


    @PostMapping("upload")
    public ImageResult uploadImage(@RequestParam(value = "editormd-image-file") MultipartFile multipartFile, HttpServletRequest request) {
        ImageResult imageResult = null;
        try {
            String path = saveImages(request, multipartFile);
            String url = generateHttpUrl(path);
            imageResult = new ImageResult(1, "success", url);
        } catch (Exception ex) {
            logger.error("image upload failed {}", multipartFile.getOriginalFilename(), ex);
            imageResult = new ImageResult(0, ex.getMessage(), null);
        }
        return imageResult;
    }

    private String generateHttpUrl(String filepath) throws UnknownHostException {
        String ip = IpUtil.getIp();
        return Constant.HTTP + ip + Constant.PORT_SEPARATOR + port + filepath;
    }


    private String saveImages(HttpServletRequest request, MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);
        String path = request.getServletContext().getRealPath("/");
        logger.info("web context path {} ", path);
        path = rootPath;
        String current = LocalDate.now().toString();
        String webPath = File.separator + Constant.UPLOAD_FOLDER + File.separator + current;
        path = path + webPath;
        File file = new File(path);
        if (!file.exists()) {
            Files.createDirectories(file.toPath());
        }
        String filename = System.currentTimeMillis() + "." + extension;
        IOUtils.copy(multipartFile.getInputStream(), new FileOutputStream(new File(path, filename)));
        return webPath.replace("\\", "/") + Constant.WEB_SEPARATOR + filename;
    }
}
