package com.nylgsc.controller;

import com.nylgsc.TestAop.UserServiceImpl;
import com.nylgsc.test.C;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

@CrossOrigin
@RestController
@RequestMapping("/upload")
public class UploadController {


    @Autowired
    UserServiceImpl userService;

    private static final Object[] a = {};
    private static final Object[] b = {};

    @PostMapping("/part")
    public void bigFile(HttpServletRequest request, HttpServletResponse response, String guid, Integer chunk, MultipartFile file, Integer chunks) {
//        ConcurrentHashMap map =new ConcurrentHashMap();
//        map.put();
//        map.get();
//        ReentrantLock reentrantLock  = new ReentrantLock();
//        reentrantLock.lock();
//
//        List list = new CopyOnWriteArrayList();
//        list.add();
        List<String> arrayList = new ArrayList<>(1);
        arrayList.add("1");
        arrayList.add("2");
        HashMap<Object, Object> hashMap = new HashMap<>();
//        hashMap.put();

    }

    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<>(1);
        arrayList.add("1");
        arrayList.add("2");
        arrayList.get(1);
    }

    public static int test(){
        int a = 0;
        try {
            a++;
            int b=1/0;
        } catch (Exception e) {
            return a;
        } finally {
            a=3;
            System.out.println(a);
        }
        return -1;
    }


}