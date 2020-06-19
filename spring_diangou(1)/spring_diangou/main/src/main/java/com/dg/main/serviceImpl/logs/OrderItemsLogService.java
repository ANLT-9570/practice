package com.dg.main.serviceImpl.logs;

import com.dg.main.Entity.logs.OrderItemsLog;
import com.dg.main.repository.log.OrderItemsLogRepository;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemsLogService {
    @Autowired
    OrderItemsLogRepository repository;
    public void save(OrderItemsLog item){
        repository.save(item);
    }
    public void batchSave(List<OrderItemsLog> items){
        repository.saveAll(items);
    }
    public Single<OrderItemsLog> rxSave(OrderItemsLog item){
        return Single.create(new SingleOnSubscribe<OrderItemsLog>() {
            @Override
            public void subscribe(SingleEmitter<OrderItemsLog> emitter) throws Exception {
                repository.save(item);
                emitter.onSuccess(item);
            }
        });
    }
    public Single<List<OrderItemsLog>> rxBatchSave(List<OrderItemsLog> items){
        return Single.create(new SingleOnSubscribe<List<OrderItemsLog>>() {
            @Override
            public void subscribe(SingleEmitter<List<OrderItemsLog>> emitter) throws Exception {
                repository.saveAll(items);
                emitter.onSuccess(items);
            }
        });
    }
}
