package com.songxin.pyg.shop.controller;

import com.songxin.pyg.vo.OperateResultVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import utils.FastDFSClient;

/**
 * 文件上传控制器
 * @author fishsx
 * @date 2018/12/15 下午5:31
 */
@RestController
@RequestMapping("file")
public class UploadController {

    /** 文件服务器的url地址 */
    @Value("${FILE_SERVER_URL}")
    private String FILE_SERVER_URL;

    /**
     * 上传文件
     * @param file
     * @return com.songxin.pyg.vo.OperateResultVO
     * @author fishsx
     * @date 2018/12/15 下午5:31
     */
    @RequestMapping("upload")
    public OperateResultVO upload(MultipartFile file) {
        try {
            //获取文件源名称
            String fileName = file.getOriginalFilename();
            //获取文件拓展名
            String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
            //初始化fastDFS工具类
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fdfs_client.conf");
            //调用上传文件方法,返回相对的路径
            String relativePath = fastDFSClient.uploadFile(file.getBytes(), extName);
            //拼接绝对的路径
            String absPath = FILE_SERVER_URL + relativePath;
            return new OperateResultVO(true, "上传成功!", absPath);
        } catch (Exception e) {
            e.printStackTrace();
            return new OperateResultVO(false, "上传失败!");
        }
    }
}
