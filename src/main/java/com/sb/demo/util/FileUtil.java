package com.sb.demo.util;

import io.lettuce.core.output.ByteArrayOutput;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;
import org.junit.platform.commons.util.StringUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class FileUtil {

    /**
     * 增量读取本地文件内容
     * @param filePath 要读取的文件路径
     * @param fileName 要读取的文件名
     * @param point 指针位置
     * @return Map<String, Object>
     * @throws IOException
     */
    public static Map<String, Object> RandomReadFile(String filePath, String fileName, long point) throws IOException {
        String content = null;
        RandomAccessFile raf = null;
        Map<String, Object> map = new HashMap<>();
        try {
            if (!filePath.endsWith(File.separator))
                filePath += File.separator;
            File file = new File(filePath + fileName);
            Long fileLength = file.length();
            Long diffLength = fileLength - point;
            byte[] bytes = new byte[diffLength.intValue()];
            raf = new RandomAccessFile(filePath + fileName, "r");
            raf.seek(point);
            raf.readFully(bytes);
            point = raf.getFilePointer();
            map.put("point", point);
            if (bytes != null) {
                content = new String(bytes, StandardCharsets.UTF_8);
                map.put("content", content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (raf != null) {
                raf.close();
            }
        }
        return map;
    }

    /**
     * 读取远程文件内容
     * @param remoteUrl 远程文件路径
     * @return List<String> 远程文件内容
     */
    public static List<String> readRemoteFile(String remoteUrl) {
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        List<String> fileContentList = new ArrayList<>();
        try {
            URL url = new URL(remoteUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("content-type;charset=utf-8", "text/html");
            String msg = connection.getHeaderField(0);
            if (msg.indexOf("404") != -1) {
                throw new RuntimeException("file is not exist, " + remoteUrl);
            }
            connection.connect();
            inputStream = connection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (!StringUtils.isBlank(line)) {
                    fileContentList.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileContentList;
    }

    /**
     * 读取本地文件内容
     * @param filePath 本地文件路径
     * @param fileName 本地文件名称
     * @return String
     */
    public static String readFile(String filePath, String fileName) {
        FileInputStream fileInputStream = null;
        String content = null;
        try {
            if (!filePath.endsWith(File.separator))
                filePath += File.separator;
            File file = new File(filePath + fileName);
            Long fileLength = file.length();
            byte[] fileContent = new byte[fileLength.intValue()];
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(fileContent);
            content = new String(fileContent, StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    /**
     * 写文件（在原文件的内容上增加）
     * @param filePath 文件路径
     * @param fileName 文件名
     * @param content 要写入的文件内容
     * @return String
     */
    public static String writeFile(String filePath, String fileName, String content) {
        String result = null;
        if (filePath.endsWith(File.separator))
            filePath += File.separator;
        File file = new File(filePath + fileName);
        File path = new File(filePath);
        try (FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8)
        ) {
            if (!file.exists()) {
                if (path.exists())
                    path.mkdirs();
                file.createNewFile();
            }
            StringBuffer stringBuffer = new StringBuffer();
            String temp = "";
            while ((temp = bufferedReader.readLine()) != null) {
                stringBuffer.append(temp).append("\n");
            }
            stringBuffer.append(content);
            outputStreamWriter.write(stringBuffer.toString().toCharArray());
            outputStreamWriter.flush();
            result = "success";
        } catch (IOException e) {
            e.printStackTrace();
            result = "fail";
        }
        return result;
    }

    /**
     * 写文件（删除文件原来的内容）
     * @param filePath 文件路径
     * @param fileName 文件名
     * @param content 文件内容
     * @return String
     */
    public static String writeFile2(String filePath, String fileName, String content) {
        String result = null;
        if (!filePath.endsWith(File.separator))
            filePath += File.separator;
        File file = new File(filePath + fileName);
        File path = new File(filePath);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8)
        ) {
            if (!file.exists()) {
                if (!path.exists())
                    path.mkdirs();
                file.createNewFile();
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(content);
            outputStreamWriter.write(stringBuffer.toString().toCharArray());
            outputStreamWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            result = "fail";
        }
        return result;
    }

    /**
     * 删除文件
     * @param file 要删除的文件（包含文件路径和文件名）
     * @return boolean
     */
    public static boolean deleteFile(String file) {
        File file1 = new File(file);
        if (file1.isFile()) {
            if (!file1.exists())
                log.info("file is not exist. {}", file);
            file1.deleteOnExit();
            return true;
        } else {
            throw new IllegalArgumentException(file + " is not a file");
        }
    }

/**
     * 删除目录
     * @param path 要删除的目录
     * @return boolean
     */
    public static boolean deleteDirectory(String path) {
        if (!path.endsWith(File.separator))
            path += File.separator;
        File directory = new File(path);
        if (!directory.exists())
            log.info("directory is not exist. {}", directory);
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                deleteFile(files[i].getAbsolutePath());
            } else {
                deleteDirectory(files[i].getAbsolutePath());
            }
        }
        // todo 返回值有问题
        return true;
    }

    /**
     * 下载远程文件到指定目录（linux系统中有wget命令才可使用）
     * @param url 远程文件地址
     * @param path 指定的目录
     * @param userName 用户名
     * @param password 密码
     * @return Boolean
     */
    public static Boolean downloadFile(String url, String path, String userName, String password) {
        boolean result = false;
        try (
                // 接收正常结果流
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                // 接收异常结果流
                ByteArrayOutputStream errorOutputStream = new ByteArrayOutputStream();
        ) {
            log.info("url: {}", url);
            File filePath = new File(path);
            if (!filePath.exists())
                filePath.mkdirs();
            Map<String, File> fileMap = new HashMap<>();
            fileMap.put("path", filePath);
            CommandLine commandLine = new CommandLine("wget");
            commandLine.setSubstitutionMap(fileMap);
            if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)) {
                commandLine.addArgument("--http-user=" + userName.trim(), false);
                commandLine.addArgument("--http-password=" + password.trim(), false);
            }
            commandLine.addArgument("-c", false);
            commandLine.addArgument("-r", false);
            commandLine.addArgument("-nd", false);
            commandLine.addArgument("-np", false);
            commandLine.addArgument("-k", false);
            commandLine.addArgument("-L", false);
            commandLine.addArgument("-P", false);
            commandLine.addArgument("${path}", false);
            commandLine.addArgument(url, false);
            DefaultExecutor defaultExecutor = new DefaultExecutor();
            defaultExecutor.setExitValues(null);
            // 设置超时时间（min）
            ExecuteWatchdog executeWatchdog = new ExecuteWatchdog(1 * 60 * 1000);
            defaultExecutor.setWatchdog(executeWatchdog);
            PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(outputStream, errorOutputStream);
            defaultExecutor.setStreamHandler(pumpStreamHandler);
            log.info("commandLine: {}", commandLine);
            int count = defaultExecutor.execute(commandLine);// count = 0  success
            String resultStr = outputStream.toString("UTF-8");
            log.info("resultStr: {}", resultStr);
            String errorStr = outputStream.toString("UTF-8");
            log.info("errorStr: {}", errorStr);
            if (count == 0)
                result = true;

        } catch (Exception e) {
            log.error("IOException: {}", e);
        }
        return result;
    }



    public Map<String, String> runShell(String path, String fileName, String url) {
        Map<String, String> map = new HashMap<>();
        try {
            String key = "123";
            String pathLog = "/usr/local/temp/";
            String[] cmd = {"bash", "-c", path + fileName + " > " + pathLog + key + ".log 2>&1"};
            log.info("cmd: {}", cmd);
            // 执行shell命令
            ProcessBuilder builder = new ProcessBuilder(cmd);
            Map<String, String> envMap = builder.environment();
            envMap.put("LANG", "en_US.UTF-8");
            builder.directory(new File(path));
            builder.redirectErrorStream(true);
            Process pid = null;
            pid = builder.start();
            Field field = pid.getClass().getDeclaredField("pid");
            field.setAccessible(true);
            if (pid != null) {
                log.info("The process started successfully!");
            } else {
                log.error("Process failed to start!");
            }
            // deployTask.normalStream()
            pid.waitFor();
            int exitValue = pid.exitValue();
            log.info("exitalue: {}", exitValue);
            if (exitValue == 0) {
                // shell执行成功
            } else {
                // shell执行失败
            }
            map.put("exitValue", String.valueOf(exitValue));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
