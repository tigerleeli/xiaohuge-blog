package com.llh.moneykeep.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llh.moneykeep.common.BasePageParam;
import com.llh.moneykeep.common.BasePageResult;
import com.llh.moneykeep.common.BizException;
import com.llh.moneykeep.common.ContextHolder;
import com.llh.moneykeep.dto.record.*;
import com.llh.moneykeep.entity.Account;
import com.llh.moneykeep.entity.Category;
import com.llh.moneykeep.entity.Record;
import com.llh.moneykeep.entity.Record;
import com.llh.moneykeep.mapper.RecordMapper;
import com.llh.moneykeep.service.AccountService;
import com.llh.moneykeep.service.CategoryService;
import com.llh.moneykeep.service.RecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
    @Resource
    private AccountService accountService;

    @Resource
    private CategoryService categoryService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean add(RecordAddParam param) {
        long userId = ContextHolder.getContext().getUserId();
        Record record = new Record();
        // 拷贝属性
        BeanUtil.copyProperties(param, record);
        record.setUserId(userId);
        // 新增记录
        save(record);

        if (ObjectUtil.equal(param.getType(), RecordType.COST.getValue())) {
            // 支出 减少账户余额
            accountService.decreaseBalance(param.getAccountId(), param.getAmount());
        } else if (ObjectUtil.equal(param.getType(), RecordType.INCOME.getValue())) {
            // 收入 增加账户余额
            accountService.increaseBalance(param.getAccountId(), param.getAmount());
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean removeById(Long id) {
        long userId = ContextHolder.getContext().getUserId();
        List<Record> recordList = list(new LambdaQueryWrapper<Record>()
                .eq(Record::getUserId, userId)
                .eq(Record::getId, id)
                .eq(Record::getIsDeleted, 0));
        if (CollUtil.isEmpty(recordList)) {
            throw new BizException("该记录不存在");
        }
        Record selected = recordList.get(0);

        // 逻辑删除
        Record update = new Record();
        update.setId(id);
        update.setIsDeleted(1);
        updateById(update);

        // 恢复账户余额
        if (ObjectUtil.equal(selected.getType(), RecordType.COST.getValue())) {
            accountService.increaseBalance(selected.getAccountId(), selected.getAmount());
        } else if (ObjectUtil.equal(selected.getType(), RecordType.INCOME.getValue())) {
            accountService.decreaseBalance(selected.getAccountId(), selected.getAmount());
        }
        return true;
    }

    @Override
    public RecordPageResult<RecordResult> page(RecordPageParam param) {
        long userId = ContextHolder.getContext().getUserId();
        LambdaQueryWrapper<Record> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Record::getIsDeleted, 0);
        queryWrapper.eq(Record::getUserId, userId);
        queryWrapper.orderByDesc(Record::getCreateTime);
        if (ObjectUtil.isNotNull(param.getStartDate())) {
            // 开始日期
            queryWrapper.ge(Record::getCreateTime, LocalDateTime.of(param.getStartDate(), LocalTime.MIN));
        }
        if (ObjectUtil.isNotNull(param.getEndDate())) {
            // 结束日期
            queryWrapper.le(Record::getCreateTime, LocalDateTime.of(param.getEndDate(), LocalTime.MAX));
        }
        if (ObjectUtil.isNotNull(param.getCategoryId())) {
            // 分类
            queryWrapper.eq(Record::getCategoryId, param.getCategoryId());
        }
        if (ObjectUtil.isNotNull(param.getAccountId())) {
            // 账户
            queryWrapper.eq(Record::getAccountId, param.getAccountId());
        }
        // 分页查询
        Page<Record> page = page(new Page<>(param.getPageNum(), param.getPageSize()), queryWrapper);

        // 获取统计总金额
        AmountStatsParam amountStatsParam = new AmountStatsParam();
        BeanUtil.copyProperties(param, amountStatsParam);
        BigDecimal totalAmount = statsTotalAmount(amountStatsParam);

        List<Record> recordList = page.getRecords();
        if (CollUtil.isEmpty(recordList)) {
            // 为空直接返回
            return new RecordPageResult<>(page, ListUtil.empty(), totalAmount);
        }

        List<Long> categoryIds = recordList.stream()
                .map(Record::getCategoryId)
                .distinct()
                .collect(Collectors.toList());
        List<Category> categoryList = categoryService.listByIds(categoryIds);

        List<Long> accountIds = recordList.stream()
                .map(Record::getAccountId)
                .distinct()
                .collect(Collectors.toList());
        List<Account> accountList = accountService.listByIds(accountIds);

        List<RecordResult> resList = new ArrayList<>();
        for (Record record : recordList) {
            RecordResult res = new RecordResult();
            BeanUtil.copyProperties(record, res);

            Optional<Category> categoryOptional = categoryList.stream()
                    .filter(e -> ObjectUtil.equal(e.getId(), record.getCategoryId()))
                    .findAny();
            categoryOptional.ifPresent(category -> res.setCategoryName(category.getName()));

            Optional<Account> accountOptional = accountList.stream()
                    .filter(e -> ObjectUtil.equal(e.getId(), record.getAccountId()))
                    .findAny();
            accountOptional.ifPresent(category -> res.setAccountName(category.getName()));

            resList.add(res);
        }

        return new RecordPageResult<>(page, resList, totalAmount);
    }

    @Override
    public BigDecimal statsTotalAmount(AmountStatsParam param) {
        return null;
    }

}
