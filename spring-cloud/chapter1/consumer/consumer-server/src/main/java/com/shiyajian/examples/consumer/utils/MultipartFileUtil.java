package com.shiyajian.examples.consumer.utils;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shiyajian
 * create: 2018-11-25
 */
public class MultipartFileUtil {

    private MultipartFileUtil() {
        //no instance
    }

    /**
     * 将file转换成MultipartFile
     *
     * @param source    源文件
     * @param fieldName field字段名称,default : file
     * @return
     */
    public static MultipartFile from(@NotNull File source, @Nullable String fieldName) {

        if (source == null) {
            return null;
        }

        fieldName = fieldName == null ? "file" : fieldName;

        DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem(fieldName,
                MediaType.TEXT_PLAIN_VALUE, true, source.getName());

        try (InputStream input = new FileInputStream(source);
             OutputStream os = fileItem.getOutputStream()) {
            IOUtils.copy(input, os);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file: " + e, e);
        }
        return new CommonsMultipartFile(fileItem);
    }

    public static MultipartFile[] from(File[] source, String fieldName) {
        if (source.length == 0) {
            return new MultipartFile[0];
        }

        fieldName = fieldName == null ? "file" : fieldName;
        List<MultipartFile> multipartFiles = new ArrayList<>();
        for (File file : source) {
            DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem(fieldName,
                    MediaType.TEXT_PLAIN_VALUE, true, file.getName());
            try (InputStream input = new FileInputStream(file);
                 OutputStream os = fileItem.getOutputStream()) {
                IOUtils.copy(input, os);
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid file: " + e, e);
            }
            multipartFiles.add(new CommonsMultipartFile(fileItem));
        }
        MultipartFile[] multipartFileArr = new MultipartFile[multipartFiles.size()];
        return multipartFiles.toArray(multipartFileArr);
    }
}
