package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    /**
     * 根据菜品id查询对应的套餐id
     * @param dishIds
     */
    //SELECT setmeal_id FROM setmeal_dish WHERE dish_id IN (dishIds)
    List<Long> getSetmealDishIds(List<Long> dishIds);
}
