package com.ouresports.grimstroke.app.base.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ouresports.grimstroke.app.base.annotation.*;
import com.ouresports.grimstroke.app.base.template.PaginationTemplate;
import com.ouresports.grimstroke.app.base.template.ResultTemplate;
import com.ouresports.grimstroke.app.base.template.SingleTemplate;
import com.ouresports.grimstroke.core.base.entity.BaseEntity;
import com.ouresports.grimstroke.core.base.entity.BaseTo;
import com.ouresports.grimstroke.core.base.service.Service;
import com.ouresports.grimstroke.core.util.CollectionUtil;
import com.ouresports.grimstroke.core.util.EntityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.lang.reflect.ParameterizedType;
import java.util.Set;

/**
 *
 * @author will
 * @date 2018/12/7
 */
@SuppressWarnings("unchecked")
public abstract class BaseCrudController<T extends BaseEntity, S extends Service<T>> extends AbstractController {
    @Autowired
    protected S baseService;
    @Autowired
    private Validator validator;
    private String tableName;
    private Class<T> entityClass;

    /**
     * 列表
     * @param currentPage
     * @param per
     * @param t
     * @return
     * @throws Exception
     */
    @GetMapping("")
    public ResponseEntity index(@RequestParam(value="page", defaultValue="1") int currentPage,
                                @RequestParam(defaultValue="10") int per,
                                T t) throws Exception {
        RestIndex restIndex = getClass().getAnnotation(RestIndex.class);
        if (restIndex == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Page page = new Page<>(currentPage, per);
        IPage data;
        if (restIndex.dto()) {
            data = baseService.getDtos(page, new QueryWrapper<>(t).orderByDesc(String.format("`%s`.`created_at`", getTableName())));
        } else {
            data = baseService.list(page, new QueryWrapper<>(t).orderByDesc("`created_at`"));
        }
        if (restIndex.voClass() != Object.class) {
            page.setRecords(CollectionUtil.copyElementsAttr(page.getRecords(), restIndex.voClass()));
        }
        return render(new PaginationTemplate(data));
    }

    /**
     * 详情
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/{id}")
    public ResponseEntity show(@PathVariable long id) throws Exception {
        RestShow restShow = getClass().getAnnotation(RestShow.class);
        if (restShow == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Object data;
        if (restShow.dto()) {
            data = baseService.getDto(id);
        } else {
            data = baseService.find(id);
        }
        if (restShow.voClass() == Object.class) {
            return render(new SingleTemplate<>(data));
        } else {
            Object vo = restShow.voClass().newInstance();
            BeanUtils.copyProperties(data, vo);
            return render(new SingleTemplate<>(vo));
        }
    }

    /**
     * 创建
     * @param jsonObject
     * @return
     * @throws Exception
     */
    @PostMapping("")
    public ResponseEntity create(@RequestBody JSONObject jsonObject) throws Exception {
        RestCreate restCreate = getClass().getAnnotation(RestCreate.class);
        if (restCreate == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        BaseTo<T> rbo = (BaseTo<T>) jsonObject.toJavaObject(restCreate.rboClass());
        if (rbo.getTargetClass() != getEntityClass()) {
            throw new ClassCastException(String.format("request body object 类型 %s 与目标类型 %s 不一致", rbo.getTargetClass().getName(), getEntityClass().getName()));
        }
        Set<ConstraintViolation<BaseTo>> violationSet = validator.validate(rbo);
        if (!violationSet.isEmpty()) {
            throw new ConstraintViolationException(violationSet);
        }
        baseService.save(rbo.convertTo());
        return render(ResultTemplate.createOk());
    }

    /**
     * 更新
     * @param id
     * @param jsonObject
     * @return
     * @throws Exception
     */
    @PatchMapping("/{id}")
    public ResponseEntity update(@PathVariable long id,
                                 @RequestBody JSONObject jsonObject) throws Exception {
        RestUpdate restUpdate = getClass().getAnnotation(RestUpdate.class);
        if (restUpdate == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        BaseTo<T> rbo = (BaseTo<T>) jsonObject.toJavaObject(restUpdate.rboClass());
        if (rbo.getTargetClass() != getEntityClass()) {
            throw new ClassCastException(String.format("request body object 类型 %s 与目标类型 %s 不一致", rbo.getTargetClass().getName(), getEntityClass().getName()));
        }
        if (restUpdate.valid()) {
            Set<ConstraintViolation<BaseTo>> violationSet = validator.validate(rbo);
            if (!violationSet.isEmpty()) {
                throw new ConstraintViolationException(violationSet);
            }
        }
        T t = rbo.convertTo();
        t.setId(id);
        baseService.updateById(t);
        return render(ResultTemplate.updateOk());
    }

    /**
     * 删除
     * @param id
     * @return
     * @throws Exception
     */
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) throws Exception {
        RestDelete restDelete = getClass().getAnnotation(RestDelete.class);
        if (restDelete == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        baseService.removeById(id);
        return render(ResultTemplate.deleteOk());
    }

    protected Class<T> getEntityClass() {
        if (entityClass == null) {
            entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return entityClass;
    }

    protected String getTableName() {
        if (tableName == null) {
            tableName = EntityUtil.getEntityTableName(getEntityClass());
        }
        return tableName;
    }
}
