package com.redis.transfer.beans;

public class Users {
   private Integer id;
   private Double count;

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", count=" + count +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }
}
