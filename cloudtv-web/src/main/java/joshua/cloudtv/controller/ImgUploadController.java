package joshua.cloudtv.controller;

import joshua.cloudtv.constant.RetCode;
import joshua.cloudtv.utils.DateUtil;
import joshua.cloudtv.vo.response.BaseResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.UUID;

/**
 * 该控制器没有服务层
 * 后续的操作指南为：
 * 1、上传的图片是临时的，可能最终没有保留，应该有一张表保存该路径
 * 2、成功提交的图片在该临时表删除相应的路径记录
 * 3、定时扫描临时表的路径，将临时图片删除
 */
//FIXME
@RestController
@RequestMapping(value = "/upload")
public class ImgUploadController {

    // 图片上传路径响应
    class ImgUploadResponse extends BaseResponse{
        String urlPath;
        public String getUrlPath() {
            return urlPath;
        }
        public void setUrlPath(String urlPath) {
            this.urlPath = urlPath;
        }
    }

    @RequestMapping(value = "/img", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String UploadImg(@RequestParam("file") MultipartFile multipartFile, HttpSession httpSession) {

        ImgUploadResponse response = new ImgUploadResponse();

        System.out.print(multipartFile.getOriginalFilename());
        String imgType = multipartFile.getOriginalFilename().split("\\.")[1];
        String fileName = new StringBuilder( UUID.randomUUID().toString() ).append(".").append(imgType).toString();
        String path = new StringBuilder("/upload/")
                .append(DateUtil.dateToString(new Date()).replace('-', '/'))
                .toString();

        String realPath = httpSession.getServletContext().getRealPath("/WEB-INF"+path);
        System.out.println(realPath);
        File targetFile = new File(realPath, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        //保存
        try {
            multipartFile.transferTo(targetFile);
            response.setRetCode(RetCode.SUCCESS.retCode);
            response.setMessage("图片上传成功！");
            response.setUrlPath(path+"/"+fileName);
        } catch (Exception e) {
            response.setRetCode(RetCode.SERVER_ERROR.retCode);
            response.setMessage("图片上传服务器异常！");
            e.printStackTrace();
        }
        response.printJson();
        return response.toJson();
    }

    @RequestMapping(value = "/roomImg", method = RequestMethod.POST, produces = "text/x-json; charset=utf-8")
    public String UploadRoomImg(@RequestParam("wangEditorH5File") MultipartFile multipartFile, HttpSession httpSession) {
        String result = "";

        System.out.print(multipartFile.getOriginalFilename());
        String imgType = multipartFile.getOriginalFilename().split("\\.")[1];
        String fileName = new StringBuilder( UUID.randomUUID().toString() ).append(".").append(imgType).toString();
        String path = new StringBuilder("/upload/")
                .append(DateUtil.dateToString(new Date()).replace('-', '/'))
                .toString();

        String realPath = httpSession.getServletContext().getRealPath("/WEB-INF"+path);

        File targetFile = new File(realPath, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        //保存
        try {
            multipartFile.transferTo(targetFile);
            result = path+"/"+fileName;
        } catch (Exception e) {
            result = "error|服务器端错误";
            e.printStackTrace();
        }

        return result;
    }

}

