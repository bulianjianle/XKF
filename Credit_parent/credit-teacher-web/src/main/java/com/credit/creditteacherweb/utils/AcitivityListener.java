package com.credit.creditteacherweb.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.credit.mapper.ActivityMapper;
import com.credit.pojo.Activity;
import com.credit.service.IAllAcitivity;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AcitivityListener extends AnalysisEventListener<Activity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AcitivityListener.class);
    
    

    List<Activity> list = new ArrayList<Activity>();
    
    private static final int BATCH_COUNT = 3000;

    private IAllAcitivity iAllAcitivity;

    public AcitivityListener(IAllAcitivity iAllAcitivity) {
        this.iAllAcitivity = iAllAcitivity;
    }

    @Override
    public void invoke(Activity activity, AnalysisContext analysisContext) {
        list.add(activity);
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
        for (Activity activity:list){
            activity.setId(null);
            if (iAllAcitivity.findByAname(activity.getAname()).isEmpty()){
                iAllAcitivity.add(activity);
            }
        }
    }
}
