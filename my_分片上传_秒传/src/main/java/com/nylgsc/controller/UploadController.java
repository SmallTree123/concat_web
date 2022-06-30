package com.nylgsc.controller;

import com.nylgsc.TestAop.UserServiceImpl;
import com.nylgsc.test.C;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.aspectj.weaver.ast.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private RedisTemplate redisTemplate;

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

    @GetMapping(value = "/sm2")
    public String sm2(){
        redisTemplate.delete("hello");
        return "SM2";
    }
    @GetMapping(value = "/sm/hello")
    public String smHello(){
        return "SM";
    }

    public static void main(String[] args) {
        ArrayList<Object> excludes = new ArrayList<>();
        String url = "/upload/sm/hello";
        excludes.add("/upload/sm/*");
        Iterator var4 = excludes.iterator();
        boolean matches = false;
        Matcher m;
        do {
            if (!var4.hasNext()) {
                System.out.println("打印false");
            }

            Object pattern = var4.next();
            Pattern p = Pattern.compile(pattern+"");
            m = p.matcher(url);
            matches = m.matches();
        } while(!matches);

        System.out.println("matches---->"+matches);
    }

}