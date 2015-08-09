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
import com.sun.jersey.multipart.FormDataParam;

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
    
    public String uploadFile2(@FormDataParam("fileName")
    String fileName, @FormDataParam("file")
    InputStream uploadedInputStream, @FormDataParam("file")
    FormDataContentDisposition fileDetail) {
        String fileFullName = fileDetail.getFileName();
        
        try {
            OutputStream outputStream = new FileOutputStream(new File("D:\\upload", fileName + fileFullName.substring(fileFullName.indexOf("."), fileFullName.length())));
            int length = 0;
            
            byte[] buff = new byte[256];
            
            while (-1 != (length = uploadedInputStream.read(buff))) {
                outputStream.write(buff, 0, length);
            }
            uploadedInputStream.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return fileFullName;
    }
}
