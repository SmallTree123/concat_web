package com.nylgsc.controller;

import com.nylgsc.TestAop.UserServiceImpl;
import com.nylgsc.test.C;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.aspectj.weaver.ast.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
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
        ReentrantLock reentrantLock  = new ReentrantLock();
        reentrantLock.lock();
//        reentrantLock.lockInterruptibly();
//        ThreadLocal tl = new ThreadLocal();
//        tl.set();

//
//        List list = new CopyOnWriteArrayList();
//        list.add();
//        List<String> arrayList = new ArrayList<>(1);
//        arrayList.add("1");
//        arrayList.add("2");
        HashMap<Object, Object> hashMap = new HashMap<>();
//        hashMap.put();
//        Map map = new LinkedHashMap<>();
        HashSet<Object> set = new HashSet<>();
//        set.add();
        ExecutorService poolExecutor = Executors.newSingleThreadExecutor();
        poolExecutor.submit(new FutureTask<>(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        }));
        LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>();
//        queue.offer();
        queue.remove();
        System.out.println("hello");
    }

    public static void main(String[] args) {
        int i =0;
        System.out.println(++i);
        List<String> arrayList = new ArrayList<>(1);
        arrayList.add("1");
        arrayList.add("2");
//        arrayList.get(1);

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean(new FileInputStream(),);
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