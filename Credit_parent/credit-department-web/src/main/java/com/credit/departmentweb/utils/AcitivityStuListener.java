package com.credit.departmentweb.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.credit.pojo.Stujoin;
import com.credit.service.IActivitystu;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AcitivityStuListener extends AnalysisEventListener<Stujoin> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AcitivityStuListener.class);
    
    

    List<Stujoin> list = new ArrayList<Stujoin>();
    
    private static final int BATCH_COUNT = 2000;

    private IActivitystu iActivitystu;

    public AcitivityStuListener(IActivitystu iActivitystu) {
        this.iActivitystu = iActivitystu;
    }

    @Override
    public void invoke(Stujoin stujoin, AnalysisContext analysisContext) {
        list.add(stujoin);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
    }

    private void saveData() {
        System.out.println("开始输出");
        for (Stujoin stujoin:list){
            stujoin.setId(null);
            if (stujoin.getAname()!=null && stujoin.getStuname()!=null){
                if (iActivitystu.findAnameAndStuName(stujoin.getAname(),stujoin.getStuname()).isEmpty()){
                    iActivitystu.add(stujoin);
                }
            }
        }
    }
}
