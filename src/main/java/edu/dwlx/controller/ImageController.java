package edu.dwlx.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.*;

@Controller
//@RequestMapping("/zhifou")
public class ImageController {

    @GetMapping("/qrg1")
    public ResponseEntity<byte[]> getImg() {

        //通过自己写的http工具类获取到图片输入流
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("E:\\GitHub\\zhifou\\src\\main\\resources\\static\\001.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //将输入流转为byte[]
        byte[] bytesByStream = getBytesByStream(inputStream);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<>(bytesByStream,headers, HttpStatus.OK);
    }


    public byte[]  getBytesByStream(InputStream inputStream){
        byte[] bytes = new byte[1024];

        int b;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            while((b = inputStream.read(bytes)) != -1){

                byteArrayOutputStream.write(bytes,0,b);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
