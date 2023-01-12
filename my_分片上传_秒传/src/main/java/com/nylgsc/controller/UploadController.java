package com.nylgsc.controller;

import com.nylgsc.TestAop.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
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
    public void bigFile(HttpServletRequest request, HttpServletResponse response, String guid, Integer chunk, MultipartFile file, Integer chunks) throws Exception {
//        ConcurrentHashMap map =new ConcurrentHashMap();
//        map.put();
//        map.get();
        ReentrantLock reentrantLock  = new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.lockInterruptibly();
        ThreadLocal tl = new ThreadLocal();
//
//        List list = new CopyOnWriteArrayList();
//        list.add();
//        List<String> arrayList = new ArrayList<>(1);
//        arrayList.add("1");
//        arrayList.add("2");
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put(12,12);
//        Map map = new LinkedHashMap<>();
        HashSet<Object> set = new HashSet<>();
        set.add(1);
//        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(12);
//        poolExecutor.submit(new FutureTask<>(new Callable<Object>() {
//            @Override
//            public Object call() throws Exception {
//                return null;
//            }
//        }));
        LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>();
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