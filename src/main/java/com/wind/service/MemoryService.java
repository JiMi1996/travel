package com.wind.service;import com.github.pagehelper.PageHelper;import com.wind.common.Constant;import com.wind.mybatis.mapper.MemoryMapper;import com.wind.mybatis.pojo.Memory;import com.wind.mybatis.pojo.User;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import tk.mybatis.mapper.entity.Example;import java.util.List;import java.util.Optional;@Servicepublic class MemoryService {    @Autowired    MemoryMapper memoryMapper;    public Optional<Memory> getEntityByID(int id) {        return Optional.ofNullable(memoryMapper.selectByPrimaryKey(id));    }    public Optional<Memory> getEntityByEntity(Memory memory){return Optional.ofNullable(memoryMapper.selectOne(memory));}    public Optional<Memory> getEntityByName(Integer id) {        Memory memory = new Memory();        memory.setUserid(id);        return Optional.ofNullable(memoryMapper.selectOne(memory));    }    public List<Memory> getAll(int page) {        PageHelper.startPage(page, Constant.PAGE_SIZE);        return memoryMapper.selectAll();    }    public List<Memory> getAll(String type, String value, int page) {        Example example = new Example(Memory.class);        Example.Criteria criteria = example.createCriteria();        criteria.andLike(type, "%" + value + "%");        PageHelper.startPage(page, Constant.PAGE_SIZE);        return memoryMapper.selectByExample(example);    }    public int getCount() {        int count = memoryMapper.selectCount(new Memory());        return count;    }    public int getCount(String type, String value) {        Example example = new Example(Memory.class);        Example.Criteria criteria = example.createCriteria();        criteria.andLike(type, "%" + value + "%");        int count = memoryMapper.selectCountByExample(example);        return count;    }    @Transactional    public boolean addEntity(Memory memory) {        return memoryMapper.insertUseGeneratedKeys(memory) > 0;    }    @Transactional    public boolean modifyEntityById(Memory memory) {        return memoryMapper.updateByPrimaryKey(memory) > 0;    }    @Transactional    public boolean deleteEntityById(int id) {        return memoryMapper.deleteByPrimaryKey(id) > 0;    }}