package com.zmlejia.ljlife.api.module.demo.ppt;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.annotation.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Store results in files.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
@ThreadSafe
public class PptFilePipeline extends FilePersistentBase implements PageModelPipeline<PptOne> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * create a FilePipeline with default path"D:\yunpan\pptModel"
     */
    public PptFilePipeline() {
        setPath("D:\\yunpan\\pptModel");
    }

    public PptFilePipeline(String path) {
        setPath(path);
    }

    @Override
    public void process(PptOne pptOne, Task task) {
        if (!StringUtils.isEmpty(pptOne.getCategory())
                && !StringUtils.isEmpty(pptOne.getPptName())
                && !StringUtils.isEmpty(pptOne.getDownloadUrl())
                && pptOne.getDownloadUrl().endsWith(".rar")) {
            String path = this.path + PATH_SEPERATOR + task.getUUID() + PATH_SEPERATOR;
            try {
                String filePath = path + pptOne.getCategory() + ".txt";
                File file = new File(filePath);
                if (!file.exists()) {
                    file.createNewFile();
                }
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));
                printWriter.println(pptOne.getDownloadUrl());
                printWriter.close();
            } catch (IOException e) {
                logger.warn("write file error", e);
            }
        }
    }
}
