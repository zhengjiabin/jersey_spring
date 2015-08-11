package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;

/**
 * 文件上传
 * 
 * @author Administrator
 * 
 */
@Service
public class UploadFileService {
    
    /**
     * 测试FormDataMultiPart文件上传
     * 
     * @param form
     * @return
     */
    public String uploadFile(FormDataMultiPart form) {
        FormDataBodyPart usernamePart = form.getField("username");
        String username = usernamePart.getValue();
        System.out.println(username);
        
        FormDataBodyPart passwordPart = form.getField("password");
        String password = passwordPart.getValue();
        System.out.println(password);
        
        FormDataBodyPart genderPart = form.getField("gender");
        String gender = genderPart.getValue();
        System.out.println(gender);
        
        List<FormDataBodyPart> hobbyParts = form.getFields("hobby");
        for (FormDataBodyPart hobbyPart : hobbyParts) {
            String hobby = hobbyPart.getValue();
            System.out.println(hobby);
        }
        
        FormDataBodyPart jobPart = form.getField("job");
        String job = jobPart.getValue();
        System.out.println(job);
        
        FormDataBodyPart filePart = form.getField("file");
        InputStream inputStream = filePart.getValueAs(InputStream.class);
        
        String filePath = "F:/temp/haha.png";
        saveFile(inputStream, filePath);
        return "true";
    }
    
    /**
     * 上传文件至服务器
     * 
     * @param uploadedInputStream
     * @param serverFilePath
     */
    private void saveFile(InputStream uploadedInputStream, String serverFilePath) {
        try {
            File file = new File(serverFilePath);
            OutputStream outpuStream = new FileOutputStream(file);
            
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }
            
            outpuStream.flush();
            outpuStream.close();
            uploadedInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 测试文件上传
     * 
     * @param fileName
     * @param uploadedInputStream
     * @param fileDetail
     * @param keywordObjs
     * @return
     */
    public String uploadFile2(String fileName, InputStream fileInputStream, FormDataContentDisposition fileContentDisposition) {
        String fileFullName = fileContentDisposition.getFileName();
        
        String fileType = fileFullName.substring(fileFullName.indexOf("."), fileFullName.length());
        String newFileName = fileName + fileType;
        File file = new File("F:/temp", newFileName);
        try {
            OutputStream outputStream = new FileOutputStream(file);
            int length = 0;
            
            byte[] buff = new byte[256];
            
            while (-1 != (length = fileInputStream.read(buff))) {
                outputStream.write(buff, 0, length);
            }
            fileInputStream.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return fileFullName;
    }
}
