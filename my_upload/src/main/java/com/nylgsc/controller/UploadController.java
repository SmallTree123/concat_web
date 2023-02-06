package com.nylgsc.controller;

import com.nylgsc.anno.UseAnnotation;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RestController
public class UploadController {


    @PostMapping("/aspect")
    public void testAspect(@RequestBody UserParam userParam){
        System.out.println("aspect");
    }

    @PostMapping("/upload")
    public void test(MultipartFile file,HttpServletRequest request){
        System.out.println("==============开始导入企业表数据====================userid={}");
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());
        if(file!=null&&file.getSize()<=0){
            System.out.println("导入失败,"+file.getOriginalFilename()+",文件无内容!");
        }
        try {
            long t1 = new Date().getTime();
            System.out.println("=======================开始解析上传文件===========================");
            ExcelParser parse = null;
            try {
				InputStream inputStream = file.getInputStream();
                parse = new ExcelParser().parse(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("导入失败,解析excel异常:{}");
            }
            List<String[]> datas = parse.getDatas();
            if(null==datas ||(null!=datas&&datas.size()<=0)){
                System.out.println("表格数据内容为空!请检查数据模板是否正确");
            }
//            datas.remove(0);//移除表的字段标题行
            long t2 = new Date().getTime();
            System.out.println("=======================POI解析出("+datas.size()+")条数据,POI解析上传文件耗时("+(t2-t1)+")===========================");
            List<List<String[]>> partitionList = ListUtils.partition(datas, 20000);//一个线程处理2千条数据
            // 创建一个线程池
            ExecutorService exec = Executors.newFixedThreadPool(10);
            // 定义一个任务集合
            List<Callable<List<Object>>> tasks = new ArrayList<>();
            Callable<List<Object>> task = null;
            for (List<String[]> list : partitionList) {
                task = new Callable<List<Object>>() {
                    @Override
                    public List<Object> call() throws Exception {
                        return new ArrayList<Object>();
                    }
                };
                // 这里提交的任务容器列表和返回的Future列表存在顺序对应的关系
                tasks.add(task);
            }
            //执行任务
            List<Object> failList = new ArrayList<>();
            try{
                List<Future<List<Object>>> results = exec.invokeAll(tasks);
                for (Future<List<Object>> future : results) {
                    failList.addAll(future.get());
                }
                //任务执行结束，如果有异常数据，此处会返回封装到failList中，这里可以根据自己的业务做一些处理
            }catch (Exception e){
                System.out.println("线程池执行任务异常:{}");
            }finally {
                // 关闭线程池
                exec.shutdown();
            }
            long t3 = new Date().getTime();
            System.out.println("====================数据入库总计耗时("+(t3-t2)+")==============================");
        } catch (Exception e) {
            System.out.println("导入表数据操作失败,发现异常:");
        }

    }

    @PostMapping("/upload2")
    public void test2(MultipartFile file,HttpServletRequest request) throws Exception{
        InputStream inputStream = file.getInputStream();
        Scanner sc = new Scanner(inputStream, "UTF-8");
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            System.out.println(line);
        }
        inputStream.close();
        sc.close();

    }

    private static final String CSV_COLUMN_SEPARATOR = ",";
    private static final String CSV_RN = "\r\n";

    @GetMapping("/exportFansCsv2")
    public void doExport2(HttpServletResponse response) throws Exception {
        String[] title = {"登录用户id"};
        List<String> loginUserIds = new ArrayList<>();
        loginUserIds.add("10");
        loginUserIds.add("11");
        loginUserIds.add("12");
        loginUserIds.add("13");
        loginUserIds.add("14");
        loginUserIds.add("15");
        loginUserIds.add("16");
        loginUserIds.add("17");
        setFansHeader2(loginUserIds,title,response);
    }



    private void setFansHeader2(List<String> loginUserIds,String[] colNames, HttpServletResponse response) {

        String fileName = System.currentTimeMillis()+"";

        //设置编码
        response.setCharacterEncoding("UTF8");
        //设置响应类型
        response.setContentType("application/ms-txt.numberformat:@");
        //设置响应头
        response.setHeader("Pragma","public");
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".csv");

        StringBuffer buf = new StringBuffer();

        for (String aColNamesArr : colNames) {
            buf.append(aColNamesArr).append(CSV_COLUMN_SEPARATOR);
        }

        buf.append(CSV_RN);

        if (null != loginUserIds) { // 输出数据
            for (String id:loginUserIds) {
                buf.append(id).append(CSV_COLUMN_SEPARATOR);
                buf.append(CSV_RN);
            }
        }

        try {
            response.getOutputStream().write(buf.toString().getBytes("GBK"));
        } catch (IOException e) {
            System.out.println("下载失败");
            e.printStackTrace();
        }
    }


    @PostMapping("/uploadCsv")
    public void uploadCsv(UserParam param) throws Exception {
        MultipartFile multipartFile = param.getFile();
        System.out.println(param.toString());
        //选择用缓冲区来实现这个转换即使用java 创建的临时文件 使用 MultipartFile.transferto()方法 。
        File file = null;
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String[] filename = originalFilename.split("\\.");
            file = File.createTempFile(filename[0], filename[1]);    //注意下面的 特别注意！！！
            multipartFile.transferTo(file);
            file.deleteOnExit();
            insertData(file);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (file != null){
                file.delete();
            }
        }
    }


    public void insertData(File file) {
        List<String> list;
        BufferedReader reader = null;
        //读取第一行的标题信息
        try {
            reader = new BufferedReader(new FileReader(file));
            String head = reader.readLine();
            while ((list = readCSV(reader)).size() != 0){
                //开始往数据库插入信息
                System.out.println(list.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * @Description: 把数据从csv文件中读取到list
     * @Param: [reader] 字符缓冲流
     * @return: java.util.List 读取到的数据（一次读取10000行）
     */
    public List<String> readCSV(BufferedReader reader){
        try {
            List<String> list = new ArrayList<>();
            String line;
            for(int i=0; i < 10000; i++) {
                line = reader.readLine();
                if(line == null){
                    break;
                }
                list.add(line);
            }
            return list;
        } catch (Exception e) {
            try {
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }
    @Autowired
    public UseAnnotation annotation;

    public static void main(String[] args){
        Integer num = 0;
        System.out.println(num);
    }

}
