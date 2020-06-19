package com.dg.main.serviceImpl.logs;

import com.dg.main.Entity.logs.OrderItemsRefundLog;
import com.dg.main.repository.log.OrderItemsRefundLogRepository;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemsRefundLogService {
    @Autowired
    OrderItemsRefundLogRepository refundLogRepository;
    public void save(OrderItemsRefundLog item){
        refundLogRepository.save(item);
    }
    public void batchSave(List<OrderItemsRefundLog> items){
        refundLogRepository.saveAll(items);
    }
    public Single<OrderItemsRefundLog> rxSave(OrderItemsRefundLog item){
        return Single.create(new SingleOnSubscribe<OrderItemsRefundLog>() {
            @Override
            public void subscribe(SingleEmitter<OrderItemsRefundLog> emitter) throws Exception {
                    refundLogRepository.save(item);
                    emitter.onSuccess(item);
            }
        });
    }
    public Single<List<OrderItemsRefundLog>> rxBatchSave(List<OrderItemsRefundLog> items){
        return Single.create(new SingleOnSubscribe<List<OrderItemsRefundLog>>() {
            @Override
            public void subscribe(SingleEmitter<List<OrderItemsRefundLog>> emitter) throws Exception {
                refundLogRepository.saveAll(items);
                emitter.onSuccess(items);
            }
        });
    }
}
