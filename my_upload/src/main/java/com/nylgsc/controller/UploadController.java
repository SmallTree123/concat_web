package com.nylgsc.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.nylgsc.anno.UseAnnotation;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
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

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    public  static OutputStream getBase64QRCode(String content) throws Exception {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        @SuppressWarnings("rawtypes")
        Map hints = new HashMap();

        //设置二维码四周白色区域的大小
        hints.put(EncodeHintType.MARGIN,1);
        //设置二维码的容错性
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //画二维码
        BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400, hints);
        BufferedImage image = toBufferedImage(bitMatrix);
//        //注意此处拿到字节数据
       OutputStream outputStream =imageToBytes(image,"jpg");
        return outputStream;
//        //Base64编码
//        return Base64.getEncoder().encodeToString(bytes);
    }

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    /**
     * 转换BufferedImage 数据为byte数组
     *
     * @param bImage
     * Image对象
     * @param format
     * image格式字符串.如"gif","png"
     * @return byte数组
     */
    public static OutputStream imageToBytes(BufferedImage bImage, String format) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(bImage, format, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    public static void main(String[] args) throws ParseException {
//        LocalDate date = LocalDate.now();
//        LocalDate lastMonth = LocalDate.now().minusMonths(1); // 当前月份减1
//        LocalDate firstDay = lastMonth.with(TemporalAdjusters.firstDayOfMonth()); // 获取当前月的第一天
//        LocalDate lastDay = lastMonth.with(TemporalAdjusters.lastDayOfMonth()); // 获取当前月的最后一天
//        System.out.println(date);
//        System.out.println(firstDay);
//        System.out.println(lastDay);
//        List<String> dataList = new ArrayList<>();
//        dataList.add("he");
//        dataList.add("hewod");
//        dataList.add("123llo");
//        dataList.add("234llo");
//        List<List<String>> lists = fixedGrouping(dataList, 500);
//        int[] arr = {1,2,3};
//        if (arr.length == 3){
//            System.out.println("true");
//        }
//        String date = "2023-01";
//        LocalDate dateTime = LocalDate.parse(date+"-01");
//        LocalDate firstDay = dateTime.with(TemporalAdjusters.firstDayOfMonth());
//        LocalDate lastDay = dateTime.with(TemporalAdjusters.lastDayOfMonth());
//        System.out.println(firstDay);
//        System.out.println(lastDay);
//        LocalDateTime now = LocalDateTime.of(2023, 3, 29, 12, 0, 0);
//        System.out.println("计算两个时间的差：");
//        LocalDateTime end = LocalDateTime.now();
//        Duration duration = Duration.between(now,end);
//        long days = duration.toDays(); //相差的天数
//        long hours = duration.toHours();//相差的小时数
//        long minutes = duration.toMinutes();//相差的分钟数
//        long millis = duration.toMillis();//相差毫秒数
//        long nanos = duration.toNanos();//相差的纳秒数
//        System.out.println(days);
//        System.out.println(hours);
//        System.out.println(minutes);
//        String[] titles = {"省份", "地市","客户名称", "商务负责人"};
        BigDecimal totalRemainAmount = new BigDecimal(0);
        totalRemainAmount = totalRemainAmount.add(new BigDecimal(12.45));
        System.out.println(totalRemainAmount.toString());

    }

    private static void sleepMethod() {
        System.out.println();
    }

    @RequestMapping(value = "/getQRcode",produces = MediaType.IMAGE_JPEG_VALUE)
    public BufferedImage getQRcode(HttpServletResponse response) throws Exception{
        OutputStream qrCode = getBase64QRCode("我是张晨晨，我最美");
        return ImageIO.read(new FileInputStream(qrCode.toString()));
    }

    public static <T> List<List<T>> fixedGrouping(List<T> source, int n) {

        if (null == source || source.size() == 0 || n <= 0)
            return null;
        List<List<T>> result = new ArrayList<List<T>>();
        int remainder = source.size() % n;
        int size = (source.size() / n);
        for (int i = 0; i < size; i++) {
            List<T> subset = null;
            subset = source.subList(i * n, (i + 1) * n);
            result.add(subset);
        }
        if (remainder > 0) {
            List<T> subset = null;
            subset = source.subList(size * n, size * n + remainder);
            result.add(subset);
        }
        return result;
    }
}
