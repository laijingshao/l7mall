package com.ls.l7mall.dao;

import com.ls.l7mall.entity.PayInfo;
import org.springframework.stereotype.Repository;

/**
 * @author laijs
 * @date 2020-3-20-18:42
 */
@Repository("payInfoDao")
public interface PayInfoDao {
    public int insert(PayInfo payInfo);
}
