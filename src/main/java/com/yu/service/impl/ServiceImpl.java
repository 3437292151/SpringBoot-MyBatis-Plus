//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yu.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.yu.service.IService;
import com.yu.service.mapper.EntityMapper;
import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ServiceImpl<DAO extends BaseMapper<E>, Mapper extends EntityMapper<D, E>, D, E> implements IService<D, E> {
    protected Log log = LogFactory.getLog(this.getClass());

    @Autowired
    protected DAO baseMapper;

    @Autowired
    protected Mapper entityMapper;
    private Wrapper<D> queryWrapper;
    private Function<? super Object, Object> mapper;

    public ServiceImpl() {
    }

    public DAO getBaseMapper() {
        return this.baseMapper;
    }

    public Mapper getEntityMapper() {
        return this.entityMapper;
    }

    protected boolean retBool(Integer result) {
        return SqlHelper.retBool(result);
    }

    protected Class<E> currentModelClass() {
        return (Class<E>) ReflectionKit.getSuperClassGenericType(this.getClass(), 2);
    }

    protected SqlSession sqlSessionBatch() {
        return SqlHelper.sqlSessionBatch(this.currentModelClass());
    }

    protected void closeSqlSession(SqlSession sqlSession) {
        SqlSessionUtils.closeSqlSession(sqlSession, GlobalConfigUtils.currentSessionFactory(this.currentModelClass()));
    }

    protected String sqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.table(this.currentModelClass()).getSqlStatement(sqlMethod.getMethod());
    }

    public boolean save(D entity) {
        return this.retBool(Integer.valueOf(this.baseMapper.insert(this.entityMapper.toEntity(entity))));
    }

    @Transactional(
        rollbackFor = {Exception.class}
    )
    public boolean saveBatch(Collection<D> entityList, int batchSize) {
        String sqlStatement = this.sqlStatement(SqlMethod.INSERT_ONE);
        SqlSession batchSqlSession = this.sqlSessionBatch();
        Throwable var5 = null;
        try {
            int i = 0;

            for(Iterator var7 = entityList.iterator(); var7.hasNext(); ++i) {
                E anEntityList = this.entityMapper.toEntity( (D) var7.next());
                batchSqlSession.insert(sqlStatement, anEntityList);
                if(i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
            }

            batchSqlSession.flushStatements();
            return true;
        } catch (Throwable var16) {
            var5 = var16;
            throw var16;
        } finally {
            if(batchSqlSession != null) {
                if(var5 != null) {
                    try {
                        batchSqlSession.close();
                    } catch (Throwable var15) {
                        var5.addSuppressed(var15);
                    }
                } else {
                    batchSqlSession.close();
                }
            }

        }
    }

    @Transactional(
        rollbackFor = {Exception.class}
    )
    public boolean saveOrUpdate(D entity) {
        if(null == entity) {
            return false;
        } else {
            E e = this.entityMapper.toEntity(entity);
            Class<?> cls = e.getClass();
            TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
            Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!", new Object[0]);
            String keyProperty = tableInfo.getKeyProperty();
            Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!", new Object[0]);
            Object idVal = ReflectionKit.getMethodValue(cls, entity, tableInfo.getKeyProperty());
            return !StringUtils.checkValNull(idVal) && !Objects.isNull(this.getById((Serializable)idVal))?this.updateById(entity):this.save(entity);
        }
    }

    @Transactional(
        rollbackFor = {Exception.class}
    )
    public boolean saveOrUpdateBatch(Collection<D> entityList, int batchSize) {
        Assert.notEmpty(entityList, "error: entityList must not be empty", new Object[0]);
        Class<?> cls = this.currentModelClass();
        TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
        Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!", new Object[0]);
        String keyProperty = tableInfo.getKeyProperty();
        Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!", new Object[0]);
        SqlSession batchSqlSession = this.sqlSessionBatch();
        Throwable var7 = null;

        try {
            int i = 0;

            for(Iterator var9 = entityList.iterator(); var9.hasNext(); ++i) {
                E entity = this.entityMapper.toEntity((D) var9.next());
                Object idVal = ReflectionKit.getMethodValue(cls, entity, keyProperty);
                if(!StringUtils.checkValNull(idVal) && !Objects.isNull(this.getById((Serializable)idVal))) {
                    ParamMap<E> param = new ParamMap();
                    param.put("et", entity);
                    batchSqlSession.update(this.sqlStatement(SqlMethod.UPDATE_BY_ID), param);
                } else {
                    batchSqlSession.insert(this.sqlStatement(SqlMethod.INSERT_ONE), entity);
                }

                if(i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
            }

            batchSqlSession.flushStatements();
            return true;
        } catch (Throwable var20) {
            var7 = var20;
            throw var20;
        } finally {
            if(batchSqlSession != null) {
                if(var7 != null) {
                    try {
                        batchSqlSession.close();
                    } catch (Throwable var19) {
                        var7.addSuppressed(var19);
                    }
                } else {
                    batchSqlSession.close();
                }
            }

        }
    }

    public boolean removeById(Serializable id) {
        return SqlHelper.retBool(Integer.valueOf(this.baseMapper.deleteById(id)));
    }

    public boolean removeByMap(Map<String, Object> columnMap) {
        Assert.notEmpty(columnMap, "error: columnMap must not be empty", new Object[0]);
        return SqlHelper.retBool(Integer.valueOf(this.baseMapper.deleteByMap(columnMap)));
    }

    public boolean remove(Wrapper<D> wrapper) {
        E entity = this.entityMapper.toEntity(wrapper.getEntity());
        Wrapper<E> eWrapper = Wrappers.update(entity);
        return SqlHelper.retBool(Integer.valueOf(this.baseMapper.delete(eWrapper)));
    }

    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return SqlHelper.retBool(Integer.valueOf(this.baseMapper.deleteBatchIds(idList)));
    }

    public boolean updateById(D entity) {
        return this.retBool(Integer.valueOf(this.baseMapper.updateById(this.entityMapper.toEntity(entity))));
    }

    public boolean update(D entity, Wrapper<D> updateWrapper) {
        E e = this.entityMapper.toEntity(entity);
        Wrapper<E> wrapper = Wrappers.update(this.entityMapper.toEntity(updateWrapper.getEntity()));
        return this.retBool(Integer.valueOf(this.baseMapper.update(e, wrapper)));
    }

    @Transactional(
        rollbackFor = {Exception.class}
    )
    public boolean updateBatchById(Collection<D> entityList, int batchSize) {
        Assert.notEmpty(entityList, "error: entityList must not be empty", new Object[0]);
        String sqlStatement = this.sqlStatement(SqlMethod.UPDATE_BY_ID);
        SqlSession batchSqlSession = this.sqlSessionBatch();
        Throwable var5 = null;

        try {
            int i = 0;

            for(Iterator var7 = entityList.iterator(); var7.hasNext(); ++i) {
                E anEntityList = this.entityMapper.toEntity((D) var7.next());
                ParamMap<E> param = new ParamMap();
                param.put("et", anEntityList);
                batchSqlSession.update(sqlStatement, param);
                if(i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
            }

            batchSqlSession.flushStatements();
            return true;
        } catch (Throwable var17) {
            var5 = var17;
            throw var17;
        } finally {
            if(batchSqlSession != null) {
                if(var5 != null) {
                    try {
                        batchSqlSession.close();
                    } catch (Throwable var16) {
                        var5.addSuppressed(var16);
                    }
                } else {
                    batchSqlSession.close();
                }
            }

        }
    }

    public D getById(Serializable id) {
        return this.entityMapper.toDto(this.baseMapper.selectById(id));
    }

    public Collection<D> listByIds(Collection<? extends Serializable> idList) {
        return this.entityMapper.toDto(this.baseMapper.selectBatchIds(idList));
    }

    public Collection<D> listByMap(Map<String, Object> columnMap) {
        return this.entityMapper.toDto(this.baseMapper.selectByMap(columnMap));
    }

    public D getOne(Wrapper<D> queryWrapper, boolean throwEx) {
        Wrapper<E> wrapper = Wrappers.query(this.entityMapper.toEntity(queryWrapper.getEntity()));
        return throwEx?this.entityMapper.toDto(this.baseMapper.selectOne(wrapper)):SqlHelper.getObject(this.log, this.entityMapper.toDto(this.baseMapper.selectList(wrapper)));
    }

    public Map<String, Object> getMap(Wrapper<D> queryWrapper) {
        Wrapper<E> wrapper = Wrappers.query(this.entityMapper.toEntity(queryWrapper.getEntity()));
        return (Map)SqlHelper.getObject(this.log, this.baseMapper.selectMaps(wrapper));
    }



    public int count(Wrapper<D> queryWrapper) {
        Wrapper<E> wrapper = Wrappers.query(this.entityMapper.toEntity(queryWrapper.getEntity()));
        return SqlHelper.retCount(this.baseMapper.selectCount(wrapper));
    }

    public List<D> list(Wrapper<D> queryWrapper) {
        Wrapper<E> wrapper = Wrappers.query(this.entityMapper.toEntity(queryWrapper.getEntity()));
        return this.entityMapper.toDto(this.baseMapper.selectList(wrapper));
    }

    public IPage<D> page(Pageable pageable, Wrapper<D> queryWrapper) {
        Wrapper<E> wrapper = Wrappers.query(this.entityMapper.toEntity(queryWrapper.getEntity()));
        IPage<E> eiPage = new Page<E>();
        eiPage.setCurrent(pageable.getPageNumber());
        eiPage.setSize(pageable.getPageNumber());
        //eiPage.setOrders(page.orders());
        IPage<E> eiPage1 = this.baseMapper.selectPage(eiPage, wrapper);
        Page<D> page = new Page<D>();
        page.setTotal(eiPage1.getTotal());
        page.setPages(eiPage1.getPages());
        page.setRecords(this.entityMapper.toDto(eiPage1.getRecords()));
        return page;
    }

    public List<Map<String, Object>> listMaps(Wrapper<D> queryWrapper) {
        Wrapper<E> wrapper = Wrappers.query(this.entityMapper.toEntity(queryWrapper.getEntity()));
        return this.baseMapper.selectMaps(wrapper);
    }

    public <V> List<V> listObjs(Wrapper<D> queryWrapper, Function<? super Object, V> mapper) {
        
        Wrapper<E> wrapper = Wrappers.query(this.entityMapper.toEntity(queryWrapper.getEntity()));
        return this.baseMapper.selectObjs(wrapper).stream().filter(Objects::nonNull).map(mapper).collect(Collectors.toList());
    }

    public IPage<Map<String, Object>> pageMaps(IPage<D> page, Wrapper<D> queryWrapper) {
        Wrapper<E> wrapper = Wrappers.query(this.entityMapper.toEntity(queryWrapper.getEntity()));
        Page<E> eiPage = new Page<E>();
        eiPage.setCurrent(page.getCurrent());
        eiPage.setSize(page.getPages());
        eiPage.setOrders(page.orders());
        return this.baseMapper.selectMapsPage(eiPage, wrapper);
    }

    public <V> V getObj(Wrapper<D> queryWrapper, Function<? super Object, V> mapper) {
        return SqlHelper.getObject(this.log, this.listObjs(queryWrapper, mapper));
    }
}
