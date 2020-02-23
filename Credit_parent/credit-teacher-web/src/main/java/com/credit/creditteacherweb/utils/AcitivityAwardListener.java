package com.credit.creditteacherweb.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.credit.pojo.Prize;
import com.credit.pojo.Stujoin;
import com.credit.service.IActivityAward;
import com.credit.service.IActivitystu;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AcitivityAwardListener extends AnalysisEventListener<Prize> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AcitivityAwardListener.class);
    
    

    List<Prize> list = new ArrayList<Prize>();
    
    private static final int BATCH_COUNT = 2000;

    private IActivityAward iActivityAward;

    public AcitivityAwardListener(IActivityAward iActivityAward) {
        this.iActivityAward = iActivityAward;
    }

    @Override
    public void invoke(Prize prize, AnalysisContext analysisContext) {
        list.add(prize);
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
        for (Prize prize:list){
            prize.setId(null);
            if (prize.getAname()!=null && prize.getStuname()!=null){
                if(iActivityAward.findAnameAndStuName(prize.getAname(),prize.getStuname()).isEmpty()){
                    iActivityAward.add(prize);
                }
            }
        }
    }
}
