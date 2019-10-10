package service;

import entity.Limit;
import mapper.MainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("MainService")
@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
public class MainServiceImp implements MainService {
    @Autowired
    private MainMapper mapper;
    @Override
    public List<Limit> findAllLimit(int empid) {
        return mapper.findAllLimit(empid);
    }
}
