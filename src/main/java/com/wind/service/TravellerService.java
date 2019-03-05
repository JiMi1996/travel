package com.wind.service;import com.github.pagehelper.PageHelper;import com.wind.common.Constant;import com.wind.mybatis.mapper.TicketMapper;import com.wind.mybatis.mapper.TravellerMapper;import com.wind.mybatis.pojo.Ticket;import com.wind.mybatis.pojo.Traveller;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import tk.mybatis.mapper.entity.Example;import java.util.List;import java.util.Optional;@Servicepublic class TravellerService {    @Autowired    TravellerMapper travellerMapper;    public Optional<Traveller> getEntityByID(int id) {        return Optional.ofNullable(travellerMapper.selectByPrimaryKey(id));    }    public Optional<Traveller> getEntityByEntity(Traveller traveller){return Optional.ofNullable(travellerMapper.selectOne(traveller));}    public Optional<Traveller> getEntityByName(String name) {        Traveller traveller = new Traveller();        traveller.setName(name);        return Optional.ofNullable(travellerMapper.selectOne(traveller));    }    public List<Traveller> getAll(int page) {        PageHelper.startPage(page, Constant.PAGE_SIZE);        return travellerMapper.selectAll();    }    public List<Traveller> getAll(String type, String value, int page) {        Example example = new Example(Traveller.class);        Example.Criteria criteria = example.createCriteria();        criteria.andLike(type, "%" + value + "%");        PageHelper.startPage(page, Constant.PAGE_SIZE);        return travellerMapper.selectByExample(example);    }    public int getCount() {        int count = travellerMapper.selectCount(new Traveller());        return count;    }    public int getCount(String type, String value) {        Example example = new Example(Traveller.class);        Example.Criteria criteria = example.createCriteria();        criteria.andLike(type, "%" + value + "%");        int count = travellerMapper.selectCountByExample(example);        return count;    }    @Transactional    public boolean addEntity(Traveller traveller) {        return travellerMapper.insertUseGeneratedKeys(traveller) > 0;    }    @Transactional    public boolean modifyEntityById(Traveller traveller) {        return travellerMapper.updateByPrimaryKey(traveller) > 0;    }    @Transactional    public boolean deleteEntityById(int id) {        return travellerMapper.deleteByPrimaryKey(id) > 0;    }}